package utilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Driver {

    private static AppiumDriver<MobileElement> appiumDriver;

    private static int timeout = 5;

    public static AppiumDriver getAppiumDriver()  {


        URL appiumServerURL = null;
        try {
            appiumServerURL = new URL("http:127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (appiumDriver == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigReader.getProperty("automationName"));
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigReader.getProperty("platformName"));
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigReader.getProperty("platformVersion"));
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigReader.getProperty("deviceName"));
            desiredCapabilities.setCapability(MobileCapabilityType.APP, ConfigReader.getProperty("appPath"));
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            desiredCapabilities.setCapability("autoAcceptAlert",true);

            if (ConfigReader.getProperty("platformName").equals("android")) {

            //if you do not provide app path so you should provide "appPackage" and "appActivity"
            desiredCapabilities.setCapability("appPackage","");
            desiredCapabilities.setCapability("appActivity","");
            appiumDriver = new AndroidDriver(appiumServerURL,desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else if (ConfigReader.getProperty("platformName").equals("ios")) {
                //if you do not provide app path so you should use "bundleId"
                desiredCapabilities.setCapability("bundleId",ConfigReader.getProperty("iosBundleId"));
                appiumDriver = new IOSDriver(appiumServerURL,desiredCapabilities);
                appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else {
                throw new UnsupportedOperationException("Invalid Platform Name " + ConfigReader.getProperty("platformName"));
            }
        }
        return appiumDriver;
    }



    public static void waitAndClick(MobileElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static void waitAndClick(MobileElement element) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }


    public static void waitAndSendText(MobileElement element, String text, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    //    Driver.waitANdSendText(Element , "TEXT");
    public static void waitAndSendText(MobileElement element, String text) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static void waitAndSendTextWithDefaultTime(MobileElement element, String text) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static String waitAndGetText(MobileElement element, int timeout) {
        String text = "";
        for (int i = 0; i < timeout; i++) {
            try {
                text = element.getText();
                return text;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
        return null;
    }
    public static MobileElement waitForVisibility(MobileElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getAppiumDriver(), timeout);
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static MobileElement waitForClickablility(MobileElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getAppiumDriver(), timeout);
        return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    //Webdriver
    //ChromeDriver
    //Iedriver
    //FirefoxDriver

    public static void wait2(int sec) {
        try {
            Thread.sleep(1000 * sec);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //5 seconds
    public static void waitAndClickElement(MobileElement element, int seconds) {
        for (int i = 0; i < seconds; i++) {

            try {
                element.click();
                break;
            } catch (Exception e) {
                wait2(1);
            }


        }
    }

    public static void wait(int secs) {

        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }










    public static void executeJScommand(MobileElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getAppiumDriver();
        jse.executeScript(command, element);
    }

    public static void selectAnItemFromDropdown(MobileElement item, String selectableItem) {
        wait(5);
        Select select = new Select(item);
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equalsIgnoreCase(selectableItem)) {
                select.getOptions().get(i).click();
                break;
            }
        }

    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(MobileElement element) {
        ((JavascriptExecutor) Driver.getAppiumDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getAppiumDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param elements
     */


    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(MobileElement element) {
        new Actions(Driver.getAppiumDriver()).doubleClick(element).build().perform();
    }

    //    Parameter1 : MobileElement
//    Parameter2:  String
//    Driver.selectByVisibleText(dropdown element, "CHECKING-91303-116.98$")
    public static void selectByVisibleText(MobileElement element, String text) {
        Select objSelect = new Select(element);
        objSelect.selectByVisibleText(text);
    }
    //    Parameter1 : MobileElement
//    Parameter2:  int
//    Driver.selectByIndex(dropdown element, 1)
    public static void selectByIndex(MobileElement element, int index) {
        Select objSelect = new Select(element);
        objSelect.selectByIndex(index);
    }
    //    Parameter1 : MobileElement
//    Parameter2:  String
//    Driver.selectByIndex(dropdown element, "91303")


    public static void sleep(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitAndClickLocationText(MobileElement element, String value) {
        Driver.getAppiumDriver().findElement(By.xpath("//*[text()='" + value + "']")).click();
    }


        public static void quitAppiumDriver(){
            if (appiumDriver != null) {
                appiumDriver.quit();
                appiumDriver = null;
            }
        }


}