package com.charles;

import com.charles.prime.PlayAutoPrime;
import com.microsoft.playwright.Page;

public record PlayAuto(Page page) {

    public PlayAutoPrime prime() {
        return new PlayAutoPrime(page);
    }

    public static PlayAuto getInstance(Page page) {
        return new PlayAuto(page);
    }
}
