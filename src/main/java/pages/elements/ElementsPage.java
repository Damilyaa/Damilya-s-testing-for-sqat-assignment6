package pages.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pages.HomePage;

import static utilities.JavaScriptUtils.scrollToElementJS;

public class ElementsPage extends HomePage {
    private static final Logger logger =
            LogManager.getLogger(ElementsPage.class);

    private By webTablesMenuItem = By.xpath("//li[@id='item-3']/span[text()='Web Tables']");

    public WebTablesPage clickWebTables() {
        logger.info("Clicking Web Tables menu item");
        scrollToElementJS(webTablesMenuItem);
        click(webTablesMenuItem);
        return new WebTablesPage();
    }
}

