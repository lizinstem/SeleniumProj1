import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

// Sample Selenium Project
// queryBingTC -- uses Bing search engine to search for term 'Java' and displays links found
// webElementsTC -- uses https://artoftesting.com/sampleSiteForSelenium to interact with various web elements
public class Main {
    WebDriver driver;

    public void tcStep(int counter, String test_name, String message) {
        String step = test_name + ": STEP " + counter + " : ";
        System.out.println(step + message);
    }

    // Public method for launching a chrome browser
    public void queryBingTC(String queryString) {

        int count = 0;

        //Initialize Test Step and Start Test
        String tc_name = "queryBingTC";
        String msg = "Start";
        System.out.println(tc_name + ": " + msg);

        //Set Path to  tcStep(count,tc_name,msg); chromedriver and instantiate chromedriver obj
        msg = "Instantiate ChromeDriver Obj";
        ++count;
        tcStep(count,tc_name,msg);
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        driver= new ChromeDriver();

        // Resize window
        msg = "Resize Browser to 600 x 400";
        ++count;
        tcStep(count,tc_name,msg);
        int width = 600;
        int height = 400;
        Dimension dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);

        //Get Bing site
        msg = "Go to Bing Search Page";
        ++count;
        tcStep(count,tc_name,msg);
        driver.get("https://www.bing.com/");

        //Enter search string in search bar
        msg = "Search for " + queryString + " in search bar";
        ++count;
        tcStep(count,tc_name,msg);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sb_form_q")));
        WebElement bingTextBox = driver.findElement(By.id("sb_form_q"));
        bingTextBox.sendKeys(queryString);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bingTextBox.submit();
        //Wait for search results
        //Collect links returned by the search results
        msg = "Wait for search results";
        ++count;
        tcStep(count,tc_name,msg);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("b_results")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement list=driver.findElement(By.id("b_results"));
        List<WebElement> rows=list.findElements(By.tagName("li"));
        System.out.println("Number of rows of content found in query:" + rows.size());
        if (!rows.isEmpty()) {
            ArrayList<String> linkarr=new ArrayList<>();
            for(WebElement elem : rows) {
                String item= elem.getText();
                if(item.contains("http")) {
                    String[] lines = item.split("\r?\n|\r");
                    for ( String line : lines) {
                        if(line.contains("http")) linkarr.add(line);
                    }
                }
            }
            msg = "Display links found";
            ++count;
            tcStep(count,tc_name,msg);
            System.out.println("Number of links found: " + linkarr.size());
            for (String url : linkarr)
            {
                System.out.println(url);
            }
        }
        driver.close();

        msg = "Completed Successfully";
        System.out.println(tc_name + ": " + msg);
    }

    public void webElementsTC() {

        // implementation of Selenium Sample Script from https://artoftesting.com/sampleSiteForSelenium
        int count = 0;

        //Initialize Test Step and Start Test
        String tc_name = "webElementsTC";
        String msg = "Start";
        System.out.println(tc_name + ": " + msg);

        //Set Path to  tcStep(count,tc_name,msg); chromedriver and instantiate chromedriver obj
        msg = "Instantiate ChromeDriver Obj";
        ++count;
        tcStep(count,tc_name,msg);
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        driver= new ChromeDriver();
        driver.manage().window().maximize();

        //Launch sampleSiteForSelenium
        driver.get("https://artoftesting.com/sampleSiteForSelenium");

        //Fetch the text "This is sample text!" and print it on the console
        msg="Fetch sample text";
        ++count;
        tcStep(count,tc_name,msg);
        String sampleText = driver.findElement(By.id("idOfDiv")).getText();
        System.out.println("Found the following text: " + sampleText);


        //Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Find the link, then click on it
        msg="Locate a Link and Click";
        ++count;
        tcStep(count,tc_name,msg);
        driver.findElement(By.linkText("This is a link")).click();

        //Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Find textbox and write 'Lucky One' in text box
        msg = "Find text box and enter 'Lucky One'";
        ++count;
        tcStep(count,tc_name,msg);
        driver.findElement(By.id("fname")).sendKeys("Lucky One");

        //Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Clear text in textbox
        msg = "Clear textbox";
        ++count;
        tcStep(count,tc_name,msg);
        driver.findElement(By.id("fname")).clear();

        //Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Click on button
        msg = "Click on Submit Button";
        ++count;
        tcStep(count,tc_name,msg);
        driver.findElement(By.id("idOfButton")).click();

        //Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Find and click radio button called "female"
        msg = "Click on 'female' Radio Button";
        ++count;
        tcStep(count,tc_name,msg);
        WebElement rbutton = driver.findElement(By.cssSelector("input[value='female']"));
        rbutton.click();

        //Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Find checkbox and click it
        msg = "Find checkbox and click";
        ++count;
        tcStep(count,tc_name,msg);
        driver.findElement(By.cssSelector("input.Automation")).click();

        //Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Using Select class for for selecting value from dropdown
        msg = "Find dropdown and select item";
        ++count;
        tcStep(count,tc_name,msg);
        Select dropdown = new Select(driver.findElement(By.id("testingDropdown")));
        dropdown.selectByVisibleText("Manual Testing");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.close();

        msg = "Completed Successfully";
        System.out.println(tc_name + ": " + msg);
    }

    public static void main(String[] args) {
        Main myObj= new Main(); // Create an object of Main
        myObj.queryBingTC("Java");
        myObj.webElementsTC();
    }
}