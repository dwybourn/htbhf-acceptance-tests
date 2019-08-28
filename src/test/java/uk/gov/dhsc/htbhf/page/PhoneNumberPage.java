package uk.gov.dhsc.htbhf.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.dhsc.htbhf.page.component.InputField;

/**
 * Page object for the enter your phone number page
 */
public class PhoneNumberPage extends SubmittablePage {

    private InputField phoneNumberInput;

    public PhoneNumberPage(WebDriver webDriver, String baseUrl, WebDriverWait wait) {
        super(webDriver, baseUrl, wait);
        this.phoneNumberInput = new InputField(webDriver, wait, "phone-number");
    }

    @Override
    String getPath() {
        return "/phone-number";
    }

    @Override
    String getPageName() {
        return "phone number";
    }

    @Override
    String getPageTitle() {
        return "GOV.UK - What’s your mobile telephone number?";
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberInput.enterValue(phoneNumber);
    }
}
