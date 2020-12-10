import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//Thao De Clercq - Mirte Theunis

public class LogInPage extends Page{

    @FindBy(id="userid")  // verander
    private WebElement useridField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="logIn")
    private WebElement logInButton;


    public LogInPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Home");
    }

    public void setUserid(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submit() {
        logInButton.click();
    }


    public boolean hasErrorMessage (String message) {
        List<WebElement> errorMsg = driver.findElements(By.cssSelector("div.alert-danger ul li"));
        for (WebElement webElement : errorMsg) {
            if (webElement.getText().equals(message)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWelcomeMessage (String message) {
        WebElement welcomeMsg = driver.findElement(By.cssSelector("h2"));
        return message.equals(welcomeMsg.getText());
    }


}
