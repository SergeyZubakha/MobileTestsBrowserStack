package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserStackDrivers;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;


public class TestBase {
    @BeforeAll
    static void BeforeAll(){
        Configuration.browser = BrowserStackDrivers.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach(){
        String sessionId = sessionId().toString();

//        Attach.screenshotAs("Last screenshot"); //todo
        Attach.pageSource();

        closeWebDriver();

        Attach.addVideo(sessionId);
    }
}
