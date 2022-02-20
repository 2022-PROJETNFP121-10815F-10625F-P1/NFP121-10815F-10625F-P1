package Model;

import java.util.List;

public class PhoneNumbers {
	

	private int Region_code;
	private int Phone_Number;
	private int Contacts_ID;
	private List<String> list;
	

	public PhoneNumbers(List<String> lt) {
		 
		list = lt;
		 
		 
	}
	public PhoneNumbers(int region_code, int phone_Number, int contacts_ID) {
		 
		Region_code = region_code;
		Phone_Number = phone_Number;
		Contacts_ID = contacts_ID;
	}

	public int getRegion_code() {
		return Region_code;
	}

	public void setRegion_code(int region_code) {
		Region_code = region_code;
	}

	public int getPhone_Number() {
		return Phone_Number;
	}

	public void setPhone_Number(int phone_Number) {
		Phone_Number = phone_Number;
	}

	public int getContacts_ID() {
		return Contacts_ID;
	}

	public void setContacts_ID(int contacts_ID) {
		Contacts_ID = contacts_ID;
	}

	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
}
