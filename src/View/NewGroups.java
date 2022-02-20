package View;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import Control.Actions;
import Control.DbAcces;
import Model.Contacts;
import Model.ListGroups;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class NewGroups implements Actions {

	protected Shell shell;
	private Text txtGrpName;
	private Text txtDescription;
	private Table table;
	private Label lblerror;
	
	
 

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
		lblNewContact.setBounds(10, 193, 206, 34);
		lblNewContact.setText("Add New Group");
		
		txtGrpName = new Text(shell, SWT.BORDER);
		txtGrpName.setBounds(352, 57, 225, 26);
		
		txtDescription = new Text(shell, SWT.BORDER);
		txtDescription.setBounds(352, 89, 225, 26);
		
		Label lblGroupName = new Label(shell, SWT.NONE);
		lblGroupName.setText("Group Name");
		lblGroupName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblGroupName.setBounds(242, 59, 104, 20);
		
		Label lblDescription = new Label(shell, SWT.NONE);
		lblDescription.setText("Description");
		lblDescription.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblDescription.setBounds(242, 91, 104, 20);
		

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("insert_group");
			}
		});
		btnSave.setText("Save");
		btnSave.setBounds(242, 414, 90, 30);
		
		Button btnCanel = new Button(shell, SWT.NONE);
		btnCanel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCanel.setText("Canel");
		btnCanel.setBounds(458, 414, 90, 30);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(221, 45, 2, 447);
		
		lblerror = new Label(shell, SWT.NONE);
		lblerror.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblerror.setBounds(228, 472, 70, 20);
		lblerror.setText(" ");
		

     
        

	}

	@Override
	public void onClick(String action) {
		if (action.equals("insert_group")){
		if (txtGrpName.getText().length() != 0) {
			if (txtDescription.getText().length() != 0) {
			      ListGroups ls = new ListGroups();
			      ls.setGroup(txtGrpName.getText());
			      ls.setDescription(txtDescription.getText());
			      DbAcces db = new DbAcces();
			      db.insertgroups(ls);
			      shell.close();
			      
			}
		}else {
			lblerror.setText("you should write description for this group");
		}

		}
			 

		
	}

	@Override
	public void load(String action) {
	 
		 
		
	}
}
