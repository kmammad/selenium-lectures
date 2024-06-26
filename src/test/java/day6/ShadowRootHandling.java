package day6;

import day2.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ShadowRootHandling {

    @Test
    public void testCase() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // works for location operation, findElement

        driver.get("https://www.dice.com/");

        Thread.sleep(4000);

        // Locate the main element that contains the shadow root element

        WebElement topElement = driver.findElement(By.cssSelector("dhi-seds-nav-header"));

        SearchContext shadowRoot1 = topElement.getShadowRoot();

// Obtain the second shadow root

        // to find an element inside a shadow root, use CssSelector() to avoid InvalidArgumentException

   SearchContext shadowRoot2 = shadowRoot1.findElement(By.cssSelector("dhi-seds-nav-header-technologist[class='hydrated']")).getShadowRoot();

   SearchContext shadowRoot3 = shadowRoot2.findElement(By.cssSelector("dhi-seds-nav-header-display")).getShadowRoot();

   shadowRoot3.findElement(By.linkText("Why Dice")).click();


        Thread.sleep(5000);
        driver.quit();


    }

}

