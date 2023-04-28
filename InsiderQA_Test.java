import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class QAProcessTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  public String waitForWindow(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Set<String> whNow = driver.getWindowHandles();
    Set<String> whThen = (Set<String>) vars.get("window_handles");
    if (whNow.size() > whThen.size()) {
      whNow.removeAll(whThen);
    }
    return whNow.iterator().next();
  }
  @Test
  public void qAProcess() {
    driver.get("https://useinsider.com/");
    driver.manage().window().setSize(new Dimension(1936, 1048));
    driver.findElement(By.cssSelector(".nav-item:nth-child(6) > #mega-menu-1 > span")).click();
    driver.findElement(By.cssSelector(".nav-item:nth-child(6) .dropdown-item:nth-child(1) > .col-12:nth-child(3) h5")).click();
    driver.findElement(By.linkText("See all teams")).click();
    driver.findElement(By.cssSelector(".job-item:nth-child(23) .img-fluid")).click();
    driver.findElement(By.linkText("See all QA jobs")).click();
    vars.put("window_handles", driver.getWindowHandles());
    driver.moveToElement(By.linkText("Software Quality Assurance Engineer (Remote)")).perform();
    driver.findElement(By.linkText("Apply Now")).click();
    vars.put("win1982", waitForWindow(2000));
    driver.switchTo().window(vars.get("win1982").toString());
  }
}
