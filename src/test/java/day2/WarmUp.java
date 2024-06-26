package day2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class WarmUp {

    @Test
    public void test01() throws InterruptedException {

        // 1. Get to the https://www.webstaurantstore.com/

        WebDriver driver = null;

        try {
            driver = new ChromeDriver();

            driver.manage().window().maximize();

            driver.get("https://www.webstaurantstore.com/");

            // 2. Search for a product

            String term = "table";

            driver.findElement(By.id("searchval")).sendKeys(term, Keys.ENTER);

            // 3. Verify the default results per page is 60

            //  //elementName[@attr='value']

           List <WebElement> titles = driver.findElements(By.xpath("//span[@data-testid='itemDescription']"));

          // List <WebElement> titles = SeleniumUtils.locateByDataTestIdMultiple(driver, "span", "itemDescription");

            Assert.assertEquals(titles.size(),60);

            // 4. Verify the product titles contain the search term

            for (WebElement title : titles) {
                System.out.println(title.getText());
                Assert.assertTrue(title.getText().toLowerCase().contains(term));
            }

            // 5. click on the last title and verify the title text matches

            WebElement lastOne = titles.get(titles.size()-1);

            String expected = lastOne.getText();

            lastOne.click();
            Thread.sleep(1000);

            String actual = driver.findElement(By.id("page-header-description")).getText();

            Assert.assertEquals(actual,expected);


        } finally {
            Thread.sleep(1000);
            driver.quit();
        }
    }
}
