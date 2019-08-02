package uk.gov.dhsc.htbhf.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.dhsc.htbhf.page.component.RadioButton;
import uk.gov.dhsc.htbhf.page.component.SubmitButton;

/**
 * Page object for the Do You Live In Scotland page.
 */
public class DoYouLiveInScotlandPage extends BasePage {

    private RadioButton yesRadioButton;
    private RadioButton noRadioButton;
    private SubmitButton submitButton;

    public DoYouLiveInScotlandPage(WebDriver webDriver, String baseUrl, WebDriverWait wait) {
        super(webDriver, baseUrl, wait);
        this.yesRadioButton = new RadioButton(webDriver, RadioButton.YES);
        this.noRadioButton = new RadioButton(webDriver, RadioButton.NO);
        this.submitButton = new SubmitButton(webDriver);
    }

    @Override
    String getPath() {
        return "/do-you-live-in-scotland";
    }

    @Override
    String getPageName() {
        return "do you live in Scotland";
    }

    @Override
    String getPageTitle() {
        return "GOV.UK - Do you live in Scotland?";
    }

    public void selectYesRadioButton() {
        yesRadioButton.select();
    }

    public void selectNoRadioButton() {
        noRadioButton.select();
    }

    public void clickContinue() {
        submitButton.click();
    }
}
