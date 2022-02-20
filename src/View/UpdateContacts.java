package View;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Control.Actions;
import Control.DbAcces;
import Model.Contacts;
import Model.Contacts_Groups;
import Model.ListGroups;
import Model.PhoneNumbers;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class UpdateContacts implements Actions{

	protected Shell shell;
	Text txtFN;
	Text txtLN;
	Text txtCT;
	private Text txtNumber;
	private Text txtNumber1;
	private Text txtNumber2;
	private Text txtCode2;
	private Text txtCode1;
	private Text txtCode;
	private Contacts contacts;
	private Contacts_Groups contacts_Groups;
 
	private String first_name,last_name,city="";
	private String[] numbers ;
 
	private ListGroups ListGroups;
	private int id =0;

	/**
	 * Launch the application.
	 * @param args
	 */
 

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(605, 548);
		shell.setText("Gestion des contacts");
		Label lblGestionDesContacts = new Label(shell, SWT.NONE);
		lblGestionDesContacts.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.BOLD));
		lblGestionDesContacts.setBounds(173, 10, 232, 29);
		lblGestionDesContacts.setText("Gestion des contacts");

		Label lblNewContact = new Label(shell, SWT.NONE);
		lblNewContact.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewContact.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lblNewContact.setBounds(10, 181, 197, 34);
		lblNewContact.setText("Update Contacts");



		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setText("First Name");
		lblFirstName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblFirstName.setBounds(234, 58, 94, 20);

		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setText("Last Name");
		lblLastName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblLastName.setBounds(234, 90, 84, 20);

		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setText("City");
		lblCity.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblCity.setBounds(234, 118, 84, 20);



		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("Region Code");
		lblNewLabel.setBounds(266, 192, 94, 20);

		Label lblPhoneNumber = new Label(shell, SWT.NONE);
		lblPhoneNumber.setText("Phone Number");
		lblPhoneNumber.setBounds(400, 192, 111, 20);

		Label lblPhoneNumbers = new Label(shell, SWT.NONE);
		lblPhoneNumbers.setText("Phone numbers");
		lblPhoneNumbers.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblPhoneNumbers.setBounds(312, 166, 141, 20);

		Label lblContactGroup = new Label(shell, SWT.NONE);
		lblContactGroup.setText("Modify Contact Group");
		lblContactGroup.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblContactGroup.setBounds(286, 312, 197, 20);



		txtFN = new Text(shell, SWT.BORDER);
		txtFN.setText(first_name);
		txtFN.setBounds(334, 56, 219, 26);

		txtLN = new Text(shell, SWT.BORDER);
		txtLN.setBounds(334, 88, 219, 26);
		txtLN.setText(last_name);

		txtCT = new Text(shell, SWT.BORDER);
		txtCT.setBounds(334, 116, 219, 26);
		txtCT.setText(city);

		txtNumber = new Text(shell, SWT.BORDER);
		txtNumber.setBounds(353, 216, 175, 26);

		txtNumber1 = new Text(shell, SWT.BORDER);
		txtNumber1.setBounds(353, 248, 175, 26);

		txtNumber2 = new Text(shell, SWT.BORDER);
		txtNumber2.setBounds(353, 280, 175, 26);

		txtCode2 = new Text(shell, SWT.BORDER);
		txtCode2.setBounds(276, 280, 42, 26);

		txtCode1 = new Text(shell, SWT.BORDER);
		txtCode1.setBounds(276, 248, 42, 26);

		txtCode = new Text(shell, SWT.BORDER);
		txtCode.setBounds(276, 216, 42, 26);


		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(213, 45, 15, 446);

		for (int a = 0 ; a<numbers.length;a++) {



			if (numbers.length>2) {
				if (numbers[0] != null) {
					txtCode2.setText(numbers[0].substring(0,numbers[0].toString().indexOf("-")));
					txtNumber2.setText(numbers[0].substring(numbers[0].toString().indexOf("-") +1)); 
				}else {

					txtCode2.setText(" ");
					txtNumber2.setText(" ");
				}
				if (numbers[1] != null) {
					txtCode1.setText(numbers[1].substring(0,numbers[1].toString().indexOf("-")));
					txtNumber1.setText(numbers[1].substring(numbers[1].toString().indexOf("-") +1));

				}else {
					txtCode1.setText(" ");
					txtNumber1.setText(" ");
				}

				if (numbers[2] != null) {
					txtCode.setText(numbers[2].substring(0,numbers[2].toString().indexOf("-")));
					txtNumber.setText(numbers[2].substring(numbers[2].toString().indexOf("-") +1));

				}else {
					txtCode.setText(" ");
					txtNumber.setText(" ");
				}
			}else {
				if (numbers[0] != null) {
					txtCode2.setText(numbers[0].substring(0,numbers[0].toString().indexOf("-")));
					txtNumber2.setText(numbers[0].substring(numbers[0].toString().indexOf("-") +1)); 
				}else {

					txtCode2.setText(" ");
					txtNumber2.setText(" ");
					txtCode.setText(" ");
					txtNumber.setText(" ");
				}
				if (numbers.length > 1){
					if (numbers[1] != null) {
						txtCode1.setText(numbers[1].substring(0,numbers[1].toString().indexOf("-")));
						txtNumber1.setText(numbers[1].substring(numbers[1].toString().indexOf("-") +1));

					}else {
						txtCode1.setText(" ");
						txtNumber1.setText(" ");
						txtCode.setText(" ");
						txtNumber.setText(" ");
					}
				}
			}



		}
		Table table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.CHECK );
		table.setHeaderVisible(true);
		String[] titles = {"Groups"};

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setWidth(200);
			column.setText(titles[loopIndex]);
		}		
		load("load_groups");

		for (int loopIndex = 0; loopIndex < ListGroups.getList().size(); loopIndex++) {

			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(0, ListGroups.getList().get(loopIndex).toString());

		}
		for (int loopIndex = 0; loopIndex < 1; loopIndex++) {
			table.getColumn(loopIndex).pack();
		}
		List<String> list = new ArrayList<String>();
		contacts_Groups = new Contacts_Groups(list);
		table.setBounds(276, 339, 204, 111);
		table.addListener(SWT.Selection, new Listener() {			
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {     	
					String grp = event.item.toString().replace("TableItem {", "");
					String grp1 = grp.replace("}", "");
					list.add(grp1);
					contacts_Groups.setList(list);
				}
			}



		});


		Contacts contact = new Contacts(txtFN.getText(), txtLN.getText(), txtCT.getText());
		DbAcces db = new DbAcces();
		id=db.getID(contact);


		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("update_contact");
			}
		});
		btnSave.setText("Save");
		btnSave.setBounds(266, 461, 90, 30);

		Button btnCanel = new Button(shell, SWT.NONE);
		btnCanel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCanel.setText("Canel");
		btnCanel.setBounds(396, 461, 90, 30);
	}
	public void getContact(Contacts ct) {
		contacts = new Contacts(ct.getList());
		contacts.setList(ct.getList());
		load("update_contact");
		open();

	}

	@Override
	public void onClick(String action) {
		if (action.equals("update_contact")){
			List<String> list = new ArrayList<String>();
			Contacts contact = new Contacts(txtFN.getText(), txtLN.getText(), txtCT.getText());
			if (txtCode.getText().length() != 0) {
				list.add(txtCode.getText()+txtNumber.getText());
			}
			if(txtCode1.getText().length() != 0) {
				list.add(txtCode1.getText()+txtNumber1.getText());
			}
			if(txtCode2.getText().length() != 0) {
				list.add(txtCode2.getText()+txtNumber2.getText());
			}

			PhoneNumbers phonenumber = new PhoneNumbers(list);
			phonenumber.setList(list);
			contact.setFirstname(txtFN.getText());
			contact.setLastname(txtLN.getText());
			contact.setCity(txtCT.getText());			
			DbAcces db = new DbAcces();

			db.updateContacts(id,contact, phonenumber,contacts_Groups);
		}

	}

	@Override
	public void load(String action) {
		List<String> list = new ArrayList<String>();
		List<String> list_groups = new ArrayList<String>();
		if (action.equals("update_contact")){
			DbAcces db = new DbAcces();
			list=db.View_detail_contact(contacts);
			contacts = new Contacts(list); 
			contacts.setList(list);
			numbers = new String[contacts.getList().size()];
			for (int i =0;i<contacts.getList().size();i++) {
				if (i == 0) {
					String x = contacts.getList().get(0).toString();
					first_name = x.substring(0, x.indexOf("-"));
					last_name = x.substring(x.indexOf("-") + 1,x.indexOf("/"));
					city = x.substring(x.indexOf("/") +1);

				}
				else {

					numbers[i] =contacts.getList().get(i).toString();

				}
			}

			list_groups=db.View_detail_contact_group(contacts.getList().get(0).toString(), contacts_Groups);
			contacts_Groups = new Contacts_Groups(list_groups);
			contacts_Groups.setList(list_groups);
		} 
		if (action.equals("load_groups")){
			DbAcces db = new DbAcces();
			list = db.LoadData("load_groups");
			ListGroups = new ListGroups(list);
			ListGroups.setList(list);

		}

	}
}
