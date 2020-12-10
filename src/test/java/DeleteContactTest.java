import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeleteContactTest {
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

    @Test  //https://stackoverflow.com/questions/12270092/best-way-to-check-that-element-is-not-present-using-selenium-webdriver-with-java
    public void test_Delete_buttons_on_Contactpage_do_not_show_if_UserIsNotRegistered() {
        driver.get(path + "?command=Contacts");

        List<WebElement> deleteButton = driver.findElements(By.className("button"));
        assertEquals(deleteButton.size(), 0);
    }

    @Test
    public void test_Delete_buttons_on_Contactpage_show_if_UserIsRegistered() {
        WebElement useridInput = driver.findElement(By.id("userid"));
        useridInput.clear();
        useridInput.sendKeys("thao");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys("thao");

        driver.findElement(By.id("logIn")).click();

        driver.get(path + "?command=Contacts");

        WebElement deleteButton = driver.findElement(By.className("button"));

        assertNotNull(deleteButton);
        assertEquals(deleteButton.getText(), "Delete");
    }

    @Test
    public void test_Press_Delete_button_confirmDeletePage_is_shown() {
        WebElement useridInput = driver.findElement(By.id("userid"));
        useridInput.clear();
        useridInput.sendKeys("thao");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys("thao");

        driver.findElement(By.id("logIn")).click();

        driver.get(path + "?command=Contacts");

        driver.findElement(By.className("button")).click();

        assertEquals("Confirm delete", driver.getTitle());
    }

    @Test
    public void test_Press_Cancel_button_on_confirmDeletePage_contact_is_NOT_deleted_and_shown_on_contactspage() {
        WebElement useridInput = driver.findElement(By.id("userid"));
        useridInput.clear();
        useridInput.sendKeys("thao");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys("thao");

        driver.findElement(By.id("logIn")).click();

        driver.get(path + "?command=Contacts");

        driver.findElement(By.className("button")).click();

        driver.findElement(By.id("cancel")).click();
        assertEquals("Contacts", driver.getTitle());
        ArrayList<WebElement> tds =
                (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementsWithText(tds, "Gunter De Clercq"));
    }

    @Test
    public void test_Press_Yes_button_on_confirmDeletePage_contact_IS_deleted_and_NOT_shown_on_contactspage() {
        WebElement useridInput = driver.findElement(By.id("userid"));
        useridInput.clear();
        useridInput.sendKeys("thao");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys("thao");

        driver.findElement(By.id("logIn")).click();

        driver.get(path + "?command=Contacts");

        driver.findElement(By.className("button")).click();

        driver.findElement(By.id("yes")).click();
        assertEquals("Contacts", driver.getTitle());
        ArrayList<WebElement> tds =
                (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertFalse(containsWebElementsWithText(tds, "Chris Decat"));
    }

    private boolean containsWebElementsWithText(ArrayList<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        } return false;
    }






}
