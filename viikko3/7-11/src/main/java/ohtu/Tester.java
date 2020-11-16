package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        // WebElement element = driver.findElement(By.linkText("login"));
        WebElement element = driver.findElement(By.linkText("register new user"));

        element.click();

        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());

        // Kirjautuminen oikealla nimellä ja salasanalla
        // element = driver.findElement(By.name("username"));
        // element.sendKeys("pekka");
        // element = driver.findElement(By.name("password"));
        // element.sendKeys("akkep");
        // element = driver.findElement(By.name("login"));
        // element.submit();

        // Kirjautuminen oikealla nimellä ja väärällä salasanalla
        // element = driver.findElement(By.name("username"));
        // element.sendKeys("pekka");
        // element = driver.findElement(By.name("password"));
        // element.sendKeys("wrong");
        // element = driver.findElement(By.name("login"));
        // element.submit();

        // Uuden käyttäjätunnuksen luominen
        Random r = new Random();
    
        element = driver.findElement(By.name("username"));
        element.sendKeys("henna"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salainen13");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salainen13");
        element = driver.findElement(By.name("signup"));
        element.submit();

        System.out.println(driver.getPageSource());

        // Uloskirjautuminen
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println(driver.getPageSource());

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
