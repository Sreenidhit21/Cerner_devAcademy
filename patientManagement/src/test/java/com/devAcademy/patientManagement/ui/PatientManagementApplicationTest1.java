package com.devAcademy.patientManagement.ui;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientManagementApplicationTest1 {
	private static SWTBot bot;
	private static Shell shell;
	private static Display display;
	private String deleteID;
	private String getPID;
	private String govtID;

	@BeforeClass
	void setupShell() {

	}

	@BeforeEach
	void setUp() throws Exception {
		display = new Display();
		shell = new Shell();
		shell.setSize(650, 500);
		shell.setText("Patient Management Applications");
		PatientManagementApplication.setContentOnMainShell(shell);
		shell.open();
		bot = new SWTBot(shell);
		deleteID = "7";
		getPID = "8";
		govtID = "0978";
	}

	@Test
	void test() {
		SWTBotPreferences.PLAYBACK_DELAY = 100; // slow down the tests.
		SWTBotLabel botLabel = bot.label("Search Patient by Patient ID or Patient name or Patient Govt ID");
		assertEquals("Search Patient by Patient ID or Patient name or Patient Govt ID", botLabel.getText());
		assertTrue(bot.button("Create Patient").isActive());

		// all other button should deactivate
		assertFalse(bot.button("Update Patient").isEnabled());
		assertFalse(bot.button("View Patient").isEnabled());
		assertFalse(bot.button("Delete Patient").isEnabled());
		assertFalse(bot.radio("Patient ID").isActive());
		assertFalse(bot.radio("Patient Name").isActive());
		assertFalse(bot.radio("Govt ID").isActive());

		SWTBotText txt = bot.textWithId("text");

		// test for search with out button
		txt.setFocus();
		txt.setText("123");

		assertEquals("123", txt.getText());

		bot.button("Search").click();

		SWTBotShell alertShell = bot.shell("Alert");

		assertTrue(alertShell.isActive());
		alertShell.close();

		// test for invalid patient id
		bot.radio("Patient ID").setFocus();
		txt.setFocus();
		txt.setText("ereyr");

		assertEquals("ereyr", txt.getText());

		bot.button("Search").click();

		SWTBotShell alertShell1 = bot.shell("Alert");

		assertTrue(alertShell1.isActive());
		alertShell1.close();

		// test for no patient found for given patient id
		bot.radio("Patient ID").setFocus();
		txt.setFocus();
		txt.setText("123123");

		assertEquals("123123", txt.getText());

		bot.button("Search").click();

		SWTBotShell alertShell2 = bot.shell("Alert");

		assertTrue(alertShell2.isActive());
		alertShell2.close();

		// test for no patient found for given patient name
		bot.radio("Patient Name").setFocus();
		txt.setFocus();
		txt.setText("123123");

		assertEquals("123123", txt.getText());

		bot.button("Search").click();

		SWTBotShell alertShell3 = bot.shell("Alert");

		assertTrue(alertShell3.isActive());
		alertShell3.close();

		// test for no patient found for given govtid
		bot.radio("Govt ID").setFocus();
		txt.setFocus();
		txt.setText("InvalidGovtId");

		assertEquals("InvalidGovtId", txt.getText());

		bot.button("Search").click();

		SWTBotShell alertShell4 = bot.shell("Alert");

		assertTrue(alertShell4.isActive());
		alertShell4.close();

		// test for get patient with valid govt id
		bot.radio("Govt ID").setFocus();
		txt.setFocus();
		txt.setText(govtID);

		assertEquals(govtID, txt.getText());

		bot.button("Search").click();
		assertEquals(1, bot.treeWithId("tree").rowCount());

		// test for get patient with valid patient name
		bot.radio("Patient Name").setFocus();
		txt.setFocus();
		txt.setText("deepa");

		assertEquals("deepa", txt.getText());

		bot.button("Search").click();
		assertTrue(bot.treeWithId("tree").cell(0, 1).contains("deepa"));

		// test for get patient with valid patient id
		bot.radio("Patient ID").setFocus();
		txt.setFocus();
		txt.setText(getPID);

		assertEquals(getPID, txt.getText());

		bot.button("Search").click();
		assertEquals(1, bot.treeWithId("tree").rowCount());

		// select tree item - once item selected update, view, delete buttons should be enabled
		SWTBotTreeItem treeItem = bot.treeWithId("tree").getTreeItem(getPID);
		treeItem.setFocus();
		treeItem.select();
		treeItem.click();
		SWTBotButton update = bot.button("Update Patient");
		assertTrue(update.isEnabled());
		assertTrue(bot.button("View Patient").isEnabled());
		assertTrue(bot.button("Delete Patient").isEnabled());
		
		//test for view patient
		bot.button("View Patient").click();
		SWTBotShell patientDetails = bot.shell("Patient Details");
		
		assertTrue(patientDetails.isActive());
		patientDetails.setFocus();
		patientDetails.bot().button("OK").click();

		// test for update patient details
		update.click();
		SWTBotShell patientDetails1 = bot.shell("Patient Details");

		assertTrue(patientDetails1.isActive()); //Shell -"Patient Details" should opened
		
		patientDetails1.setFocus();
		SWTBotText name = patientDetails1.bot().textWithId("txtName");
		name.setFocus();
		name.setText("name");
		patientDetails1.bot().button("OK").click();
		
		//delete patient
		bot.radio("Patient ID").setFocus();
		txt.setFocus();
		txt.setText(deleteID);

		bot.button("Search").click();
		assertEquals(1, bot.treeWithId("tree").rowCount());

		// select tree item - 
		SWTBotTreeItem treeItem1 = bot.treeWithId("tree").getTreeItem(deleteID);
		treeItem1.setFocus();
		treeItem1.select();
		treeItem1.click();
		bot.button("Delete Patient").setFocus();
		bot.button("Delete Patient").click();
		
		SWTBotShell deleteAlert = bot.shell("Alert");

		assertTrue(deleteAlert.isActive());
		deleteAlert.close();
		
		//create patient
		bot.button("Create Patient").click();
		SWTBotShell patientDetails2 = bot.shell("Patient Details");

		assertTrue(patientDetails2.isActive()); //Shell -"Patient Details" should opened
		
		patientDetails2.setFocus();
		SWTBotText cname = patientDetails2.bot().textWithId("txtName");
		cname.setFocus();
		cname.setText("name");
		
		SWTBotText cgtype = patientDetails2.bot().textWithId("txtGovtIdType");
		cgtype.setFocus();
		cgtype.setText("Aadaar");
		
		SWTBotText cgid = patientDetails2.bot().textWithId("txtGovtId");
		cgid.setFocus();
		cgid.setText("123456");
		
		patientDetails2.bot().button("OK").click();
		
				

	}

	@AfterEach
	public void sleep() {
		// bot.sleep(2000);
	}

}
