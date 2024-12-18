package ticketprice.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TicketPriceCalculatorStepDefs {

    private WebDriver driver;
    private final String testedURL = "https://nlhsueh.github.io/iecs-gym/";

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user selects {string} as the day")
    public void theUserSelectsDay(String day) {
        driver.get(testedURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dayDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("day")));
        Select select = new Select(dayDropdown);
        select.getOptions().stream()
                .filter(option -> option.getText().trim().equals(day))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    @Given("the user selects {string} as the time")
    public void theUserSelectsTime(String time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement timeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("time")));
        Select select = new Select(timeDropdown);
        select.getOptions().stream()
                .filter(option -> option.getText().trim().equals(time))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    @Given("the user inputs {string} as the age")
    public void theUserInputsAge(String age) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ageInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    @Given("the user is a member with the ID {string}")
    public void theUserIsAMemberWithID(String memberId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement memberYesRadio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member-yes")));
        wait.until(ExpectedConditions.elementToBeClickable(memberYesRadio)).click();

        WebElement memberIdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member-id")));
        memberIdInput.clear();
        memberIdInput.sendKeys(memberId);
    }

    @Given("the user is not a member")
    public void theUserIsNotAMember() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement memberNoRadio = wait.until(ExpectedConditions.elementToBeClickable(By.id("member-no")));
        memberNoRadio.click();
    }

    @When("the user clicks the calculate button")
    public void theUserClicksCalculateButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();
    }

    @Then("the ticket price should be {string}")
    public void theTicketPriceShouldBe(String expectedPrice) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));
        assertTrue(output.isDisplayed(), "Output should be displayed");
        String actualPrice = output.getText();
        assertEquals("費用為 $" + expectedPrice + ".", actualPrice, "Ticket price does not match");
    }

    @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedErrorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member-id-error")));
        assertTrue(errorElement.isDisplayed(), "Error message should be displayed");
        String actualErrorMessage = errorElement.getText().trim();
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message does not match");
    }

}
