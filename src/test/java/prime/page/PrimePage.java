package prime.page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class PrimePage {

    public PrimePage(Page page) {
        this.page = page;
    }

    public final String url = "http://localhost:8080";

    private final Page page;

    public void goTo(String path) {
        page.navigate(url.concat(path), new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    }
}
