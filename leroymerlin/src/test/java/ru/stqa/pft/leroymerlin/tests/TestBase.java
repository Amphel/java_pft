package ru.stqa.pft.leroymerlin.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.leroymerlin.appmanager.ApplicationManager;

public class TestBase {

    public ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();

    }


    @AfterClass(alwaysRun = true)
      public void tearDown() throws Exception {
        app.stop();
    }

}
