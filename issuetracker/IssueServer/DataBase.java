import java.util.ArrayList;


public class DataBase {

	private ProjectImpl project1;
	private ProjectImpl project2;
	private ProjectImpl project3;
	
	DataBase () {
		project1 = new ProjectImpl();
		project2 = new ProjectImpl();
		project3 = new ProjectImpl();
	}
	
	public void setProject1(ProjectImpl project1) {
		this.project1 = project1;
	}

	public void setProject2(ProjectImpl project2) {
		this.project2 = project2;
	}

	public void setProject3(ProjectImpl project3) {
		this.project3 = project3;
	}

	public ProjectImpl getProject1() {
		return project1;
	}
	
	public ProjectImpl getProject2() {
		return project2;
	}
	
	public ProjectImpl getProject3() {
		return project3;
	}
	
	public void sendProjectNames() {
		
	}
	
	public ArrayList<Issue> getProIssue1() {
		
		return project1.getIssue();
	}
	public ArrayList<Issue> getProIssue2() {
		return project1.getIssue();
	}
	public ArrayList<Issue> getProIssue3() {
		return project1.getIssue();
	}
}

