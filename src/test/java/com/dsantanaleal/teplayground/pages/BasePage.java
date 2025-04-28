package com.dsantanaleal.teplayground.pages;

import com.microsoft.playwright.Page;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasePage {

    @NonNull
    protected Page page;

}
