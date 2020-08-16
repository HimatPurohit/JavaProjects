import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private final String url="https://www.ultimatix.net/";
    private WebDriver driver;
    private final String username="159049";
    private final String auth = "123456";
    private final String chromedriver = System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe";
    private WebDriverWait wait;
@BeforeClass
    public void openBrowser(){
    System.setProperty("webdriver.chrome.driver",chromedriver);
    System.out.println(chromedriver);
    driver= new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    wait=new WebDriverWait(driver,30);
}

@Test
    public void loginValidation(){
    driver.get(url);
    driver.findElement(By.id("form1")).sendKeys(username);
    driver.findElement(By.id("proceed-button")).click();
    WebElement auth_code_btn = driver.findElement(By.id("auth-code-btn"));
    auth_code_btn.click();
    driver.findElement(By.id("authcode1")).sendKeys(auth);
    WebElement submit_btn = driver.findElement(By.id("form-submit"));
    wait.until(ExpectedConditions.elementToBeClickable(submit_btn));
    submit_btn.click();
}
@Test
public void logoutValidation(){
    driver.findElement(By.id("uam_modal")).click();
    driver.findElement(By.id("logoutbutton")).click();
    Boolean loggedout = driver.findElement(By.className("info-logout")).getText().equals("You have successfully logged out");
}

@AfterClass
    public void closeBrowser(){
    driver.quit();
    driver=null;
}
}
