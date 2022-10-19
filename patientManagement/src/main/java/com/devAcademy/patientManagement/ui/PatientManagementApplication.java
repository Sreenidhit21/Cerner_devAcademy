/**
 * 
 */
package com.devAcademy.patientManagement.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

/**
 * @author ST105714
 *
 *         SWT methods for PatientManagementApplication
 *
 */
public class PatientManagementApplication {

	private static Text text;
	private static Table table;

	/**
	 * 
	 */
	public PatientManagementApplication() {
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(650, 500);
		shell.setText("Patient Management Applications");

		Button btnCreatePatient = new Button(shell, SWT.PUSH);
		btnCreatePatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

			}
		});
		btnCreatePatient.setBounds(32, 21, 96, 25);
		btnCreatePatient.setText("Create Patient");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(285, 96, 148, 21);

		Label lblSearchPatient = new Label(shell, SWT.NONE);
		lblSearchPatient.setBounds(10, 67, 349, 15);
		lblSearchPatient.setText("Search Patient by Patient ID or Patient name or Patient Govt ID");

		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setBounds(440, 94, 75, 25);
		btnSearch.setText("Search");

		Button btnUpdatePatient = new Button(shell, SWT.NONE);
		btnUpdatePatient.setEnabled(false);
		btnUpdatePatient.setBounds(162, 21, 110, 25);
		btnUpdatePatient.setText("Update Patient");

		Button btnDeletePatient = new Button(shell, SWT.NONE);
		btnDeletePatient.setEnabled(false);
		btnDeletePatient.setBounds(306, 21, 98, 25);
		btnDeletePatient.setText("Delete Patient");

		Button btnPatientId = new Button(shell, SWT.RADIO);
		btnPatientId.setBounds(10, 98, 75, 16);
		btnPatientId.setText("Patient ID");

		Button btnPatientName = new Button(shell, SWT.RADIO);
		btnPatientName.setBounds(91, 98, 98, 16);
		btnPatientName.setText("Patient Name");

		Button btnGovtId = new Button(shell, SWT.RADIO);
		btnGovtId.setBounds(195, 98, 66, 16);
		btnGovtId.setText("Govt ID");

		table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setBounds(10, 133, 500, 100);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn id = new TableColumn(table, SWT.LEFT);
		id.setWidth(100);
		id.setText("ID");

		TableColumn name = new TableColumn(table, SWT.LEFT);
		name.setWidth(100);
		name.setText("Name");

		TableColumn dateOfBirth = new TableColumn(table, SWT.LEFT);
		dateOfBirth.setWidth(100);
		dateOfBirth.setText("Date Of Birth");

		shell.open();
		shell.layout();
		// execute the loop as long as the window is open
		while (!shell.isDisposed()) { 
			if (!display.readAndDispatch()) { // display.readAndDispatch() - read the next OS event and dispatches to
												// SWT
				// sleep until next OS events available
				display.sleep(); 
			}
		}
	}

}
