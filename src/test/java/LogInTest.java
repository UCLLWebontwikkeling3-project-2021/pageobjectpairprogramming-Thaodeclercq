import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//Thao De Clercq - Mirte Theunis

public class LogInTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/pageobjectpairprogramming_Thaodeclercq_war_exploded/Controller"; //VERANDER

    @Before
    public void setUp() {
        //VERANDER
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Thao De Clercq\\Documents\\2TI\\Semester 1\\Web3\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_LogIn_AllFieldsFilledInCorrectly_UserIsLoggedIn() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.setUserid("thao"); //VERANDER
        logInPage.setPassword("thao"); //VERANDER

        logInPage.submit();

        assertEquals("Home", logInPage.getTitle()); //VERANDER
        assertTrue(logInPage.hasWelcomeMessage("Welcome Thao!")); //VERANDER

    }

    @Test
    public void test_LogIn_UserIdNotFilledIn_ErrorMesssageGiven() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.setUserid("");
        logInPage.setPassword("thao"); //VERANDER

        logInPage.submit();

        assertEquals("Home", logInPage.getTitle());
        assertTrue(logInPage.hasErrorMessage("Userid is empty")); // VERANDER
    }

    @Test
    public void test_LogIn_PasswordNotFilledIn_ErrorMessageGiven() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.setUserid("thao"); //VERANDER
        logInPage.setPassword("");

        logInPage.submit();

        assertEquals("Home", logInPage.getTitle());
        assertTrue(logInPage.hasErrorMessage("Password is empty")); // VERANDER
    }

    @Test
    public void test_LogIn_UserIdFilledIn_PasswordFilledIn_WrongCombination_ErrorMessageGiven() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.setUserid("thao");
        logInPage.setPassword("123"); //VERANDER

        logInPage.submit();

        assertEquals("Home", logInPage.getTitle());
        assertTrue(logInPage.hasErrorMessage("Wrong combination of userid and password")); // VERANDER
    }
    //Juiste userid met fout ww -> krijg error message
    //Fout userid met -> error
    //Beide juist -> ingelogd , "welkom..."

}
