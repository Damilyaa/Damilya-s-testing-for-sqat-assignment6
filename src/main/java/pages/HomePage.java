package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.elements.ElementsPage;
import base.BasePage;
import org.openqa.selenium.By;

import static utilities.JavaScriptUtils.scrollToElementJS;

public class HomePage extends BasePage {
    private static final Logger logger =
            LogManager.getLogger(HomePage.class);

    private By elementsCard = By.xpath("//div[@id='app']//h5[text()='Elements']");

    public ElementsPage goToElements() {
        logger.info("Navigating to Elements page");
        scrollToElementJS(elementsCard);
        click(elementsCard);
        return new ElementsPage();
    }
}