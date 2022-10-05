package com.charles.prime;

import com.charles.prime.page.PlayAutoPage;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlayAutocomplete extends PlayAutoPage {

    public PlayAutocomplete(Page page) {
        this.page = page;
    }

    private final Page page;

    public void selectBasic(Integer autocompleteIndex, String text) {
        page.locator(AUTOCOMPLETE_INPUT).nth(autocompleteIndex).fill(text);
        assertThat(page.locator(AUTOCOMPLETE_ITEMS).nth(0)).hasText(text);
        page.locator(AUTOCOMPLETE_ITEMS).nth(0).click();
    }

    public void selectMultiple(Integer autocompleteIndex, String... text) {
        for (String t : text) {
            page.locator(AUTOCOMPLETE_INPUT).nth(autocompleteIndex).fill(t);
            assertThat(page.locator(AUTOCOMPLETE_ITEMS).nth(0)).hasText(t);
            page.locator(AUTOCOMPLETE_ITEMS).nth(0).click();
        }
    }
}
