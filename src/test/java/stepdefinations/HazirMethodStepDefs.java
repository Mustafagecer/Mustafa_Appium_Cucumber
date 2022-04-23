package stepdefinations;

import io.cucumber.java.en.When;
import pages.AllPages;
import utilities.Driver;
import utilities.ReusableMethods;

public class HazirMethodStepDefs {
    AllPages elements = new AllPages();

    @When("kullanici {string} tiklasin")
    public void kullanici_tiklasin(String pageName) throws InterruptedException {

        ReusableMethods.clickOnPage(pageName);
    }

}
