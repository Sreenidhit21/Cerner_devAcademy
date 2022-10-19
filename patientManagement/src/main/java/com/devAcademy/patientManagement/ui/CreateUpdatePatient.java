/**
 * 
 */
package com.devAcademy.patientManagement.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author ST105714
 *
 */
public class CreateUpdatePatient {
	private static Text txtName;

	/**
	 * 
	 */
	public CreateUpdatePatient() {
	}
	
	public static void setContentOnCreateUpdateShell(Shell shell) {
		Label lblPatientDetails = new Label(shell, SWT.NONE);
		lblPatientDetails.setBounds(10, 10,172, 15);
		lblPatientDetails.setText("Patient Details");
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(10, 30, 55, 15);
		lblName.setText("Name");
		
		txtName = new Text(shell, SWT.BORDER);
		txtName.setText("name");
		txtName.setBounds(187, 30, 76, 21);
		
		Label lblDateOfBirth = new Label(shell, SWT.NONE);
		lblDateOfBirth.setBounds(10, 50, 78, 15);
		lblDateOfBirth.setText("Date of birth");
		
		Label lblPhoneNumber = new Label(shell, SWT.NONE);
		lblPhoneNumber.setBounds(10, 70, 172, 15);
		lblPhoneNumber.setText("Primary phone number");
		
		Label lblAlternativePhoneNumber = new Label(shell, SWT.NONE);
		lblAlternativePhoneNumber.setBounds(10, 90, 149, 15);
		lblAlternativePhoneNumber.setText("Alternative phone number");
		
		Label lblPrimaryAddress = new Label(shell, SWT.NONE);
		lblPrimaryAddress.setBounds(10, 110, 117, 15);
		lblPrimaryAddress.setText("Current Address");
		
		Label lblHouseNumber = new Label(shell, SWT.NONE);
		lblHouseNumber.setBounds(33, 130, 94, 15);
		lblHouseNumber.setText("House number");
		
		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setBounds(33, 150, 55, 15);
		lblCity.setText("City");
		
		Label lblState = new Label(shell, SWT.NONE);
		lblState.setBounds(33, 170, 55, 15);
		lblState.setText("State");
		
		Label lblCountry = new Label(shell, SWT.NONE);
		lblCountry.setBounds(33, 190, 55, 15);
		lblCountry.setText("Country");
		
		Label lblPin = new Label(shell, SWT.NONE);
		lblPin.setBounds(33, 210, 55, 15);
		lblPin.setText("PIN");
		
		Label lblAddress2 = new Label(shell, SWT.NONE);
		lblAddress2.setBounds(10, 230, 117, 15);
		lblAddress2.setText("Permanent Address");
		
		Label lblHouseNumber2 = new Label(shell, SWT.NONE);
		lblHouseNumber2.setBounds(33, 250, 94, 15);
		lblHouseNumber2.setText("House number");
		
		Label lblCity2 = new Label(shell, SWT.NONE);
		lblCity2.setBounds(33, 270, 55, 15);
		lblCity2.setText("City");
		
		Label lblState2 = new Label(shell, SWT.NONE);
		lblState2.setBounds(33, 290, 55, 15);
		lblState2.setText("State");
		
		Label lblCountry2 = new Label(shell, SWT.NONE);
		lblCountry2.setBounds(33, 310, 55, 15);
		lblCountry2.setText("Country");
		
		Label lblPin2 = new Label(shell, SWT.NONE);
		lblPin2.setBounds(33, 330, 55, 15);
		lblPin2.setText("PIN");
		
		Label lblPrimaryGovtId = new Label(shell, SWT.NONE);
		lblPrimaryGovtId.setBounds(10, 350, 117, 15);
		lblPrimaryGovtId.setText("Primary Govt ID");
		
		Label lblGovtIdType = new Label(shell, SWT.NONE);
		lblGovtIdType.setBounds(33, 370, 55, 15);
		lblGovtIdType.setText("Govt ID type");
		
		Label lblGovtId = new Label(shell, SWT.NONE);
		lblGovtId.setBounds(33, 390, 149, 15);
		lblGovtId.setText("Govt ID number");
		
		Label lblReason = new Label(shell, SWT.NONE);
		lblReason.setBounds(33, 410, 149, 15);
		lblReason.setText("Reason for not sharing ID");
		
		Label lblSecondaryGovtId = new Label(shell, SWT.NONE);
		lblSecondaryGovtId.setBounds(10, 430, 149, 15);
		lblSecondaryGovtId.setText("Secondary Govt ID");
		
		Label lblGovtIdType1 = new Label(shell, SWT.NONE);
		lblGovtIdType1.setBounds(33, 450, 149, 15);
		lblGovtIdType1.setText("Govt ID type");
		
		Label lblGovtId1 = new Label(shell, SWT.NONE);
		lblGovtId1.setBounds(33, 470, 149, 15);
		lblGovtId1.setText("Govt ID number");
		
		Label lblReason1 = new Label(shell, SWT.NONE);
		lblReason1.setBounds(33, 490, 149, 15);
		lblReason1.setText("Reason for not sharing ID");


		
	}

	

}
