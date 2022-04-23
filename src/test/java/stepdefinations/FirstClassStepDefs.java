package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AllPages;
import utilities.Driver;

public class FirstClassStepDefs {

    AllPages elements = new AllPages();

    @Given("App telefonda acilsin")
    public void app_telefonda_acilsin() {
        Driver.getAppiumDriver();

    }
    @When("kullanici API demos tiklasin")
    public void kullanici_api_demos_tiklasin() {
        elements.mainPage().apiDemos.click();
    }

    @When("kullanici Preference tiklasin")
    public void kullanici_preference_tiklasin() {
        elements.apiDemosPage().preference.click();
    }

    @When("kullanici Preference Dependencies tiklasin")
    public void kullanici_preference_dependencies_tiklasin() {
        elements.preferencePage().preferenceDependencies.click();
    }

    @When("kullanici checkbox tik atsin")
    public void kullanici_checkbox_tik_atsin() {
        if (elements.preferenceDependenciesPage().checkBox.getAttribute("checked").equals("false")) {
            elements.preferenceDependenciesPage().checkBox.click();
        }

    }

    @When("kullanici WiFi settings tiklasin")
    public void kullanici_wi_fi_settings_tiklasin() {
        Driver.waitAndClick(elements.preferenceDependenciesPage().wifiSettings);
    }

    @When("kullanici kutiya yazi gondersin {string}")
    public void kullanici_kutiya_yazi_gondersin(String string) {
        Driver.waitAndSendText(elements.preferenceDependenciesPage().textBox,"Appium Demo");
    }

    @Then("kullanici OK tiklasin")
    public void kullanici_ok_tiklasin() {
        elements.preferenceDependenciesPage().okButton.click();
    }

    @Then("App kapansin")
    public void appKapansin() throws InterruptedException {
        Driver.sleep(5);
        Driver.quitAppiumDriver();
    }
}
