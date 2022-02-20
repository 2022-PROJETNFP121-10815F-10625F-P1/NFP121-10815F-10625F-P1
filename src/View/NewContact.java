package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import org.eclipse.swt.SWT;

import Control.*;


import Model.*;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
 
import org.eclipse.swt.widgets.Listener;
public class NewContact implements Actions {

	protected Shell shell;
	private Text txtFn;
	private Text txtLn;
	private Text txtCity;
	private Text txtCode;
	private Text txtNumber;
	private Text txtCode1;
	private Text txtNumber1;
	private Text txtCode2;
	private Text txtNumber2;
	private ListGroups ListGroups;
	private Contacts_Groups Contacts_Groups;


 
	
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
		lblGestionDesContacts.setBounds(177, 22, 232, 29);
		lblGestionDesContacts.setText("Gestion des contacts");

		Label lblNewContact = new Label(shell, SWT.NONE);
		lblNewContact.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewContact.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lblNewContact.setBounds(10, 201, 167, 34);
		lblNewContact.setText("New Contact");

		txtFn = new Text(shell, SWT.BORDER);
		txtFn.setBounds(324, 65, 219, 26);

		txtLn = new Text(shell, SWT.BORDER);
		txtLn.setBounds(324, 97, 219, 26);

		txtCity = new Text(shell, SWT.BORDER);
		txtCity.setBounds(324, 129, 219, 26);

		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setText("First Name");
		lblFirstName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblFirstName.setBounds(224, 67, 94, 20);

		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setText("Last Name");
		lblLastName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblLastName.setBounds(224, 99, 84, 20);

		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setText("City");
		lblCity.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblCity.setBounds(224, 127, 84, 20);



		Label lblPhoneNumbers = new Label(shell, SWT.NONE);
		lblPhoneNumbers.setText("Phone numbers");
		lblPhoneNumbers.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblPhoneNumbers.setBounds(301, 161, 141, 20);

		Label lblAdd = new Label(shell, SWT.NONE);
		lblAdd.setText("Add the contacts to the group");
		lblAdd.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
		lblAdd.setBounds(224, 313, 248, 20);

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("insert_contact");

			}
		});
		btnSave.setBounds(238, 461, 90, 30);
		btnSave.setText("Save");

		Button btnCanel = new Button(shell, SWT.NONE);
		btnCanel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCanel.setText("Canel");
		btnCanel.setBounds(368, 461, 90, 30);

		txtCode = new Text(shell, SWT.BORDER);
		txtCode.setBounds(276, 209, 42, 26);

		txtNumber = new Text(shell, SWT.BORDER);
		txtNumber.setBounds(353, 209, 175, 26);

		txtCode1 = new Text(shell, SWT.BORDER);
		txtCode1.setBounds(276, 241, 42, 26);

		txtNumber1 = new Text(shell, SWT.BORDER);
		txtNumber1.setBounds(353, 241, 175, 26);

		txtCode2 = new Text(shell, SWT.BORDER);
		txtCode2.setBounds(276, 273, 42, 26);

		txtNumber2 = new Text(shell, SWT.BORDER);
		txtNumber2.setBounds(353, 273, 175, 26);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(255, 187, 94, 20);
		lblNewLabel.setText("Region Code");

		Label lblPhoneNumber = new Label(shell, SWT.NONE);
		lblPhoneNumber.setText("Phone Number");
		lblPhoneNumber.setBounds(389, 187, 111, 20);


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
		Contacts_Groups = new Contacts_Groups(list);
		table.setBounds(276, 339, 204, 111);
		table.addListener(SWT.Selection, new Listener() {			
		      public void handleEvent(Event event) {
		        if (event.detail == SWT.CHECK) {     	
		        	String grp = event.item.toString().replace("TableItem {", "");
		        	String grp1 = grp.replace("}", "");
		        	list.add(grp1);
		        	Contacts_Groups.setList(list);
		        }
		      }

		 	
			
		    });
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(185, 57, 21, 413);
	}

	@Override
	public void onClick(String action) {
		if (action.equals("insert_contact")){
			List<String> list = new ArrayList<String>();
			Contacts contact = new Contacts(txtFn.getText(), txtLn.getText(), txtCity.getText());
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
			contact.setFirstname(txtFn.getText());
			contact.setLastname(txtLn.getText());
			contact.setCity(txtCity.getText());			
			DbAcces db = new DbAcces();
			db.insertContacts(contact);
			db.insertphones(phonenumber,contact);
			db.insertContact_Groups(Contacts_Groups, contact);
		}
	}
	@Override
	public void load(String action) {
		List<String> list1 = new ArrayList<String>();
		if (action.equals("load_groups")){
			DbAcces db = new DbAcces();
			list1 = db.LoadData("load_groups");
			ListGroups = new ListGroups(list1);
			ListGroups.setList(list1);

		}
	}
}
