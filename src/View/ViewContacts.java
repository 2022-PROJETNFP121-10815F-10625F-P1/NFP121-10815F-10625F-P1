package View;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Control.*;
import Model.Contacts;
 

import java.util.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
 

public class ViewContacts implements Actions {

	protected Shell shell;
	private Text txtsearch;
	private Contacts contacts;
	private List<String> list_ct; 
	Label lblwarning;
	/**
	 * Launch the application.
	 * @param args
	 */
 

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		load("view_contacts");
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
		org.eclipse.swt.widgets.List list_1 = new org.eclipse.swt.widgets.List(shell, SWT.BORDER |SWT.V_SCROLL | SWT.H_SCROLL | SWT.SELECTED );

		
		Label lblGestionDesContacts = new Label(shell, SWT.NONE);
		lblGestionDesContacts.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.BOLD));
		lblGestionDesContacts.setBounds(177, 22, 232, 29);
		lblGestionDesContacts.setText("Gestion des contacts");
		
		lblwarning = new Label(shell, SWT.NONE);
		lblwarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblwarning.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblwarning.setBounds(23, 471, 553, 20);
		
		Button btnSortFN = new Button(shell, SWT.NONE);
		btnSortFN.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_1.removeAll();
				load("Sort_ByFN");
				for (String element : contacts.getList()) {
					list_1.add(element);
				}
			}
		});
		btnSortFN.setBounds(23, 157, 148, 30);
		btnSortFN.setText("Sort by First Name");

		Button btnSortLN = new Button(shell, SWT.NONE);
		btnSortLN.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_1.removeAll();
				load("Sort_ByLN");
				for (String element : contacts.getList()) {
					list_1.add(element);
				}
			}
		});
		btnSortLN.setText("Sort by Last Name");
		btnSortLN.setBounds(23, 205, 148, 30);
		
		
		Button btnSortCT = new Button(shell, SWT.NONE);
		btnSortCT.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_1.removeAll();
				load("Sort_ByCT");
				for (String element : contacts.getList()) {
					list_1.add(element);
				}
			}
		});
		btnSortCT.setText("Sort by City");
		btnSortCT.setBounds(23, 255, 148, 30);

		Button btnAdd_contacts = new Button(shell, SWT.NONE);
		btnAdd_contacts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
              NewContact contact = new NewContact(); 
              contact.open();
			}
		});
		btnAdd_contacts.setText("Add New Contact");
		btnAdd_contacts.setBounds(23, 306, 148, 30);

		
		Button btnViewContact = new Button(shell, SWT.NONE);
		btnViewContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("View_detail_contact");
			}
		});
		btnViewContact.setBounds(233, 435, 90, 30);
		btnViewContact.setText("View");		

		Button btnUpdateContact = new Button(shell, SWT.NONE);
		btnUpdateContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("update_contact");
			}
		});
		btnUpdateContact.setText("Update");
		btnUpdateContact.setBounds(359, 435, 90, 30);
 
		
		Button btnDeleteContact = new Button(shell, SWT.NONE);
		btnDeleteContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("delete_contact");
				list_1.removeAll();
				load("view_contacts");
				for (String element : contacts.getList()) {
					list_1.add(element);
				}
			}
		});
		btnDeleteContact.setText("Delete");
		btnDeleteContact.setBounds(487, 435, 90, 30);
 
		
		Label lblContacts = new Label(shell, SWT.NONE);
		lblContacts.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD));
		lblContacts.setBounds(36, 85, 130, 37);
		lblContacts.setText("Contacts");
		
 
		
		Label lblSearch = new Label(shell, SWT.NONE);
		lblSearch.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblSearch.setBounds(220, 85, 60, 20);
		lblSearch.setText("Search");
		
		txtsearch = new Text(shell, SWT.BORDER);
		txtsearch.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		txtsearch.setBounds(286, 83, 242, 26);
		
 
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(183, 57, 31, 408);
		
		
		list_1.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		list_1.setBounds(237, 146, 332, 268);
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String search_name=txtsearch.getText();
				if (search_name.equals("")) {
					lblwarning.setText("you should write a name to search");
				}else {
					List<String> list = new ArrayList<String>();
					DbAcces db = new DbAcces();			 
					list=db.search_name(search_name);
					contacts = new Contacts(list);
					contacts.setList(list);
					list_1.removeAll();
					for (String element : contacts.getList()) {
						list_1.add(element);
					}

					
				}
				
			}
		});
		btnOk.setText("OK");
		btnOk.setBounds(537, 81, 39, 30);
		

		
		for (String element : contacts.getList()) {
			list_1.add(element);
		}
		
		list_1.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event e) {
		        int x = 0;
		        list_ct = new   ArrayList<String>();
		        int[] selection = list_1.getSelectionIndices();
		        for (int i = 0; i < selection.length; i++) {
		          x += selection[i] ;
		        }   
		        list_ct.add(list_1.getItem(x) );
		        lblwarning.setText("");
 
		      }
		    });
 
	}

	@Override
	public void onClick(String action) {
		if (action.equals("View_detail_contact")){
			if (list_ct ==  null) {
				lblwarning.setText("you should select a contact from the list");
			}else {
				contacts = new Contacts(list_ct);
				contacts.setList(list_ct);
				ViewContact ViewContact = new ViewContact();
				ViewContact.getContact(contacts);
				
			}
			
		}
		
		
 
		
		
		if (action.equals("update_contact")){
			if (list_ct ==  null) {
				lblwarning.setText("you should select a contact from the list");
			}else {
				contacts = new Contacts(list_ct);
				contacts.setList(list_ct);
				UpdateContacts UpdateContacts = new UpdateContacts();
				UpdateContacts.getContact(contacts);
				
			}
			
		}
		if (action.equals("delete_contact")){
			if (list_ct ==  null) {
				lblwarning.setText("you should select a contact from the list");
			}else {
				contacts = new Contacts(list_ct);
				contacts.setList(list_ct);
				String x = contacts.getList().toString().replace("[", "'");
				String name =x.replace("]", "'");
				DbAcces db = new DbAcces();
				db.delete_contacts(name);
					
				
				
			}
			
		}		
		
		
		
		
	}

	@Override
	public void load(String action) {
		List<String> list = new ArrayList<String>();
		if (action.equals("view_contacts")){
			DbAcces db = new DbAcces();
			list=db.LoadData("view_contacts");
			contacts = new Contacts(list);
			contacts.setList(list);

		}	

		if (action.equals("Sort_ByFN")){
			DbAcces db = new DbAcces();
			list=db.LoadData("Sort_ByFN");
			contacts = new Contacts(list);
			contacts.setList(list);

		}	
		if (action.equals("Sort_ByLN")){
			DbAcces db = new DbAcces();
			list=db.LoadData("Sort_ByLN");
			contacts = new Contacts(list);
			contacts.setList(list);

		}	
		if (action.equals("Sort_ByCT")){
			DbAcces db = new DbAcces();
			list=db.LoadData("Sort_ByCT");
			contacts = new Contacts(list);
			contacts.setList(list);

		}	
 		
	}
}
