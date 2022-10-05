package com.charles.prime;

import com.microsoft.playwright.Page;

public record PlayAutoPrime(Page page) {

    public PlayAutocomplete autocomplete() {
        return new PlayAutocomplete(page);
    }

    public PlayCascadeSelect cascadeSelect() {
        return new PlayCascadeSelect(page);
    }

    public PlayCheckbox checkbox() {
        return new PlayCheckbox(page);
    }
}
