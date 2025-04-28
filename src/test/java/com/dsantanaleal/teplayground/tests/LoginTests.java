package com.dsantanaleal.teplayground.tests;

import com.dsantanaleal.teplayground.pages.LoginPage;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.dsantanaleal.teplayground.utils.Constants.*;

@Epic("Authentication")
@Feature("Login Feature")
@Story("Valid login and logout")
@Severity(SeverityLevel.CRITICAL)
public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(page);
        navigate("http://uitestingplayground.com/sampleapp");
    }

    @DataProvider(name = "validUser")
    public Object[][] validUser() {
        return new Object[][] {
            {"test", "pwd"}
        };
    }

    @DataProvider(name = "invalidUser")
    public Object[][] invalidUser() {
        return new Object[][] {
            {"test", "invalid"}
        };
    }

    @Test(description = "Login with valid username/password", dataProvider = "validUser")
    public void loginValidUsernamePassword(String username, String password) {
        loginPage.login(username, password);
        Assertions.assertThat(loginPage.getLoginStatus()).isEqualTo(VALID_LOGIN_MESSAGE);
    }

    @Test(description = "Login with invalid username/password", dataProvider = "invalidUser")
    public void loginInvalid(String username, String password) {
        loginPage.login(username, password);
        Assertions.assertThat(loginPage.getLoginStatus()).isEqualTo(INVALID_USER_MESSAGE);
    }

    @Test(description = "Login with empty fields")
    public void loginEmptyFields() {
        loginPage.login("", "");
        Assertions.assertThat(loginPage.getLoginStatus()).isEqualTo(INVALID_USER_MESSAGE);
    }

    @Test(description = "Login with empty username/password")
    public void loginEmptyUsernamePassword() {
        loginPage.login("", "test");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(loginPage.getLoginStatus())
                .withFailMessage("System should not allow empty username")
                .isEqualTo(INVALID_USER_MESSAGE);
        loginPage.login("test", "");
        softAssertions.assertThat(loginPage.getLoginStatus())
                .withFailMessage("System should not allow empty password")
                .isEqualTo(INVALID_USER_MESSAGE);
        softAssertions.assertAll();
    }

    @Test(description = "Logout successfully", dataProvider = "validUser")
    public void logout(String username, String password) {
        loginPage.login(username, password);
        loginPage.logout();
        Assertions.assertThat(loginPage.getLoginStatus()).isEqualTo(LOGOUT_MESSAGE);
    }

}
