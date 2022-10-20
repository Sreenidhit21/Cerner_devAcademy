/**
 * 
 */
package com.devAcademy.patientManagement.ui;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.devAcademy.patientManagement.entity.AddressEntity;
import com.devAcademy.patientManagement.entity.GovtIdEntity;
import com.devAcademy.patientManagement.entity.PatientEntity;
import com.devAcademy.patientManagement.httpClient.PatientHttpClient;

/**
 * @author ST105714
 *
 */
public class CreateUpdatePatient {
	private static Text txtName;
	private static Text txtDateOfBirth;
	private static Text txtId;
	private static Text txtPhoneNumber;
	private static Text txtReason1;
	private static Text txtGovtId1;
	private static Text txtGovtIdType1;
	private static Text txtGovtIdType;
	private static Text txtGovtId;
	private static Text txtReason;
	private static Text txtPin2;
	private static Text txtCountry2;
	private static Text txtState2;
	private static Text txtCity2;
	private static Text txtHouse2;
	private static Text txtPin;
	private static Text txtCountry;
	private static Text txtState;
	private static Text txtCity;
	private static Text txtHouseNumber;
	private static Text txtAPhoneNumber;
	private static Text cAddress;
	private static Text pAddress;
	private static Text pGovtId;
	private static Text sGovtId;

	/**
	 * 
	 */
	public CreateUpdatePatient() {
	}

	public static void setContentOnCreateUpdateShell(Display display, PatientEntity patientEntity, Boolean isView) {
		Shell shell = new Shell(display);
		shell.setSize(500, 650);
		shell.setText("Patient Details");

		Label lblPatientDetails = new Label(shell, SWT.NONE);
		lblPatientDetails.setBounds(30, 10, 172, 15);
		lblPatientDetails.setText("Patient Details");

		Label lblId = new Label(shell, SWT.NONE);
		lblId.setBounds(30, 40, 55, 15);
		lblId.setText("ID");

		txtId = new Text(shell, SWT.BORDER);
		txtId.setBounds(210, 40, 200, 19);
		txtId.setEditable(false);

		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(30, 60, 170, 15);
		lblName.setText("Name  (* required)");

		txtName = new Text(shell, SWT.BORDER);
		txtName.setBounds(210, 60, 200, 19);

		Label lblDateOfBirth = new Label(shell, SWT.NONE);
		lblDateOfBirth.setBounds(30, 80, 78, 15);
		lblDateOfBirth.setText("Date of birth");

		txtDateOfBirth = new Text(shell, SWT.BORDER);
		txtDateOfBirth.setBounds(210, 80, 200, 19);

		Label lblPhoneNumber = new Label(shell, SWT.NONE);
		lblPhoneNumber.setBounds(10, 100, 172, 15);
		lblPhoneNumber.setText("Primary phone number");

		txtPhoneNumber = new Text(shell, SWT.BORDER);
		txtPhoneNumber.setBounds(210, 100, 200, 19);

		Label lblAlternativePhoneNumber = new Label(shell, SWT.NONE);
		lblAlternativePhoneNumber.setBounds(10, 120, 149, 15);
		lblAlternativePhoneNumber.setText("Alternative phone number");

		txtAPhoneNumber = new Text(shell, SWT.BORDER);
		txtAPhoneNumber.setBounds(210, 120, 200, 19);

		Label lblPrimaryAddress = new Label(shell, SWT.NONE);
		lblPrimaryAddress.setBounds(10, 140, 117, 15);
		lblPrimaryAddress.setText("Current Address");

		cAddress = new Text(shell, SWT.BORDER);
		cAddress.setBounds(210, 140, 200, 19);
		cAddress.setVisible(false);

		Label lblHouseNumber = new Label(shell, SWT.NONE);
		lblHouseNumber.setBounds(33, 160, 94, 15);
		lblHouseNumber.setText("House number");

		txtHouseNumber = new Text(shell, SWT.BORDER);
		txtHouseNumber.setBounds(210, 160, 200, 19);

		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setBounds(33, 180, 55, 15);
		lblCity.setText("City");

		txtCity = new Text(shell, SWT.BORDER);
		txtCity.setBounds(210, 180, 200, 19);

		Label lblState = new Label(shell, SWT.NONE);
		lblState.setBounds(33, 200, 55, 15);
		lblState.setText("State");

		txtState = new Text(shell, SWT.BORDER);
		txtState.setBounds(210, 200, 200, 19);

		Label lblCountry = new Label(shell, SWT.NONE);
		lblCountry.setBounds(33, 220, 55, 15);
		lblCountry.setText("Country");

		txtCountry = new Text(shell, SWT.BORDER);
		txtCountry.setBounds(210, 220, 200, 19);

		Label lblPin = new Label(shell, SWT.NONE);
		lblPin.setBounds(33, 240, 55, 15);
		lblPin.setText("PIN");

		txtPin = new Text(shell, SWT.BORDER);
		txtPin.setBounds(210, 240, 200, 19);

		Label lblAddress2 = new Label(shell, SWT.NONE);
		lblAddress2.setBounds(10, 260, 117, 15);
		lblAddress2.setText("Permanent Address");

		pAddress = new Text(shell, SWT.BORDER);
		pAddress.setBounds(210, 260, 200, 19);
		pAddress.setVisible(false);

		Label lblHouseNumber2 = new Label(shell, SWT.NONE);
		lblHouseNumber2.setBounds(33, 280, 94, 15);
		lblHouseNumber2.setText("House number");

		txtHouse2 = new Text(shell, SWT.BORDER);
		txtHouse2.setBounds(210, 280, 200, 19);

		Label lblCity2 = new Label(shell, SWT.NONE);
		lblCity2.setBounds(33, 300, 55, 15);
		lblCity2.setText("City");

		txtCity2 = new Text(shell, SWT.BORDER);
		txtCity2.setBounds(210, 300, 200, 19);

		Label lblState2 = new Label(shell, SWT.NONE);
		lblState2.setBounds(33, 320, 55, 15);
		lblState2.setText("State");

		txtState2 = new Text(shell, SWT.BORDER);
		txtState2.setBounds(210, 320, 200, 19);

		Label lblCountry2 = new Label(shell, SWT.NONE);
		lblCountry2.setBounds(33, 340, 55, 15);
		lblCountry2.setText("Country");

		txtCountry2 = new Text(shell, SWT.BORDER);
		txtCountry2.setBounds(210, 340, 200, 19);

		Label lblPin2 = new Label(shell, SWT.NONE);
		lblPin2.setBounds(33, 360, 55, 15);
		lblPin2.setText("PIN");

		txtPin2 = new Text(shell, SWT.BORDER);
		txtPin2.setBounds(210, 360, 200, 19);

		Label lblPrimaryGovtId = new Label(shell, SWT.NONE);
		lblPrimaryGovtId.setBounds(10, 380, 200, 15);
		lblPrimaryGovtId.setText("Primary Govt ID (* required)");

		pGovtId = new Text(shell, SWT.BORDER);
		pGovtId.setBounds(210, 380, 200, 19);
		pGovtId.setVisible(false);

		Label lblGovtIdType = new Label(shell, SWT.NONE);
		lblGovtIdType.setBounds(33, 400, 75, 15);
		lblGovtIdType.setText("Govt ID type");

		txtGovtIdType = new Text(shell, SWT.BORDER);
		txtGovtIdType.setBounds(210, 400, 200, 19);

		Label lblGovtId = new Label(shell, SWT.NONE);
		lblGovtId.setBounds(33, 420, 149, 15);
		lblGovtId.setText("Govt ID number");

		txtGovtId = new Text(shell, SWT.BORDER);
		txtGovtId.setBounds(210, 420, 200, 19);

		Label lblReason = new Label(shell, SWT.NONE);
		lblReason.setBounds(33, 440, 149, 15);
		lblReason.setText("Reason for not sharing ID");

		txtReason = new Text(shell, SWT.BORDER);
		txtReason.setBounds(210, 440, 200, 19);

		Label lblSecondaryGovtId = new Label(shell, SWT.NONE);
		lblSecondaryGovtId.setBounds(10, 460, 149, 15);
		lblSecondaryGovtId.setText("Secondary Govt ID");

		sGovtId = new Text(shell, SWT.BORDER);
		sGovtId.setBounds(210, 460, 200, 19);
		sGovtId.setVisible(false);

		Label lblGovtIdType1 = new Label(shell, SWT.NONE);
		lblGovtIdType1.setBounds(33, 480, 149, 15);
		lblGovtIdType1.setText("Govt ID type");

		txtGovtIdType1 = new Text(shell, SWT.BORDER);
		txtGovtIdType1.setBounds(210, 480, 200, 19);

		Label lblGovtId1 = new Label(shell, SWT.NONE);
		lblGovtId1.setBounds(33, 500, 149, 15);
		lblGovtId1.setText("Govt ID number");

		txtGovtId1 = new Text(shell, SWT.BORDER);
		txtGovtId1.setBounds(210, 500, 200, 19);

		Label lblReason1 = new Label(shell, SWT.NONE);
		lblReason1.setBounds(33, 520, 149, 15);
		lblReason1.setText("Reason for not sharing ID");

		txtReason1 = new Text(shell, SWT.BORDER);
		txtReason1.setBounds(210, 520, 200, 19);

		if (patientEntity.getId() != null) {
			populateAllText(patientEntity);
		}
		if (isView) {
			disableAllText();

		}

		Button btnOk = new Button(shell, SWT.PUSH);
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (patientEntity.getId() != null && !isView) {
					updatePatientDetails(patientEntity);
				}
				if (patientEntity.getId() == null) {
					createPatientDetails(patientEntity);
				}
				shell.close();

			}
		});
		btnOk.setBounds(50, 560, 96, 25);
		btnOk.setText("OK");

		shell.open();

	}

	private static void disableAllText() {
		txtId.setEnabled(false);
		txtName.setEditable(false);
		txtDateOfBirth.setEnabled(false);
		txtPhoneNumber.setEditable(false);
		txtAPhoneNumber.setEditable(false);
		txtHouseNumber.setEditable(false);
		txtHouse2.setEditable(false);
		txtCity.setEditable(false);
		txtCity2.setEditable(false);
		txtState.setEditable(false);
		txtState2.setEnabled(false);
		txtCountry.setEditable(false);
		txtCountry2.setEditable(false);
		txtPin.setEditable(false);
		txtPin2.setEditable(false);
		txtGovtId.setEditable(false);
		txtGovtId1.setEditable(false);
		txtGovtIdType.setEnabled(false);
		txtGovtIdType1.setEditable(false);
		txtReason.setEditable(false);
		txtReason1.setEditable(false);
	}

	private static void createPatientDetails(PatientEntity patientEntity) {
		try {
			getAllText(patientEntity);
			HttpResponse<String> response = PatientHttpClient.createPatientDetails(patientEntity);
			if (response.statusCode() == 200) {
				PatientManagementApplication.populatePatientTree(response);
			} else {
				PatientManagementApplication.openDialog("Create patient failed. Please try again with correct data");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private static void updatePatientDetails(PatientEntity patientEntity) {
		try {
			getAllText(patientEntity);
			HttpResponse<String> response = PatientHttpClient.updatePatientDetails(patientEntity);
			if (response.statusCode() == 200) {
				PatientManagementApplication.populatePatientTree(response);
			} else {
				PatientManagementApplication.openDialog("update patient failed. Please try again with correct data");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private static void getAllText(PatientEntity patientEntity) {
		if (txtId.getText() != null && !txtId.getText().isBlank()) {
			patientEntity.setId(Long.valueOf(txtId.getText()));
		}

		if (txtName.getText() == null || txtName.getText().isBlank()) {
			PatientManagementApplication.openDialog("Patient name required");
		}

		patientEntity.setName(txtName.getText());
		patientEntity.setDateOfBirth(txtDateOfBirth.getText());
		List<Long> phNums = new ArrayList<>();
		try {
			phNums.add(txtPhoneNumber.getText() != null && !txtPhoneNumber.getText().isBlank()
					? Long.valueOf(txtPhoneNumber.getText())
					: null);
			phNums.add(txtAPhoneNumber.getText() != null && !txtAPhoneNumber.getText().isBlank()
					? Long.valueOf(txtAPhoneNumber.getText())
					: null);
		} catch (NumberFormatException e1) {
			PatientManagementApplication.openDialog("Invalid phone number");
			e1.printStackTrace();
		}
		patientEntity.setTelephoneNumber(phNums);
		List<AddressEntity> addresses = new ArrayList<>();
		AddressEntity currentAddress = new AddressEntity(
				cAddress.getText() != null && !cAddress.getText().isBlank() ? Long.valueOf(cAddress.getText()) : null,
				txtHouseNumber.getText(), txtCity.getText(), txtState.getText(), txtCountry.getText(),
				txtPin.getText());
		addresses.add(currentAddress);

		AddressEntity perAddress = new AddressEntity(
				pAddress.getText() != null && !pAddress.getText().isBlank() ? Long.valueOf(pAddress.getText()) : null,
				txtHouse2.getText(), txtCity2.getText(), txtState2.getText(), txtCountry2.getText(), txtPin2.getText());
		addresses.add(perAddress);
		patientEntity.setAddress(addresses);

		List<GovtIdEntity> govtIds = new ArrayList<>();
		GovtIdEntity entity1 = new GovtIdEntity(
				pGovtId.getText() != null && !pGovtId.getText().isBlank() ? Long.valueOf(pGovtId.getText()) : null,
				!txtGovtId.getText().isBlank() ? txtGovtId.getText() : null, txtGovtIdType.getText(),
				txtReason.getText());
		GovtIdEntity entity2 = new GovtIdEntity(
				sGovtId.getText() != null && !sGovtId.getText().isBlank() ? Long.valueOf(sGovtId.getText()) : null,
				!txtGovtId1.getText().isBlank() ? txtGovtId1.getText() : null, txtGovtIdType1.getText(),
				txtReason1.getText());
		govtIds.add(entity1);
		govtIds.add(entity2);
		patientEntity.setGovtIds(govtIds);
	}

	private static void populateAllText(PatientEntity patientEntity) {
		txtName.setText(getEmptyStringIfNull(patientEntity.getName()));
		txtDateOfBirth.setText(getEmptyStringIfNull(patientEntity.getDateOfBirth()));
		txtId.setText(patientEntity.getId().toString());
		List<Long> phNums = patientEntity.getTelephoneNumber();
		if (phNums != null && phNums.size() >= 1) {
			txtPhoneNumber.setText(phNums.get(0) != null ? phNums.get(0).toString() : "");
			txtAPhoneNumber.setText(phNums.size() >= 2 && phNums.get(1) != null ? phNums.get(1).toString() : "");
		}
		List<GovtIdEntity> govtIds = patientEntity.getGovtIds();
		if (govtIds != null && govtIds.size() >= 1 && govtIds.get(0) != null) {
			pGovtId.setText(govtIds.get(0).getId() != null ? govtIds.get(0).getId().toString() : "");
			txtGovtIdType.setText(getEmptyStringIfNull(govtIds.get(0).getGovtIdType()));
			txtGovtId.setText(getEmptyStringIfNull(govtIds.get(0).getGovtId()));
			txtReason.setText(getEmptyStringIfNull(govtIds.get(0).getReasonForNotSharingId()));
			if (govtIds.size() >= 2 && govtIds.get(1) != null) {
				sGovtId.setText(govtIds.get(1).getId() != null ? govtIds.get(1).getId().toString() : "");
				txtGovtIdType1.setText(getEmptyStringIfNull(govtIds.get(1).getGovtIdType()));
				txtGovtId1.setText(getEmptyStringIfNull(govtIds.get(1).getGovtId()));
				txtReason1.setText(getEmptyStringIfNull(govtIds.get(1).getReasonForNotSharingId()));
			}
		}

		List<AddressEntity> addresses = patientEntity.getAddress();
		if (addresses != null && addresses.size() >= 1 && addresses.get(0) != null) {
			cAddress.setText(addresses.get(0).getId() != null ? addresses.get(0).getId().toString() : "");
			txtHouseNumber.setText(getEmptyStringIfNull(addresses.get(0).getHouseNumber()));
			txtCity.setText(getEmptyStringIfNull(addresses.get(0).getCity()));
			txtState.setText(getEmptyStringIfNull(addresses.get(0).getState()));
			txtCountry.setText(getEmptyStringIfNull(addresses.get(0).getCountry()));
			txtPin.setText(getEmptyStringIfNull(addresses.get(0).getPin()));
			if (addresses.size() >= 2 && addresses.get(1) != null) {
				pAddress.setText(addresses.get(1).getId() != null ? addresses.get(1).getId().toString() : "");
				txtHouse2.setText(getEmptyStringIfNull(addresses.get(1).getHouseNumber()));
				txtCity2.setText(getEmptyStringIfNull(addresses.get(1).getCity()));
				txtState2.setText(getEmptyStringIfNull(addresses.get(1).getState()));
				txtCountry2.setText(getEmptyStringIfNull(addresses.get(1).getCountry()));
				txtPin2.setText(getEmptyStringIfNull(addresses.get(1).getPin()));
			}
		}
	}

	private static String getEmptyStringIfNull(String value) {
		if (value == null) {
			return "";
		}
		return value;
	}

}
