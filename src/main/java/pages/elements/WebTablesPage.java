package pages.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static utilities.JavaScriptUtils.scrollToElementJS;

public class WebTablesPage extends ElementsPage{
    private static final Logger logger =
            LogManager.getLogger(WebTablesPage.class);

    private By registrationFirstNameField = By.id("firstName");
    private By registrationLastNameField = By.id("lastName");
    private By registrationEmailField = By.id("userEmail");
    private By registrationAgeField = By.id("age");
    private By registrationSubmitButton = By.id("submit");

    public void clickEditButton(String email) {
        logger.info("Clicking edit table button");
        By editButton = By.xpath("//div[text()='"+ email +"']//following::span[@title='Edit']");
        scrollToElementJS(editButton);
        click(editButton);
    }

    public void setFirstName(String firstName) {
        logger.info("Setting First Name field");
        set(registrationFirstNameField, firstName);
    }

    public void setLastName(String lastName) {
        logger.info("Setting Last Name field");
        set(registrationLastNameField, lastName);
    }

    public void setAge(String age) {
        logger.info("Setting Age field");
        set(registrationAgeField, age);
    }

    public void setEmail(String email) {
        set(registrationEmailField, email);
    }

    public void clickSubmitButton() {
        logger.info("Clicking Submit button");
        click(registrationSubmitButton);
    }

    public String getTableFirstName(String email) {
        logger.info("Retrieving First Name field text");
        By tableFirstName = By.xpath("//div[text()='"+ email +"']//preceding::div[3]");
        return find(tableFirstName).getText();
    }




}