package stepdefinations;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AllPages;
import pages.android.PreferencePage;
import pages.android.SwitchPage;
import utilities.Driver;

public class SwitchStepDefs {
    TouchAction touchAction =  new TouchAction(Driver.getAppiumDriver());
    AllPages elements=new AllPages();
    @When("kullanici Switch tiklasin")
    public void kullanici_switch_tiklasin() {
        elements.preferencePage().switchButton.click();
    }

    @When("ilk switch butonu acik olmali")
    public void ilk_switch_butonu_acik_olmali() {
        if (elements.switchPage().firstSwitchButton.getAttribute("checked").equals("false")) {
            touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(elements.switchPage().firstSwitchButton))).perform();
        }
    }

    @Then("ilk switch butonunun acik oldugunu onayla")
    public void ilk_switch_butonunun_acik_oldugunu_onayla() {
        Assert.assertTrue(elements.switchPage().firstSwitchButton.getAttribute("checked").equals("true"));
    }

    @When("ilk switch butonu kapali olmali")
    public void ilk_switch_butonu_kapali_olmali() {
        if (!elements.switchPage().firstSwitchButton.getAttribute("checked").equals("false")) {
            touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(elements.switchPage().firstSwitchButton))).perform();
        }
    }

    @Then("ilk switch butonunun kapali oldugunu onayla")
    public void ilk_switch_butonunun_kapali_oldugunu_onayla() {
        Assert.assertTrue(!elements.switchPage().firstSwitchButton.getAttribute("checked").equals("true"));
    }

    @Then("ikinci switch butonu acik olmali")
    public void ikinci_switch_butonu_acik_olmali() {
        if (elements.switchPage().secondSwitchButton.getAttribute("checked").equals("false")) {
            touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(elements.switchPage().firstSwitchButton))).perform();
        }
    }

    @Then("ikinci switch butonu acik oldugunu onayla")
    public void ikinci_switch_butonu_acik_oldugunu_onayla() {
        Assert.assertTrue(elements.switchPage().secondSwitchButton.getAttribute("checked").equals("true"));
    }

}
