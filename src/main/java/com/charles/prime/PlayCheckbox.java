package com.charles.prime;

import com.charles.prime.page.PlayAutoPage;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlayCheckbox extends PlayAutoPage {

    public PlayCheckbox(Page page) {
        this.page = page;
    }

    private final Page page;

    public void selectBasic(Integer checkboxIndex) {
        page.locator(CHECKBOX).nth(checkboxIndex).click();
        assertThat(page.locator(CHECKBOX_INPUT).nth(checkboxIndex)).isChecked();
    }
}
