import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContactsTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Web3_project_war_exploded/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Thao De Clercq\\Documents\\2TI\\Semester 1\\Web3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path);
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Press_Only_show_positive_clients_button_positive_contacts_are_shown() {
        WebElement useridInput = driver.findElement(By.id("userid"));
        useridInput.clear();
        useridInput.sendKeys("thao");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys("thao");

        driver.findElement(By.id("logIn")).click();

        driver.get(path + "?command=Contacts");

        driver.findElement(By.id("positiveContacts")).click();

        ArrayList<WebElement> tds =
                (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertFalse(containsWebElementsWithText(tds, "false"));

        String text = driver.findElement(By.id("positiveContacts")).getText();
        assertEquals(text, "Show all clients");
    }

    private boolean containsWebElementsWithText(ArrayList<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        } return false;
    }

}
