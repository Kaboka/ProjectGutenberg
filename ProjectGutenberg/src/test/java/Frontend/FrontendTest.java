//package Frontend;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.hamcrest.CoreMatchers.is;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class FrontendTest {
//    static WebDriver driver;
//    static String url = "http://localhost:7777/";
//
//    @BeforeClass
//    public static void setup() {
//        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.get(url);
//    }
//
//    @AfterClass
//    public static void tearDown() {
//        driver.quit();
//    }
//    
//    @Test
//    public void test1() {
//        System.out.println("Test 1. Verify that the DOM has loaded. "
//                + "We do this by checking that all three input fields are present");
//        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
//            WebElement e = d.findElement(By.className("searchForm"));
//            ArrayList<WebElement> form = new ArrayList();
//            WebElement i1 = d.findElement(By.name("authorInput"));
//            WebElement i2 = d.findElement(By.name("titleInput"));
//            WebElement i3 = d.findElement(By.name("cityInput"));
//            form.add(i1);
//            form.add(i2);
//            form.add(i3);
//            Assert.assertThat(form.size(), is(3));
//            return true;
//        });
//
//    }
//}
