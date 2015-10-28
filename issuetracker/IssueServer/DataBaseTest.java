import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataBaseTest {

	private DataBase db;
	private DataBase db1;
	private DataBase db2;
	private DataBase db3;
	private DataBase db4;
	private DataBase db5;
	private DataBase db6;
	
	@Before
	public void setUp() throws Exception {
		
		// for constructor
		db = new DataBase();
		
		db1 = new DataBase();
		db1.getProject1().addIssue(new Issue("project", 1, "testTitle", "testDescription", "testStatus"));
		db1.getProject1().addIssue(new Issue("project", 2, "testTitle", "testDescription", "testStatus"));

		db2 = new DataBase();
		db2.getProject1().addIssue(new Issue("project", 3, "testTitle", "testDescription", "testStatus"));
		db2.getProject1().addIssue(new Issue("project", 4, "testTitle", "testDescription", "testStatus"));
		
		db3 = new DataBase();
		db3.getProject1().addIssue(new Issue("project", 5, "testTitle", "testDescription", "testStatus"));
		db3.getProject1().addIssue(new Issue("project", 6, "testTitle", "testDescription", "testStatus"));
		
		db4 = new DataBase();
		db4.getProject1().addIssue(new Issue("project", 7, "testTitle", "testDescription", "testStatus"));
		db4.getProject1().addIssue(new Issue("project", 8, "testTitle", "testDescription", "testStatus"));
		
	}
	
	@Test
	public void testAddIssue() {
		db.getProject1().addIssue(new Issue("project", 10, "testTitle", "testDescription", "testStatus"));
		assertEquals(1, db.getProject1().getIssue().size());
		
		db.getProject1().addIssue(new Issue("project", 11, "testTitle", "testDescription", "testStatus"));
		assertEquals(2, db.getProject1().getIssue().size());
		
		db.getProject1().addIssue(new Issue("project", 14, "testTitle", "testDescription", "testStatus"));
		assertEquals(3, db.getProject1().getIssue().size());

	}
	
	@Test
	public void testDeleteIssue() {
		db3.getProject1().deleteIssue(5);
		assertEquals(1, db3.getProject1().getIssue().size());
		
		db2.getProject1().deleteIssue(4);
		db2.getProject1().deleteIssue(5);
		assertEquals(1, db2.getProject1().getIssue().size());
		
		db3.getProject1().deleteIssue(6);
		db3.getProject1().deleteIssue(5);
		assertEquals(0, db3.getProject1().getIssue().size());
		
	}
	
	@Test
	public void testEditIssue() {
		db4.getProject2().addIssue(new Issue("project", 1, "title", "testDesc", "teststat"));
		db4.getProject2().editIssue(new Issue("project", 1, "change", "testDesc", "teststat"), 1);
		assertEquals("change", db4.getProject2().getIssue().get(0).getTitle());
		
		db3.getProject2().addIssue(new Issue("project", 1, "title", "testDesc", "teststat"));
		db3.getProject2().editIssue(new Issue("project", 1, "change", "testDesc", "teststat"), 1);
		assertEquals("change", db3.getProject2().getIssue().get(0).getTitle());
		
		db4.getProject3().addIssue(new Issue("project", 1, "title", "testDesc", "teststat"));
		db4.getProject3().editIssue(new Issue("project", 1, "title", "testDesc", "change"), 1);
		assertEquals("change", db4.getProject3().getIssue().get(0).getStatus());
	}
}
