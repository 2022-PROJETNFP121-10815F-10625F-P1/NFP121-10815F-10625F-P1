package View;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Control.Actions;
import Control.DbAcces;
import Model.Contacts;
import Model.Contacts_Groups;
import Model.PhoneNumbers;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ViewContact implements Actions{

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
	private List<String> list_ct; 
	private Contacts contacts;
	private Contacts_Groups contacts_Groups;
	private PhoneNumbers phoneNumbers;
	private String first_name,last_name,city="";
	private String[] numbers ;

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
		lblNewContact.setBounds(10, 208, 178, 34);
		lblNewContact.setText("View Contacts");

		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setText("First Name");
		lblFirstName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblFirstName.setBounds(219, 60, 94, 20);

		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setText("Last Name");
		lblLastName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblLastName.setBounds(219, 92, 84, 20);

		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setText("City");
		lblCity.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblCity.setBounds(219, 124, 84, 20);

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
		lblContactGroup.setText("Contact Group");
		lblContactGroup.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblContactGroup.setBounds(287, 328, 197, 20);

		Button btnCanel = new Button(shell, SWT.NONE);
		btnCanel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCanel.setText("Canel");
		btnCanel.setBounds(438, 449, 90, 30);

		txtFN = new Text(shell, SWT.BORDER);
		txtFN.setEditable(false);
		txtFN.setText(first_name);
		txtFN.setBounds(323, 58, 219, 26);

		txtLN = new Text(shell, SWT.BORDER);
		txtLN.setEditable(false);
		txtLN.setBounds(323, 90, 219, 26);
		txtLN.setText(last_name);

		txtCT = new Text(shell, SWT.BORDER);
		txtCT.setEditable(false);
		txtCT.setBounds(323, 122, 219, 26);
		txtCT.setText(city);

		txtNumber = new Text(shell, SWT.BORDER);
		txtNumber.setEditable(false);
		txtNumber.setBounds(353, 216, 175, 26);

		txtNumber1 = new Text(shell, SWT.BORDER);
		txtNumber1.setEditable(false);
		txtNumber1.setBounds(353, 248, 175, 26);

		txtNumber2 = new Text(shell, SWT.BORDER);
		txtNumber2.setEditable(false);
		txtNumber2.setBounds(353, 280, 175, 26);

		txtCode2 = new Text(shell, SWT.BORDER);
		txtCode2.setEditable(false);
		txtCode2.setBounds(276, 280, 42, 26);

		txtCode1 = new Text(shell, SWT.BORDER);
		txtCode1.setEditable(false);
		txtCode1.setBounds(276, 248, 42, 26);

		txtCode = new Text(shell, SWT.BORDER);
		txtCode.setEditable(false);
		txtCode.setBounds(276, 216, 42, 26);

		for (int a = 0 ; a<numbers.length;a++) {

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
			if (numbers.length > 2){
				if (numbers[2] != null) {
					txtCode.setText(numbers[2].substring(0,numbers[2].toString().indexOf("-")));
					txtNumber.setText(numbers[2].substring(numbers[2].toString().indexOf("-") +1));

				}else {
					txtCode.setText(" ");
					txtNumber.setText(" ");
				}				
			}

		}

		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(213, 58, 0, 421);

		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(194, 45, 13, 409);
		
 
		
 
		
		int r = 362;
		 
		 
		
		for (int i =0;i<contacts_Groups.getList().size();i++) {
			 
			Button btnChkbox = new Button(shell, SWT.CHECK);
			btnChkbox.setBounds(294, r, 111, 20);
			btnChkbox.setText(contacts_Groups.getList().get(i).toString());
			btnChkbox.setEnabled(false);
			btnChkbox.setSelection(true);
			r += 20;
		}
		
 

	}
	public void getContact(Contacts ct) {
		contacts = new Contacts(ct.getList());
		contacts.setList(ct.getList());
		load("View_detail_contact");
		open();
	}

	@Override
	public void onClick(String action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String action) {
		List<String> list = new ArrayList<String>();
		List<String> list_groups = new ArrayList<String>();
		if (action.equals("View_detail_contact")){
			DbAcces db = new DbAcces();
			//System.out.println("test " + contacts.getList());
			list=db.View_detail_contact(contacts);
			contacts = new Contacts(list); 
			contacts.setList(list);
			System.out.println(contacts.getList());
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


	}
}
