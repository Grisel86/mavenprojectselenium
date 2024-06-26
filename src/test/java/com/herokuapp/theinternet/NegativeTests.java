package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test
    public void incorrectUsernameTest() {
        System.out.println("Starting incorrectUsernameTest");

//		Create driver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/edgedriver.exe");
        WebDriver driver = new EdgeDriver();

        // sleep for 3 seconds
        sleep(3000);

        // maximize browser window
        driver.manage().window().maximize();

//		open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened.");

//		enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("incorrectUsername");


//		enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

//		click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();

        sleep(3000);

        // Verifications
        WebElement errorMessage = driver.findElement(By.id("flash"));
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual error message does not contain expected. \nActual: "
                        + actualErrorMessage + "\nExpected: "
                        + expectedErrorMessage);

        // Close browser
        driver.quit();
    }

    private void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
