import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Login {
    private static String url="https://www.ultimatix.net/";
    private static WebDriver driver;
    private static String username="1590149";
    private static String auth = "";
    private static String chromedriver = System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe";

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver",chromedriver);
        System.out.println(chromedriver);
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait=new WebDriverWait(driver,30);
        driver.get(url);
        driver.findElement(By.id("form1")).sendKeys(username);
        driver.findElement(By.id("proceed-button")).click();
        WebElement auth_code_btn = driver.findElement(By.id("auth-code-btn"));
        auth_code_btn.click();
        driver.findElement(By.id("authcode1")).sendKeys(auth);
        WebElement submit_btn = driver.findElement(By.id("form-submit"));
        wait.until(ExpectedConditions.elementToBeClickable(submit_btn));
        submit_btn.click();
        driver.findElement(By.linkText("iEvolve")).click();
        //driver.findElement(By.tagName("button")).findElement(By.className("landingpagebutton")).click();
        driver.findElement(By.id("uam_modal")).click();
        driver.findElement(By.id("logoutbutton")).click();
        Boolean loggedout = driver.findElement(By.className("info-logout")).getText().equals("You have successfully logged out");
        if (loggedout){
            driver.quit();
            driver=null;
        }
    }
}
