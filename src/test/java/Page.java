import org.openqa.selenium.WebDriver;

public abstract class Page {
    //Thao De Clercq - Mirte Theunis
    WebDriver driver;
    String path = "http://localhost:8080/pageobjectpairprogramming_Thaodeclercq_war_exploded/Controller"; //VERANDER

    public Page (WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle () {
        return driver.getTitle();
    }
}
