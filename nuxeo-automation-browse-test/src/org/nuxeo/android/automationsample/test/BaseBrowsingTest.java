package org.nuxeo.android.automationsample.test;

import com.jayway.android.robotium.solo.Solo;

public class BaseBrowsingTest extends BasisTest{

	protected String browseAndCreate(boolean online) throws Exception {

		String tag = online ? "online" : "offline";

		waitForNuxeoActivity("org.nuxeo.android.automationsample.HomeSampleActivity");

		solo.clickOnView(findViewById(org.nuxeo.android.automationsample.R.id.browsetBtn));

		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		// Domain
		solo.clickInList(1);
		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		// Workspaces
		solo.clickInList(3);
		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		// new item
		solo.sendKey(Solo.MENU);
		solo.clickOnText("New item");
		solo.clickInList(3);

		waitForNuxeoActivity("org.nuxeo.android.automationsample.DocumentLayoutActivity");

		String title = "Folder " + tag + " " + System.currentTimeMillis();
		solo.clearEditText(0);
		solo.enterText(0, title);
		solo.clickOnView(findViewById(org.nuxeo.android.automationsample.R.id.updateDocument));
		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		String newTitle = getDocumentTitle(0);
		assertNotNull(newTitle);
		assertEquals(title, newTitle);

		if (online) {
			waitForDocumentStatus(0, "");
		} else {
			assertEquals("new", getDocumentStatus(0));
		}

		solo.goBack();
		solo.goBack();
		solo.goBack();

		return newTitle;
	}

	protected String browseAndEdit(boolean online) throws Exception {

		String tag = online ? "online" : "offline";

		waitForNuxeoActivity("org.nuxeo.android.automationsample.HomeSampleActivity");

		solo.clickOnView(findViewById(org.nuxeo.android.automationsample.R.id.browsetBtn));

		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		// Domain
		solo.clickInList(1);
		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		// Workspaces
		solo.clickInList(3);
		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		solo.clickLongInList(0);
		Thread.sleep(200);
		solo.clickOnText("Edit");

		waitForNuxeoActivity("org.nuxeo.android.automationsample.DocumentLayoutActivity");

		solo.clearEditText(0);
		String title = "Folder " + tag + " Edited" + System.currentTimeMillis();
		solo.enterText(0, title);
		solo.clickOnView(findViewById(org.nuxeo.android.automationsample.R.id.updateDocument));
		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		String newTitle = getDocumentTitle(0);
		assertNotNull(newTitle);
		assertEquals(title, newTitle);

		if (online) {
			waitForDocumentStatus(0, "");
		} else {
			assertEquals("updated", getDocumentStatus(0));
		}

		solo.goBack();
		solo.goBack();
		solo.goBack();

		return newTitle;
	}

	protected void browseAndCheck(String title) throws Exception {

		waitForNuxeoActivity("org.nuxeo.android.automationsample.HomeSampleActivity");

		solo.clickOnView(findViewById(org.nuxeo.android.automationsample.R.id.browsetBtn));

		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		// Domain
		solo.clickInList(1);
		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		// Workspaces
		solo.clickInList(3);
		waitForNuxeoActivity("org.nuxeo.android.automationsample.GetChildrenSampleActivity");

		String newTitle = getDocumentTitle(0);
		assertNotNull(newTitle);
		assertEquals(title, newTitle);
    	waitForDocumentStatus(0, "");

    	assertNotNull(getDocumentCreationDate(0));

		solo.clickLongInList(0);
		Thread.sleep(200);
		solo.clickOnText("View");

		waitForNuxeoActivity("org.nuxeo.android.automationsample.DocumentLayoutActivity");

		solo.goBack();
		solo.goBack();
		solo.goBack();
		solo.goBack();
	}

}
