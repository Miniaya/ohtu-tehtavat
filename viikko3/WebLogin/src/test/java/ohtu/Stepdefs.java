package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userSuccessfullyCreated(String username, String password) {
        driver.get("http://localhost:4567/user");
        createNewUser(username, password, password);
        driver.get(baseUrl);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userNotCreated(String username, String password) {
        driver.get("http://localhost:4567/user");
        createNewUser(username, password, password);
        driver.get(baseUrl);
    }
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    

    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPasswordGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("not successfully created username {string} and password {string} are given")
    public void givingNotSuccessfullyCreatedUser(String username, String password) {
        logInWith(username, password);
    }
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }      

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndConfirmationEntered(String username, String password){
        createNewUser(username, password, password);
    }

    @Then("a new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("a short username {string} and a valid password {string} are entered")
    public void shortUsernameEntered(String username, String password) {
        createNewUser(username, password, password);
    }

    @When("a valid username {string} and a short password {string} are entered")
    public void shortPasswordEntered(String username, String password) {
        createNewUser(username, password, password);
    }

    @When("a valid username {string}, a valid password {string} and incorrect confirmation {string} are entered")
    public void wrongPasswordConfirmationEntered(String username, String password, String confirm) {
        createNewUser(username, password, confirm);
    }

    @Then("user is not created and error {string} is reported")
    public void UserNotCreatedAndErrorReported(String error) {
        pageHasContent(error);
        pageHasContent("Create username and give password");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 

    private void createNewUser(String username, String password, String confirm) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirm);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
