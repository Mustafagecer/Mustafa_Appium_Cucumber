package stepdefinations;

import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AllPages;
import utilities.Driver;
import utilities.ReusableMethods;

public class SpinnerStepDefs {

    AllPages elements=new AllPages();

    @When("kullanici color menusunden {string} secmeli")
    public void kullanici_color_menusunden_secmeli(String string) {
        Driver.waitAndClick(elements.spinnerPage().colorDropDown);
        Driver.waitAndClick(elements.spinnerPage().blue);
        Assert.assertTrue(Driver.waitForVisibility(elements.spinnerPage().selectedBlue,10).isDisplayed());
    }

    @When("kullanici planet menusunden {string} secmeli")
    public void kullanici_planet_menusunden_secmeli(String string) {
        Driver.waitAndClick(elements.spinnerPage().planetDropDown);
        Driver.waitAndClick(elements.spinnerPage().saturn);
        Assert.assertTrue(Driver.waitForVisibility(elements.spinnerPage().selectedSaturn,10).isDisplayed());

    }
}
