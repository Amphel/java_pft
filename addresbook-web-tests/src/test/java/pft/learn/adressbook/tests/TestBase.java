package pft.learn.adressbook.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pft.learn.adressbook.appmanager.ApplicationManager;

public class TestBase {

    public ApplicationManager app = new ApplicationManager();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();

    }


    @AfterClass(alwaysRun = true)
      public void tearDown() throws Exception {
        app.stop();
    }

}
