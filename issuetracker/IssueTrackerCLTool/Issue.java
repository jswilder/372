//package edu.clemson.cs.cpsc3720.project;

public class Issue {
	private String project;
	private int id;
	private String title;
	private String description;
	private String status;
	//Client side constructor - ID is not initialized and will be 0. Server side must assign an ID to the issue.

	public Issue(String project, int id, String title, String description, String status) {
		this.project = project;
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
