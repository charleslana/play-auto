package com.charles.prime;

import com.charles.prime.page.PlayAutoPage;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlayCascadeSelect extends PlayAutoPage {

    public PlayCascadeSelect(Page page) {
        this.page = page;
    }

    private final Page page;

    public void selectBasic(Integer cascadeSelectedIndex, String... text) {
        page.locator(CASCADE_SELECT).nth(cascadeSelectedIndex).click();
        for (String t : text) {
            page.locator(String.format(CASCADE_SELECT_ITEMS_WITH_TEXT, t)).click();
        }
        assertThat(page.locator(CASCADE_SELECT_LABEL).nth(cascadeSelectedIndex)).hasText(text[text.length - 1]);
    }

    public void selectBasic(Integer cascadeSelectedIndex, Integer... itemPosition) {
        page.locator(CASCADE_SELECT).nth(cascadeSelectedIndex).click();
        for (Integer i : itemPosition) {
            page.locator(CASCADE_SELECT_ITEMS).nth(i).click();
        }
    }
}
