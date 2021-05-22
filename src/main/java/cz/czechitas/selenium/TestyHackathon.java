package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyHackathon {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void uzivatelSeHlasiPresMainPage() {
        prihlaseniExistujicihoUzivatele("PepaHnatek@gmail.com","HrciPrci");

        WebElement mujUcet = prohlizec.findElement(By.xpath("//div/h1"));
        Assertions.assertNotNull(mujUcet);
    }

    @Test
    public void registraceUzivatele() {
        String nahodnyEmail = "PepaHnatek" + System.currentTimeMillis() + "@gmail.com";

        prohlizec.navigate().to("http://czechitas-datestovani-hackathon.cz/en/");
        WebElement tlacitkoSignIn = prohlizec.findElement(By.className("hide_xs"));
        tlacitkoSignIn.click();
        WebElement poleEmail = prohlizec.findElement(By.xpath("//form/div/div/input[@name='email_create']"));
        poleEmail.sendKeys(nahodnyEmail);
        WebElement tlacitkoVytvoritUcet = prohlizec.findElement(By.id("SubmitCreate"));
        tlacitkoVytvoritUcet.click();
        WebElement jmenoUzivatele = prohlizec.findElement(By.id("customer_firstname"));
        jmenoUzivatele.sendKeys("Pepka");
        WebElement prijmeniUzivatele = prohlizec.findElement(By.id("customer_lastname"));
        prijmeniUzivatele.sendKeys("Hnátková");
        WebElement hesloUzivatele = prohlizec.findElement(By.xpath("//input[@id='passwd']"));
        hesloUzivatele.sendKeys("hackathonujeme");
        WebElement denNarozeni = prohlizec.findElement(By.id("days"));
        denNarozeni.sendKeys("1");
        WebElement mesicNarozeni = prohlizec.findElement(By.id("months"));
        mesicNarozeni.sendKeys("November");
        WebElement rokNarozeni = prohlizec.findElement(By.id("years"));
        rokNarozeni.sendKeys("1999");
        WebElement checkboxNewsletter = prohlizec.findElement(By.id("newsletter"));
        checkboxNewsletter.sendKeys(Keys.SPACE);
        WebElement checkboxSpecialniNabidky = prohlizec.findElement(By.id("optin"));
        checkboxSpecialniNabidky.sendKeys(Keys.SPACE);
        WebElement tlacitkoRegistrovat = prohlizec.findElement(By.id("submitAccount"));
        tlacitkoRegistrovat.click();
        WebElement tlacitkoPersonalInfo = prohlizec.findElement(By.xpath("//a[@title='Information']"));
        tlacitkoPersonalInfo.click();

        String xPathEmail = "//input[@value='" + nahodnyEmail + "']";

        WebElement uzivateluvEmail = prohlizec.findElement(By.xpath(xPathEmail));
        Assertions.assertNotNull(uzivateluvEmail);
    }

    public void prihlaseniExistujicihoUzivatele(String email, String heslo){
        String prihlasovaciEmail = email;
        String prihlasovaciHeslo = heslo;

        prohlizec.navigate().to("http://czechitas-datestovani-hackathon.cz/en/");
        WebElement tlacitkoSignIn = prohlizec.findElement(By.className("hide_xs"));
        tlacitkoSignIn.click();
        WebElement poleEmail = prohlizec.findElement(By.xpath("//form/div/div/input[@name='email']"));
        poleEmail.sendKeys(email);
        WebElement poleHeslo = prohlizec.findElement(By.xpath("//form/div/div/input[@name='passwd']"));
        poleHeslo.sendKeys(heslo);
        WebElement tlacitkoPotvrditPrihlaseni = prohlizec.findElement(By.id("SubmitLogin"));
        tlacitkoPotvrditPrihlaseni.click();
    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
