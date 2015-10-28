//package edu.clemson.cs.cpsc3720.project.client;

import java.util.ArrayList;

import StarTrekVideoGame.shared.HttpRequest;

import com.google.gson.Gson;
//import com.github.kevinsawicki.http.HttpRequest;

//import edu.clemson.cs.cpsc3720.project.Issue;

public class DataManager {
	private static ArrayList<String> projects;
	private static ArrayList<Issue> issues;
	private static ServerAddress serverAddress;

	static {
		projects = new ArrayList<String>();
		issues = new ArrayList<Issue>();
		projects.add("Project 1");
		projects.add("Project 2");
		projects.add("Project 3");
		Issue issue = new Issue("Project 1", 1, "i/o exception", "server doesn't read files", "ok");
		issues.add(issue);
	}

	//empty initializer
	public DataManager() {}

	public static String sendRequest(String request) {
		//System.out.println(request);

		String url = "http://" + serverAddress.getAddress() + ":" + Integer.toString(serverAddress.getPort()) + "/";
	    HttpRequest httpReq = HttpRequest.post(url).send(request);
	    String jsonResponse = httpReq.body();

	    //System.out.println(jsonResponse);

	    return jsonResponse;
	}

	/**
	 *
	 * @param index
	 * @return
	 */
	public static String getProject(int index) {
		if(index > 2 || index < 0) {
			return "";
		}
		return projects.get(index);
	}

	public static Issue getIssueById(int editId) {
		Issue retVal = null;
		for(int i = 0; i < issues.size(); i++) {
			if(issues.get(i).getId() == editId) {
				return issues.get(i);
			}
		}
		return retVal;
	}

	public static Issue getIssue(int index) {
		return issues.get(index);
	}
	public static ArrayList<Issue> getIssues() {
		return issues;
	}

	public static void setIssues(ArrayList<Issue> list) {
		DataManager.issues = list;
	}

	public static void addIssue(Issue issue) {
		issues.add(issue);
	}

	public static ServerAddress getServerAddress() {
		return serverAddress;
	}

	public static void setServerAddress(ServerAddress address) {
		serverAddress = address;
	}
}
