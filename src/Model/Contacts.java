package Model;

import java.util.List;

public class Contacts {


	private String firstname;
	private String lastname;
	private String City;

	private List<String> list;

	public Contacts(List<String> lt) {

		list = lt;


	}
	public Contacts(String firstname, String lastname,String City) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.City = City;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String City) {
		this.City = City;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
}
