package com.dsantanaleal.teplayground.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage {

    private Locator usernameInput = page.getByPlaceholder("User Name");
    private Locator passwordInput = page.locator("[name='Password']");
    private Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In"));
    private Locator loginStatus = page.locator("#loginstatus");
    private Locator logoutButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log Out"));

    public LoginPage(Page page) {
        super(page);
    }

    public void login(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }

    public void logout() {
        logoutButton.click();
    }

    public String getLoginStatus() {
        return loginStatus.innerText();
    }
}
