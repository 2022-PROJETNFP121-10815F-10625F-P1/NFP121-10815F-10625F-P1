package View;

import java.sql.SQLException;
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

public class UpdateGroups implements Actions{

	protected Shell shell;
	private Text txt_des;
	private Text txt_grp;
	private String description ="";
	private String grp_name = "";
	private ListGroups L_grps;
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
		lblNewContact.setBounds(10, 140, 182, 34);
		lblNewContact.setText("Update Group");
		
		Label lblGroupName = new Label(shell, SWT.NONE);
		lblGroupName.setText("Group Name");
		lblGroupName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblGroupName.setBounds(233, 202, 104, 20);
		
		Label lblDescription = new Label(shell, SWT.NONE);
		lblDescription.setText("Description");
		lblDescription.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		lblDescription.setBounds(233, 234, 104, 20);
		
		txt_des = new Text(shell, SWT.BORDER);
		txt_des.setBounds(343, 232, 225, 26);
		txt_des.setText(description);
		
		
		txt_grp = new Text(shell, SWT.BORDER);
		txt_grp.setBounds(343, 200, 225, 26);
		txt_grp.setText(grp_name);
		
		
		ListGroups ListGroups = new ListGroups(txt_grp.getText(), txt_des.getText());
		DbAcces db = new DbAcces();
		id=db.getID_group(ListGroups);
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClick("update_group");
			}
		});
		btnSave.setText("Save");
		btnSave.setBounds(233, 412, 90, 30);
		
		Button btnCanel = new Button(shell, SWT.NONE);
		btnCanel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCanel.setText("Canel");
		btnCanel.setBounds(440, 412, 90, 30);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(198, 46, 27, 438);
 

		
	}
	
	public void getGroup(ListGroups ListGroups) {
		L_grps = new ListGroups(ListGroups.getList());
		L_grps.setList(ListGroups.getList());
		 
		load("update_group");
		open();

	}

	@Override
	public void onClick(String action) {
		if (action.equals("update_group")){
			L_grps = new ListGroups(txt_grp.getText(),txt_des.getText());
					
			DbAcces db = new DbAcces();
			db.updategroup(id,L_grps);
			shell.close();
		
		}
		
	}

	@Override
	public void load(String action) {
		
		if (action.equals("update_group")){
			DbAcces db = new DbAcces();
			 
			description = db.getDescription(L_grps);
			if (description == null) {
				description = "";
			}
	    	String x = L_grps.getList().toString().replace("[", "");
	    	grp_name = x.replace("]", "");
	    	L_grps = new ListGroups(grp_name,description);
	    	
	     
			
			
		}
		
	}
	

}
