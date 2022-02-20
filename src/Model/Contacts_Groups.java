package Model;

import java.util.List;

public class Contacts_Groups {

	private int Contact_ID;
	private int Group_ID;
	private List<String> list;

	public Contacts_Groups(List<String> lt) {

		list = lt;


	}
	public Contacts_Groups(int contact_ID, int group_ID) {
		Contact_ID = contact_ID;
		Group_ID = group_ID;
	}
	public int getContact_ID() {
		return Contact_ID;
	}
	public void setContact_ID(int contact_ID) {
		Contact_ID = contact_ID;
	}
	public int getGroup_ID() {
		return Group_ID;
	}
	public void setGroup_ID(int group_ID) {
		Group_ID = group_ID;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
}
