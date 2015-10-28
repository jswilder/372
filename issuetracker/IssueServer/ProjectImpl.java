import java.util.ArrayList;

public class ProjectImpl implements Project {

	private ArrayList<Issue> issues;
	
	public ProjectImpl () {
		
		issues = new ArrayList<Issue>();	
	}
	
	public void deleteIssue(int i) {
	
			int cmp;
		
		for (int j = 0; j < issues.size(); ++j) {
			
			cmp = issues.get(j).getId();
			if(cmp == i) {
				issues.remove(j);
			}
		}
	}
	
	public void addIssue(Issue i) {;
		issues.add(i);
	}
	
	public void editIssue(Issue i, int Id) {

		deleteIssue(Id);
		issues.add(i);
	}
	
	public ArrayList<Issue> getIssue () {
		return issues;
	}
}
