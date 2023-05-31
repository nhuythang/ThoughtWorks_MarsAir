package steps;

import pageObject.HomePageObject;
import domain.BookTicket;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.Log;

import java.io.IOException;
import java.util.List;

public class BookATicketStep {

    private WebDriver driver;
    private HomePageObject homePageObject;
    private Log log;

    public BookATicketStep(WebDriver driver) {
        try {
            this.driver = driver;
            this.homePageObject = new HomePageObject(driver);
            this.log = new Log("LogTest");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void searchForFlightToMars(BookTicket bookTicket) {
        log.logger.info("Search available flight to Mars");
        homePageObject.getDepartingElement().sendKeys(bookTicket.getDepartingDate());
        homePageObject.getReturningElement().sendKeys(bookTicket.getReturningDate());
        if (bookTicket.getPromotionalCode() != null) {
            homePageObject.getPromotionalCodeElement().sendKeys(bookTicket.getPromotionalCode());
        }
        homePageObject.getButtonSearcElement().submit();
    }

    public void validateMessages(String positiveReturnMessage, String negativeReturnMessage) {
        log.logger.info("Validates response in flight search");
        List<WebElement> elements = homePageObject.getMessages("p");
        String fullMessage = "";
        for (WebElement webElement : elements) {
            String actualElement = webElement.getText();

            if (!actualElement.equals("Back")) {
                fullMessage = fullMessage + actualElement;
            }
        }
        Assert.assertTrue(fullMessage.equals(positiveReturnMessage) || fullMessage.equals(negativeReturnMessage));
    }

    public void validatePromotionalCodeMessage(String message) {
        log.logger.info("Validate promotional code response");
        String promotionalCodeMessage = homePageObject.getPromotionalCodeMessage().getText();
        Assert.assertEquals(promotionalCodeMessage, message);
    }

    public void clickAtBackButton() {
        log.logger.info("Click on the back button");
        homePageObject.getBackLinkElement().click();
    }

    public void clickAtLogoLink() {
        log.logger.info("Click on the logo link");
        homePageObject.getLogoLink().click();
    }

    public void clickAtBookATicketLink() {
        log.logger.info("Click on the message: Book a ticket for Mars");
        homePageObject.getBookATicketLink().click();
    }

}
