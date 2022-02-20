package Model;

import java.util.List;

public class ListGroups {

	private String Group;
	private String Description;

 
	private List<String> list;
	

	public ListGroups(List<String> lt) {
		 
		this.list = lt;
		
	}
	public ListGroups(String group,String Description) {
		 
		this.Group = group;
		this.Description = Description; 
	}
	public ListGroups(String group) {
		 
		Group = group;
	}
	public ListGroups( ) {
		 
		 
	}
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public String getGroup() {
		return Group;
	}

	public void setGroup(String group) {
		Group = group;
	}
	
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
}
