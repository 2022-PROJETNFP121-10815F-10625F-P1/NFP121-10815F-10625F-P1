package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class StartApp {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StartApp window = new StartApp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblGestionDesContacts = new Label(shell, SWT.NONE);
		lblGestionDesContacts.setText("Gestion des contacts");
		lblGestionDesContacts.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.BOLD));
		lblGestionDesContacts.setBounds(115, 45, 232, 29);
		
		Button btnAddContact = new Button(shell, SWT.NONE);
		btnAddContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ViewContacts viewcontacts = new ViewContacts();
				viewcontacts.open();
			}
		});
		btnAddContact.setBounds(26, 145, 133, 30);
		btnAddContact.setText("Contact_page");
		
		Button btnAddContact_1 = new Button(shell, SWT.NONE);
		btnAddContact_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Groups grp = new Groups();
				grp.open();
			}
		});
		btnAddContact_1.setText("Groups_page");
		btnAddContact_1.setBounds(248, 145, 133, 30);

	}
}
