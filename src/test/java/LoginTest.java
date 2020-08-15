import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
    private final String url="https://www.ultimatix.net/";
    private WebDriver driver;
    private final String username="159049";
    private final String auth = "123456";
    private final String chromedriver = System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe";
@BeforeTest
    public void openBrowser(){
    System.setProperty("webdriver.chrome.driver",chromedriver);
    System.out.println(chromedriver);
driver= new ChromeDriver();
driver.get(url);
}

@Test
    public void loginValidation(){
    driver.findElement(By.id("form1")).sendKeys(username);
    driver.findElement(By.id("proceed-button")).click();
    WebElement auth_code_btn = driver.findElement(By.id("auth-code-btn"));
    if (auth_code_btn.isDisplayed()){
        auth_code_btn.click();
    }
    for (int i=0;i<6;i++){
        driver.findElement(By.id("authcode"+(i+1))).sendKeys(String.valueOf(auth.charAt(i)));
    }
}
}
