import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import StarTrekVideoGame.shared.NanoHTTPD;

import com.google.gson.Gson;


public class Server extends NanoHTTPD {


	private String 	stringdata;
	private Object jsondata;
	private Socket sock;
	private String lineData;
	private DataBase projects = new DataBase();
	private String projectName;
	private ServerSocket serverSocket;

	public Server (int port) {
		super(port);
	}

	public synchronized Response serve(IHTTPSession session) {

	    String body = getBody(session);
	    String string = null;
	    ArrayList<Issue> issue = new ArrayList<Issue>();
	    Object result;
		String jsonResponse = "";

	    System.out.println("body = " + body);

	    if (body.contains("<CREATE>")) {
	    	string = determine(body);
	    }

	    if (body.contains("<REQUEST>")) {
		System.out.println("here");
	    	issue = sendProjects(body);
		jsonResponse = new Gson().toJson(issue);
		System.out.println("json Response " + jsonResponse);
		string = null;
	    }

	    if (body.contains("<EDIT>")) {
	    	string = determine(body);
	    }

	    if (body.contains("<DELETE>")) {
	    	string = determine(body);
	    }

	    if(string == null || string.equals("")) {
			System.out.println(jsonResponse);
			return new Response(jsonResponse);
		} else {
			System.out.println(string);
			return new Response(string);
		}
	}


	  protected String getBody(IHTTPSession session) {
		    int len = Integer.parseInt(session.getHeaders().get("content-length"));
		    InputStream inputStream = session.getInputStream();

		    try {
		      byte[] buf = new byte[len];
		      int bytesRead = 0;
		      int read = 0;
		      while (bytesRead < len &&
		        (read = inputStream.read(buf, bytesRead, len - bytesRead)) > 0) {
		          bytesRead += read;
		      }
		      return new String(buf);
		    } catch (IOException ex) {
		      ex.printStackTrace();
		    }
		    return "";
		  }

	public ArrayList<Issue> sendProjects(String task) {

		ArrayList<Issue> issue = new ArrayList<Issue>();

		if (task.startsWith("<REQUEST>")) {

			projectName = getProjectName(task);

			if (projectName.contains("Project 1")) {
				issue = projects.getProIssue1();
			}
			if (projectName.contains("Project 2")) {
				issue = projects.getProIssue2();
			}
			if (projectName.contains("Project 3")) {
				issue = projects.getProIssue3();
			}
		}

		return issue;
	}

	public String determine(String task) {

		String response = null;

		if (task.startsWith("<CREATE>")) {

			projectName = getProjectName(task);
			String JSON = getJSON(task);
			Issue issue = new Gson().fromJson(JSON, Issue.class);
			String jsonResponse = new Gson().toJson(issue);

			if (projectName.contains("Project 1")) {
				projects.getProject1().addIssue(issue);
			}
			if (projectName.contains("Project 2")) {
				projects.getProject2().addIssue(issue);
			}
			if (projectName.contains("Project 3")) {
				projects.getProject3().addIssue(issue);
			}

			response = "<CREATE OK>";
		}

		if (task.startsWith("<EDIT>")) {

			String JSON = GetJson(task);
			Issue issue = new Gson().fromJson(JSON, Issue.class);
			String jsonResponse = new Gson().toJson(issue);

			projectName = getProjectName(task);

			if (projectName.contains("Project 1")) {

				projects.getProject1().editIssue(issue, issue.getId());
			}
			if (projectName.contains("Project 2")) {
				projects.getProject2().editIssue(issue, issue.getId());
			}
			if (projectName.contains("Project 3")) {
				projects.getProject3().editIssue(issue, issue.getId());
			}

			response = "<EDIT OK>";
		}

		if (task.startsWith("<DELETE>")) {

			String JSON = getJSON(task);
			int number = Integer.parseInt(JSON);

			projectName = getProjectName(task);

			if (projectName.contains("Project 1")) {
				projects.getProject1().deleteIssue(number);
			}
			if (projectName.contains("Project 2")) {
				projects.getProject2().deleteIssue(number);
			}
			if (projectName.contains("Project 3")) {
				projects.getProject3().deleteIssue(number);
			}

			response = "<DELETE OK>";
		}

		return response;
	}

        public String GetJson(String json) {
	  
	  String[] list = json.split("><");
	  String result = list[3].replace(">", "");

	  return result;
	}


	public String getLineData() {
		return lineData;
	}

	public String getProjectName(String pName) {

		String[] list = pName.split("><");
		String result = list[1].replace(">", "");

		return result;
	}

	public String getJSON(String json) {

		String[] list = json.split("><");
		String result = list[2].replace(">", "");

		return result;
	}

	public DataBase getProjects() {
		return projects;
	}
}
