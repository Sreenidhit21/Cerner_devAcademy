/**
 * 
 */
package com.devAcademy.patientManagement.ui;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.devAcademy.patientManagement.entity.AddressEntity;
import com.devAcademy.patientManagement.entity.GovtIdEntity;
import com.devAcademy.patientManagement.entity.PatientEntity;
import com.devAcademy.patientManagement.httpClient.PatientHttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ST105714
 *
 *         SWT methods for PatientManagementApplication
 *
 */
public class PatientManagementApplication {

	private static Text text;
	private static Tree tree;
	private static TreeItem selectedItem;
	private static Display display;

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

		display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(650, 500);
		shell.setText("Patient Management Applications");

		setContentOnMainShell(shell);

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

	private static void setContentOnMainShell(Shell shell) {
		Button btnCreatePatient = new Button(shell, SWT.PUSH);

		Button btnUpdatePatient = new Button(shell, SWT.PUSH);
		btnUpdatePatient.setEnabled(false);

		Button btnViewPatient = new Button(shell, SWT.PUSH);
		btnViewPatient.setEnabled(false);

		Button btnDeletePatient = new Button(shell, SWT.PUSH);
		btnDeletePatient.setEnabled(false);

		btnCreatePatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tree.removeAll();
				btnUpdatePatient.setEnabled(false);
				btnDeletePatient.setEnabled(false);
				btnViewPatient.setEnabled(false);
				PatientEntity patientEntity = new PatientEntity();
				CreateUpdatePatient.setContentOnCreateUpdateShell(display, patientEntity, false);

			}
		});
		btnCreatePatient.setBounds(32, 21, 96, 25);
		btnCreatePatient.setText("Create Patient");

		btnUpdatePatient.setBounds(162, 21, 110, 25);
		btnUpdatePatient.setText("Update Patient");
		btnUpdatePatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				PatientEntity patientEntity = treeItemToPatientEntityMapping();
				tree.removeAll();
				btnUpdatePatient.setEnabled(false);
				btnDeletePatient.setEnabled(false);
				btnViewPatient.setEnabled(false);
				CreateUpdatePatient.setContentOnCreateUpdateShell(display, patientEntity, false);
			}

		});

		btnViewPatient.setBounds(426, 21, 100, 25);
		btnViewPatient.setText("View Patient");
		btnViewPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				PatientEntity patientEntity = treeItemToPatientEntityMapping();
				CreateUpdatePatient.setContentOnCreateUpdateShell(display, patientEntity, true);
			}

		});

		btnDeletePatient.setBounds(306, 21, 98, 25);
		btnDeletePatient.setText("Delete Patient");
		btnDeletePatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				deletePatientById();
				btnDeletePatient.setEnabled(false);
				btnUpdatePatient.setEnabled(false);
				btnViewPatient.setEnabled(false);
			}

		});

		text = new Text(shell, SWT.BORDER);
		text.setBounds(285, 96, 148, 21);

		Label lblSearchPatient = new Label(shell, SWT.NONE);
		lblSearchPatient.setBounds(10, 67, 349, 15);
		lblSearchPatient.setText("Search Patient by Patient ID or Patient name or Patient Govt ID");

		Button btnPatientId = new Button(shell, SWT.RADIO);
		btnPatientId.setBounds(10, 98, 75, 16);
		btnPatientId.setText("Patient ID");

		Button btnPatientName = new Button(shell, SWT.RADIO);
		btnPatientName.setBounds(91, 98, 98, 16);
		btnPatientName.setText("Patient Name");

		Button btnGovtId = new Button(shell, SWT.RADIO);
		btnGovtId.setBounds(195, 98, 66, 16);
		btnGovtId.setText("Govt ID");

		Button btnSearch = new Button(shell, SWT.PUSH);
		btnSearch.setBounds(440, 94, 75, 25);
		btnSearch.setText("Search");

		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (!btnPatientId.getSelection() && !btnGovtId.getSelection() && !btnPatientName.getSelection()) {
					openDialog("select Patient ID or Govt ID or Patient Name ");

				} else {
					tree.removeAll();
					btnDeletePatient.setEnabled(false);
					btnUpdatePatient.setEnabled(false);
					btnViewPatient.setEnabled(false);
					if (btnPatientId.getSelection()) {
						getPatientById();
					} else if (btnGovtId.getSelection()) {
						getPatientByGovtId();
					} else if (btnPatientName.getSelection()) {
						getPatientByName();
					}
				}
			}

		});

		tree = new Tree(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		tree.setBounds(10, 133, 500, 300);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		setTreeColumn();
		tree.addListener(SWT.MouseDown, event -> {
			Point point = new Point(event.x, event.y);
			selectedItem = tree.getItem(point);
			if (selectedItem != null && tree.getSelectionCount() > 0) {
				btnDeletePatient.setEnabled(true);
				btnUpdatePatient.setEnabled(true);
				btnViewPatient.setEnabled(true);
			} else {
				btnDeletePatient.setEnabled(false);
				btnUpdatePatient.setEnabled(false);
				btnViewPatient.setEnabled(false);
			}
		});
	}

	public static PatientEntity treeItemToPatientEntityMapping() {
		PatientEntity patientEntity = new PatientEntity();

		HttpResponse<String> response;
		try {
			response = PatientHttpClient.getPatientDetailsById(Long.valueOf(selectedItem.getText(0)));

			if (response.statusCode() == 200) {
				ObjectMapper mapper = new ObjectMapper();
				patientEntity = mapper.readValue(response.body(), new TypeReference<PatientEntity>() {
				});
			}
		} catch (NumberFormatException | IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return patientEntity;
	}

	private static void deletePatientById() {
		String id = selectedItem.getText(0);

		try {
			HttpResponse<String> response = PatientHttpClient.deletePatientDetails(Long.valueOf(id));
			if (response.statusCode() == 500) {
				openDialog("patient not found");
			} else {
				openDialog("patient with id " + id + " deleted successfully");
				tree.removeAll();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void setTreeColumn() {
		TreeColumn id = new TreeColumn(tree, SWT.LEFT);
		id.setText("ID");
		id.setWidth(50);

		TreeColumn name = new TreeColumn(tree, SWT.LEFT);
		name.setText("Name");
		name.setWidth(150);

		TreeColumn dateOfBirth = new TreeColumn(tree, SWT.LEFT);
		dateOfBirth.setText("Date Of Birth");
		dateOfBirth.setWidth(150);

		TreeColumn telephoneNumber = new TreeColumn(tree, SWT.LEFT);
		telephoneNumber.setText("Telephone Number");
		telephoneNumber.setWidth(150);

		TreeColumn addressType = new TreeColumn(tree, SWT.LEFT);
		addressType.setText("Address Type");
		addressType.setWidth(100);

		TreeColumn houseNumber = new TreeColumn(tree, SWT.LEFT);
		houseNumber.setText("House Number");
		houseNumber.setWidth(100);

		TreeColumn city = new TreeColumn(tree, SWT.LEFT);
		city.setText("City");
		city.setWidth(100);

		TreeColumn state = new TreeColumn(tree, SWT.LEFT);
		state.setText("State");
		state.setWidth(100);

		TreeColumn country = new TreeColumn(tree, SWT.LEFT);
		country.setText("Country");
		country.setWidth(100);

		TreeColumn pin = new TreeColumn(tree, SWT.LEFT);
		pin.setText("PIN");
		pin.setWidth(100);

		TreeColumn govtId = new TreeColumn(tree, SWT.LEFT);
		govtId.setText("Govt ID");
		govtId.setWidth(100);

		TreeColumn govtIdType = new TreeColumn(tree, SWT.LEFT);
		govtIdType.setText("Govt ID Type");
		govtIdType.setWidth(150);

		TreeColumn reasonForNotSharingId = new TreeColumn(tree, SWT.LEFT);
		reasonForNotSharingId.setText("Reason For Not Sharing ID");
		reasonForNotSharingId.setWidth(250);

	}

	private static void getPatientById() {
		if (text.getText().isBlank()) {
			openDialog("Please type patient ID");
		} else {
			try {
				HttpResponse<String> response = PatientHttpClient.getPatientDetailsById(Long.valueOf(text.getText()));
				if (response.statusCode() == 200) {
					populatePatientTree(response);
				} else {
					openDialog("patient not found");
				}

			} catch (NumberFormatException e1) {
				openDialog("Patient ID should contain only numbers");
				e1.printStackTrace();
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void getPatientByGovtId() {
		if (text.getText().isBlank()) {
			openDialog("Please type Govt ID");
		} else {
			try {
				HttpResponse<String> response = PatientHttpClient.getPatientDetailsByGovtId(text.getText());
				if (response.statusCode() == 500) {
					openDialog("patient not found");
				} else {
					populatePatientTree(response);
				}

			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void getPatientByName() {
		if (text.getText().isBlank()) {
			openDialog("Please type Patient name");
		} else {
			try {
				HttpResponse<String> response = PatientHttpClient.getPatientDetailsByName(text.getText());
				if (response.statusCode() == 500) {
					openDialog("patient not found");
				} else {
					populatePatientTreeForMultipleItem(response);
				}

			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void populatePatientTreeForMultipleItem(HttpResponse<String> response)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<PatientEntity> patientEnties = mapper.readValue(response.body(), new TypeReference<List<PatientEntity>>() {
		});
		for (PatientEntity patientEntity : patientEnties) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText(getPatientItem(patientEntity, 0));
			TreeItem subItem = new TreeItem(item, SWT.NONE);
			subItem.setText(getPatientItem(patientEntity, 1));
		}
	}

	public static void populatePatientTree(HttpResponse<String> response)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		PatientEntity patientEntity = mapper.readValue(response.body(), new TypeReference<PatientEntity>() {
		});
		TreeItem item = new TreeItem(tree, SWT.NONE);
		item.setText(getPatientItem(patientEntity, 0));
		TreeItem subItem = new TreeItem(item, SWT.NONE);
		subItem.setText(getPatientItem(patientEntity, 1));

	}

	private static String[] getPatientItem(PatientEntity patient, int i) {
		List<AddressEntity> address = patient.getAddress();
		List<Long> phone = patient.getTelephoneNumber();
		List<GovtIdEntity> govtIds = patient.getGovtIds();
		return new String[] { i == 0 ? patient.getId().toString() : null, i == 0 ? patient.getName() : null,
				i == 0 ? (patient.getDateOfBirth() != null ? patient.getDateOfBirth().toString() : null) : null,
				phone != null ? (phone.size() >= i + 1 && phone.get(i) != null ? phone.get(i).toString() : null) : null,
				address != null ? (address.size() >= i + 1 && address.get(i) != null
						? (i == 0 ? "Current Address" : "Permanent Address")
						: null) : null,
				address != null
						? (address.size() >= i + 1 && address.get(i) != null ? address.get(i).getHouseNumber() : null)
						: null,
				address != null ? (address.size() >= i + 1 && address.get(i) != null ? address.get(i).getCity() : null)
						: null,
				address != null ? (address.size() >= i + 1 && address.get(i) != null ? address.get(i).getState() : null)
						: null,
				address != null
						? (address.size() >= i + 1 && address.get(i) != null ? address.get(i).getCountry() : null)
						: null,
				address != null ? (address.size() >= i + 1 && address.get(i) != null ? address.get(i).getPin() : null)
						: null,
				govtIds != null
						? (govtIds.size() >= i + 1 && govtIds.get(i) != null ? govtIds.get(i).getGovtId() : null)
						: null,
				govtIds != null
						? (govtIds.size() >= i + 1 && govtIds.get(i) != null ? govtIds.get(i).getGovtIdType() : null)
						: null,
				govtIds != null
						? (govtIds.size() >= i + 1 && govtIds.get(i) != null ? govtIds.get(i).getReasonForNotSharingId()
								: null)
						: null,

		};
	}

	public static void openDialog(String message) {
		Shell dialog = new Shell();
		dialog.setText("Alert");
		dialog.setSize(350, 100);
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = 10;
		formLayout.marginHeight = 10;
		formLayout.spacing = 10;
		dialog.setLayout(formLayout);

		Label label = new Label(dialog, SWT.NONE);
		label.setText(message);
		FormData data = new FormData();
		label.setLayoutData(data);

		Button cancel = new Button(dialog, SWT.PUSH);
		cancel.setText("Close");
		data = new FormData();
		data.width = 60;
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		cancel.setLayoutData(data);
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				dialog.close();
			}
		});

		dialog.open();
	}

}
