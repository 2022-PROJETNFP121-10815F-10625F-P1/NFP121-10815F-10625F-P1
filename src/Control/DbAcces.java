package Control;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Contacts;
import Model.Contacts_Groups;
import Model.ListGroups;
import Model.PhoneNumbers;

public class DbAcces {
	Connection connection;
	public DbAcces() {

	}
	public void connectDB () {
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
			connection= DriverManager.getConnection("jdbc:ucanaccess://database/ContactsManagement.accdb");
		}catch(Exception e){
			System.out.println("Error in connection");

		}
	}

	public void insertContacts(Contacts contacts) {

		try {
			connectDB();
			String sql = "insert into Contacts values(1,'"+contacts.getFirstname()+"','"+contacts.getLastname()+"','"+contacts.getCity()+"'"+");";
			Statement stmt = connection.createStatement();
			int result = stmt.executeUpdate(sql);

			if (result > 0)
				System.out.println("successfully inserted INTO Contacts ");

			else
				System.out.println(
						"unsucessful insertion INTO Contacts ");

			// closing connection
			connection.close();      

		}catch (SQLException e) {
			System.out.println(e);
		}






	}

	public void insertgroups(ListGroups ListGroups) {

		try {
			connectDB();
			String sql = "insert into ListGroups values(1,'"+ListGroups.getGroup()+"','"+ListGroups.getDescription()+ "'"+");";
			Statement stmt = connection.createStatement();
			int result = stmt.executeUpdate(sql);

			if (result > 0)
				System.out.println("successfully inserted INTO ListGroups ");

			else
				System.out.println(
						"unsucessful insertion INTO ListGroups ");

			// closing connection
			connection.close();      

		}catch (SQLException e) {
			System.out.println(e);
		}






	}

	public void insertphones(PhoneNumbers phoneNumbers,Contacts contacts) {


		String region_code,phone_number,sql,selectID ;

		try {
			connectDB();	
			selectID = "select ID From Contacts where FirstName ='"+contacts.getFirstname()+"' and LastName='"+contacts.getLastname()+"'and City ='" + contacts.getCity()+"'";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectID);
			int ID = 0;
			while (rs.next()) {
				ID = rs.getInt("ID");

			}

			for (int i = 0 ; i <phoneNumbers.getList().size();i++) {

				region_code = phoneNumbers.getList().get(i).substring(0, 3);
				phone_number = phoneNumbers.getList().get(i).substring(3);
				sql = "insert into PhoneNumbers (ID,Region_code,Phone_Number,Contacts_ID) values(1,'"+region_code+"','"+phone_number+"',"+ID+");";
				Statement stmt = connection.createStatement();
				int result = stmt.executeUpdate(sql);

				if (result > 0)
					System.out.println("successfully inserted INTO PhoneNumbers ");

				else
					System.out.println(
							"unsucessful insertion INTO PhoneNumbers");
			}


		}catch (SQLException e) {
			System.out.println(e);
		}
	}

	public List<String> search_name(String name) {
		List<String> list = new ArrayList<String>();
		Contacts contacts  = new Contacts(list);
		String FN,LN,CT = "";
		connectDB();
		try {
			String selectContact = "select * From Contacts where FirstName like '"+name+"%'"; 
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectContact);
			while (rs.next()) {
				FN = rs.getString("FirstName"); 
				LN = rs.getString("LastName"); 
				CT = rs.getString("City"); 					 
				list.add(FN + " "+LN + "-" + CT );

			}
			contacts.setList(list);

		}catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}

	public List<String> LoadData( String action) {


		List<String> list = new ArrayList<String>();
		ListGroups ListGroups = new ListGroups(list);
		Contacts contacts  = new Contacts(list);
		String groups,FN,LN,CT = "";
		connectDB();	




		if (action.equals("load_groups")) {
			try {
				String selectGroups = "select * From ListGroups;";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(selectGroups);

				while (rs.next()) {
					groups = rs.getString("Group_name"); 
					list.add(groups);

				}
				ListGroups.setList(list);


			}catch (SQLException e) {
				System.out.println(e);
			}

		}



		if (action.equals("view_contacts")) {
			try {
				String selectContact = "select * From Contacts;";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(selectContact);
				while (rs.next()) {
					FN = rs.getString("FirstName"); 
					LN = rs.getString("LastName"); 
					CT = rs.getString("City"); 					 
					list.add(FN + " "+LN + "-" + CT );

				}
				contacts.setList(list);


			}catch (SQLException e) {
				System.out.println(e);
			}
		}

		if (action.equals("Sort_ByFN")) {
			try {
				String selectContact = "select * From Contacts ORDER BY FirstName ";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(selectContact);
				while (rs.next()) {
					FN = rs.getString("FirstName"); 
					LN = rs.getString("LastName"); 
					CT = rs.getString("City"); 					 
					list.add(FN + " "+LN + "-" + CT );

				}
				contacts.setList(list);


			}catch (SQLException e) {
				System.out.println(e);
			}
		}


		if (action.equals("Sort_ByLN")) {
			try {
				String selectContact = "select * From Contacts ORDER BY LastName ";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(selectContact);
				while (rs.next()) {
					FN = rs.getString("FirstName"); 
					LN = rs.getString("LastName"); 
					CT = rs.getString("City"); 					 
					list.add(FN + " "+LN + "-" + CT );

				}
				contacts.setList(list);


			}catch (SQLException e) {
				System.out.println(e);
			}
		}


		if (action.equals("Sort_ByCT")) {
			try {
				String selectContact = "select * From Contacts ORDER BY City ";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(selectContact);
				while (rs.next()) {
					FN = rs.getString("FirstName"); 
					LN = rs.getString("LastName"); 
					CT = rs.getString("City"); 					 
					list.add(FN + " "+LN + "-" + CT );

				}
				contacts.setList(list);


			}catch (SQLException e) {
				System.out.println(e);
			}
		}

		return list;
	}

	public List<String> View_detail_contact(Contacts contacts) {
		List<String> list = new ArrayList<String>();
		PhoneNumbers phoneNumbers = new PhoneNumbers(list);
		Contacts cont  = new Contacts(list);
		Contacts_Groups Contacts_Groups = new Contacts_Groups(list);
		try {
			connectDB();
			String ct1 =contacts.getList().toString().replace("[","'");
			String ct2 =ct1.replace("]","'");
			String selectContact_ID = "select * From CONTACTS WHERE (FIRSTNAME &\" \" & LASTNAME &\"-\"& City )= " +ct2 ;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectContact_ID);
			int ID = 0;
			String fn ,ln,city="";
			while (rs.next()) {
				ID = rs.getInt("ID"); 
				fn = rs.getString("FIRSTNAME") ;
				ln = rs.getString("LASTNAME") ;
				city = rs.getString("City") ;
				list.add(fn + "-" + ln+ "/" + city);

			}


			cont.setList(list);




			String details=" SELECT Region_code,Phone_Number From PHONENUMBERS  WHERE Contacts_ID="+ID ;

			Statement statement2 = connection.createStatement();
			ResultSet rs2 = statement2.executeQuery(details);
			String Region_code,Phone_Number="";
			while (rs2.next()) {
				Region_code = rs2.getString("Region_code") ;
				Phone_Number = rs2.getString("Phone_Number") ;
				list.add(Region_code + "-" + Phone_Number);
			}
			phoneNumbers.setList(list);






		}catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}

	public void insertContact_Groups(Contacts_Groups Contacts_Groups,Contacts contacts) {

		String groups,sql,selectID,selectgroups ;

		try {
			connectDB();	
			selectID = "select ID From Contacts where FirstName ='"+contacts.getFirstname()+"' and LastName='"+contacts.getLastname()+"'and City ='" + contacts.getCity()+"'";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectID);
			int ID = 0;
			while (rs.next()) {
				ID = rs.getInt("ID");

			}    

			for (int i = 0 ; i <Contacts_Groups.getList().size();i++) {

				groups = Contacts_Groups.getList().get(i).toString();
				selectgroups = "select * From ListGroups ";

				Statement stat = connection.createStatement();
				ResultSet rs1 = stat.executeQuery(selectgroups);
				int ID_group = 0;
				String grp = "";
				while (rs1.next()) {
					ID_group = rs1.getInt("ID");
					grp = rs1.getString("Group_name"); 
					if (grp.equals(groups)) {
						// System.out.println("ID " + ID_group);
						// System.out.println("Group " + grp);
						sql = "insert into Contacts_Groups (ID,Contact_ID,Group_ID) values(1,"+ID+","+ID_group +")";
						Statement stmt = connection.createStatement();
						int result = stmt.executeUpdate(sql);
						if (result > 0)
							System.out.println("successfully inserted INTO Contacts_Groups ");

						else
							System.out.println(
									"unsucessful insertion INTO Contacts_Groups ");
					}
				}
			}  

		}catch (SQLException e) {
			System.out.println(e);
		}    	

	}

	public List<String> View_detail_contact_group(String string,Contacts_Groups contacts_Groups) {
		List<String> list = new ArrayList<String>();
		contacts_Groups =new Contacts_Groups(list);

		try {
			connectDB();
			String selectContact_ID = "select * From CONTACTS WHERE (FIRSTNAME &\"-\" & LASTNAME &\"/\"& City )= '" +string +"'" ;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectContact_ID);
			int ID = 0;

			while (rs.next()) {
				ID = rs.getInt("ID"); 		  

			}		
			String cont_grp = "SELECT L.*,G.* FROM ListGroups L , CONTACTS_GROUPS G  WHERE L.ID=G.GROUP_ID and  G.Contact_ID = " +ID;

			Statement statement3 = connection.createStatement();
			ResultSet rs3 = statement3.executeQuery(cont_grp);
			String group="";
			while (rs3.next()) {	
				group=rs3.getString("Group_name");
				list.add(group);

			}

			contacts_Groups.setList(list);

		}catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}

	public int getID(Contacts contacts) {
		int ID = 0;
		try {
			connectDB();

			String selectID = "select ID From Contacts where FirstName ='"+contacts.getFirstname()+"' and LastName='"+contacts.getLastname()+"'and City ='" + contacts.getCity()+"'";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectID);

			while (rs.next()) {
				ID = rs.getInt("ID");

			}	
			// closing connection
			connection.close();      

		}catch (SQLException e) {
			System.out.println(e);
		}
		return ID;			


	}

	public int getID_group(ListGroups ListGroups) {
		int ID = 0;
		try {
			connectDB();

			String selectID = "select ID From ListGroups where Group_name='"+ListGroups.getGroup()+"' and Description='"+ListGroups.getDescription()+"';";
			 
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectID);

			while (rs.next()) {
				ID = rs.getInt("ID");

			}	
			// closing connection
			connection.close();      

		}catch (SQLException e) {
			System.out.println(e);
		}

		return ID;			


	}	
	
	public int getID_groups(ListGroups ListGroups) {
		int ID = 0;
		String x = ListGroups.getList().toString().replace("[", "'");
		String x1 = x.toString().replace("]", "'");
		try {
			connectDB();

			String selectID = "select ID From ListGroups where Group_name="+x1;
			 
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectID);

			while (rs.next()) {
				ID = rs.getInt("ID");

			}	
			// closing connection
			connection.close();      

		}catch (SQLException e) {
			System.out.println(e);
		}

		return ID;			


	}

	public void delete_contacts(String name) {

		try {
			connectDB();
			String selectContact_ID = "select * From CONTACTS WHERE (FIRSTNAME &\" \" & LASTNAME &\"-\"& City )= " +name ;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectContact_ID);
			int ID = 0;
			while (rs.next()) {
				ID = rs.getInt("ID");
			}

			String delete_contacts ="delete from contacts where ID =" +ID;
			Statement stmt = connection.createStatement();
			int result = stmt.executeUpdate(delete_contacts);

			if (result > 0)
				System.out.println("Contacts Successfully DELETED  ");

			else
				System.out.println(
						"Contacts Unsucessful DELETED  ");		

			String delete_PhoneNumbers ="delete from PhoneNumbers  where Contacts_ID ="+ID;
			Statement stmt1 = connection.createStatement();
			int result1 = stmt1.executeUpdate(delete_PhoneNumbers);

			if (result1 > 0)
				System.out.println("PhoneNumbers Successfully DELETED  ");

			else
				System.out.println(
						"PhoneNumbers Unsucessful DELETED  ");				

			String delete_Contacts_Groups ="delete from Contacts_Groups  where Contact_ID ="+ID;
			Statement stmt2 = connection.createStatement();
			int result2 = stmt2.executeUpdate(delete_Contacts_Groups);

			if (result2 > 0)
				System.out.println("Contacts_Groups Successfully DELETED  ");

			else
				System.out.println(
						"Contacts_Groups Unsucessful DELETED  ");	



		}catch (SQLException e) {
			System.out.println(e);
		}finally {
			// closing connection
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	public void updateContacts(int id,Contacts contacts,PhoneNumbers phoneNumbers,Contacts_Groups Contacts_Groups) {
		String region_code,phone_number,sql1,selectID,groups,selectgroups ; 
		int ID_number=0;
		int ID_grp_number=0;

		try {
			connectDB();


			String sql = "update Contacts set FirstName='"+contacts.getFirstname()+"',"+"LastName='"+contacts.getLastname()+"',City='"+contacts.getCity()+"'where ID="+id;
			Statement stmt = connection.createStatement();
			int result = stmt.executeUpdate(sql);

			if (result > 0)
				System.out.println("Contacts Successfully UPDATED  ");

			else
				System.out.println(
						"Contacts Unsucessful UPDATED  ");


			String ID_phone = "select ID From PhoneNumbers  where Contacts_ID ="+id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(ID_phone);
			int r = 0;
			while (rs.next()) {
				ID_number = rs.getInt("ID");
				region_code = phoneNumbers.getList().get(r).substring(0, 3);
				phone_number = phoneNumbers.getList().get(r).substring(3);
				sql1 = "UPDATE PhoneNumbers SET Region_code='"+region_code+"',Phone_Number='"+phone_number+"' WHERE Contacts_ID="+id + " and ID="+ID_number;
				Statement stmt1 = connection.createStatement();
				int result1 = stmt1.executeUpdate(sql1);
				if (result1 > 0)
					System.out.println("PhoneNumbers successfully  UPDATED    ");

				else
					System.out.println(
							"PhoneNumbers unsucessful UPDATED ");
				r++;
			}


			String ID_group = "select ID From Contacts_Groups  where Contact_ID ="+id;
			Statement statement1 = connection.createStatement();
			ResultSet rs1 = statement1.executeQuery(ID_group);
			int r1 = 0;
			while (rs1.next()) {
				ID_grp_number = rs1.getInt("ID");	
				groups = Contacts_Groups.getList().get(r1).toString();
				selectgroups = "select * From ListGroups ";				
				Statement stat = connection.createStatement();
				ResultSet rs2 = stat.executeQuery(selectgroups);
				int ID_group1 = 0;
				String grp = "";			
				while (rs2.next()) {
					ID_group1 = rs2.getInt("ID");
					grp = rs2.getString("Group"); 
					if (grp.equals(groups)) {
						String sql2 = "UPDATE  Contacts_Groups SET  Group_ID="+ID_group1 +" WHERE Contact_ID= "+id + " and id =" + ID_grp_number ;

						Statement stmt2 = connection.createStatement();
						int result2 = stmt2.executeUpdate(sql2);
						if (result2 > 0)
							System.out.println(" Contacts_Groups successfully UPDATED  ");

						else
							System.out.println(
									" Contacts_Groups unsucessful     UPDATED ");
					}
				}
				if ( Contacts_Groups.getList().size()>1){
					r1++;
				}

			}




			// closing connection
			connection.close();      

		}catch (SQLException e) {
			System.out.println(e);
		}

	}


	public String getDescription(ListGroups lt_grp) {
		String x = lt_grp.getList().toString().replace("[", "'");
		String grp_name = x.replace("]", "'");

		String  desc = "";
		try {
			connectDB();
			String selectGroup = "select * From ListGroups WHERE Group_name= " +grp_name ;


			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectGroup);

			while (rs.next()) {
				desc = rs.getString("Description");
			}
		}catch (SQLException e) {
			System.out.println(e);
		}finally {
			// closing connection
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

		return desc;


	}

	public void updategroup(int id,ListGroups l_grps)  {
		try {
			connectDB();


			String sql = "update ListGroups set Group_name='"+l_grps.getGroup()+"',"+"Description='"+l_grps.getDescription()+"'where ID="+id;

			Statement stmt = connection.createStatement();
			int result = stmt.executeUpdate(sql);

			if (result > 0)
				System.out.println("Contacts Successfully UPDATED  ");

			else
				System.out.println(
						"Contacts Unsucessful UPDATED  ");


		}catch (SQLException e) {
			System.out.println(e);
		}finally {
			// closing connection
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}


	public void delete_group (int Id) {

		try {
			connectDB();	
			String selectID = "Delete  From ListGroups where ID ="+Id;
			Statement stmt = connection.createStatement();
			int result = stmt.executeUpdate(selectID);

			if (result > 0)
				System.out.println("ListGroups Successfully DELETED  ");

			else
				System.out.println(
						"ListGroups Unsucessful DELETED  ");	
			
			
			
		}catch (SQLException e) {
			System.out.println(e);
		}finally {
			// closing connection
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}	
	}

}