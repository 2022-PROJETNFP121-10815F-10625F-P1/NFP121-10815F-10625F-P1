package View;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import Control.Actions;
import Control.DbAcces;
import Model.Contacts;
import Model.ListGroups;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;


public class Groups implements Actions {

	protected Shell shell;
	private Table table;
	private Text text;
	private Text text_1;
	 
	Label lblwarning;
    
	private ListGroups ListGroups;
	private List<String> list_grp; 
	

	/**
	 * Launch the application.
	 * @param args
	 */
 

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		load("load_groups");
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
		org.eclipse.swt.widgets.List list_1 = new org.eclipse.swt.widgets.List(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		Label lblGestionDesContacts = new Label(shell, SWT.NONE);
		lblGestionDesContacts.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.BOLD));
		lblGestionDesContacts.setBounds(173, 10, 232, 29);
		lblGestionDesContacts.setText("Gestion des contacts");
		
		Label lblNewContact = new Label(shell, SWT.NONE);
		lblNewContact.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewContact.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.NORMAL));
		lblNewContact.setBounds(40, 93, 93, 34);
		lblNewContact.setText("Groups");
		
		lblwarning = new Label(shell, SWT.NONE);
		lblwarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblwarning.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblwarning.setBounds(23, 471, 553, 20);		
		
		
		Button btnAddGrp = new Button(shell, SWT.NONE);
		btnAddGrp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewGroups ngrp = new NewGroups();
				ngrp.open();
				shell.close();
			}
		});
		btnAddGrp.setBounds(10, 200, 177, 29);
		btnAddGrp.setText("Add New Group");
		
		Button btnUpdGrp = new Button(shell, SWT.NONE);
		btnUpdGrp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("update_group");
				shell.close();
			}
		});
		btnUpdGrp.setText("Update Group");
		btnUpdGrp.setBounds(305, 421, 114, 30);
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("delete_grp");
				list_1.removeAll();
				load("load_groups");
				for (String element : ListGroups.getList()) {
					list_1.add(element);
				}
			}
		});
		btnDelete.setText("Delete");
		btnDelete.setBounds(474, 421, 90, 30);
		
		Label lblListOfGroups = new Label(shell, SWT.NONE);
		lblListOfGroups.setText("List Of Groups");
		lblListOfGroups.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblListOfGroups.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblListOfGroups.setBounds(327, 68, 128, 29);
		
	 
		  
 
		

		

		 
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(205, 45, 21, 449);
		
		
		list_1.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		list_1.setBounds(232, 116, 332, 268);
		
	    


		list_1.removeAll();
		for (String element : ListGroups.getList()) {
			list_1.add(element);
		}
		list_1.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event e) {
		        int x = 0;
		        list_grp = new   ArrayList<String>();
		        int[] selection = list_1.getSelectionIndices();
		        for (int i = 0; i < selection.length; i++) {
		          x += selection[i] ;
		        }   
		        list_grp.add(list_1.getItem(x) );
		        lblwarning.setText("");

		      }
		    });
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void onClick(String action) {
		if (action.equals("update_group")){
			if (list_grp ==  null) {
				lblwarning.setText("you should select a contact from the list");
			}else {
				ListGroups = new ListGroups(list_grp);
				ListGroups.setList(list_grp);
				UpdateGroups UpdateGroups = new UpdateGroups();
				UpdateGroups.getGroup(ListGroups);
				
				
			}
			
		}
		if (action.equals("delete_grp")){
			if (list_grp ==  null) {
				lblwarning.setText("you should select a group from the list");
			}else {
			
				ListGroups ListGroups = new ListGroups(list_grp);
				
			 	DbAcces db = new DbAcces();
				int id=db.getID_groups(ListGroups);
				db.delete_group(id);
					
				
				
			}
			
		}
	}

	@Override
	public void load(String action) {
		List<String> list = new ArrayList<String>();
		if (action.equals("load_groups")){
			DbAcces db = new DbAcces();
			list=db.LoadData("load_groups");
			ListGroups = new ListGroups(list);
			ListGroups.setList(list);

		}
	}
}
