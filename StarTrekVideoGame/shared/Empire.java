package StarTrekVideoGame.shared;


public class Empire {

	 private String empireId;
	 private String name;
	 private String type;
	 
	 public Empire() {
		empireId = "";
		name = "";
		type = ""; 
	 }
	 
	 /**
	  * Empire constructor that takes in a string and parses out empire information.
	  * @param string
	  */
	 public Empire (String string) {
		 String [] empireInfo = string.trim().split("\t");
		 empireId = empireInfo[0];
		 name = empireInfo[1];
		 type = empireInfo[2];
	 }
	
	 public String getEmpireId() {
		return empireId;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
}
