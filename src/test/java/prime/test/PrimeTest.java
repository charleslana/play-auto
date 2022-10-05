package prime.test;

import com.charles.PlayAuto;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import prime.page.PrimePage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PrimeTest extends TestConfig {

    private PrimePage primePage;

    @BeforeEach
    public void setUp() {
        page.setDefaultTimeout(60000);
        primePage = new PrimePage(page);
    }

    @Order(1)
    @Test
    @DisplayName("Should autocomplete basic")
    void shouldAutocompleteBasic() {
        primePage.goTo("/autocomplete");
        PlayAuto.getInstance(page).prime().autocomplete().selectBasic(1, "Brazil");
        page.waitForTimeout(2000);
    }

    @Order(2)
    @Test
    @DisplayName("Should autocomplete multiple")
    void shouldAutocompleteMultiple() {
        primePage.goTo("/autocomplete");
        page.mouse().wheel(0, 300);
        PlayAuto.getInstance(page).prime().autocomplete().selectMultiple(5, "Brazil", "United States");
        page.waitForTimeout(2000);
    }

    @Order(3)
    @Test
    @DisplayName("Should cascade select basic")
    void shouldCascadeSelectBasic() {
        primePage.goTo("/cascadeselect");
        PlayAuto.getInstance(page).prime().cascadeSelect().selectBasic(0, "Australia", "New South Wales", "Sydney");
        PlayAuto.getInstance(page).prime().cascadeSelect().selectBasic(1, 1, 3, 5);
        page.waitForTimeout(2000);
    }

    @Order(4)
    @Test
    @DisplayName("Should checkbox basic")
    void shouldCheckboxBasic() {
        primePage.goTo("/checkbox");
        PlayAuto.getInstance(page).prime().checkbox().selectBasic(0);
        page.waitForTimeout(2000);
    }
}
