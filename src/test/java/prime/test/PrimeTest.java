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

import java.time.LocalDate;
import java.util.List;

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

    @Order(5)
    @Test
    @DisplayName("Should calendar basic")
    void shouldCalendarBasic() {
        primePage.goTo("/calendar");
        primePage.removeTopBar();
        PlayAuto.getInstance(page).prime().calendar().selectBasic(0, LocalDate.of(2039, 5, 17));
        page.waitForTimeout(2000);
    }

    @Order(6)
    @Test
    @DisplayName("Should calendar basic with pattern")
    void shouldCalendarBasicWithPattern() {
        primePage.goTo("/calendar");
        primePage.removeTopBar();
        PlayAuto.getInstance(page).prime().calendar().selectBasic(1, LocalDate.of(2039, 5, 17), "MM-dd-yyyy");
        page.waitForTimeout(2000);
    }

    @Order(7)
    @Test
    @DisplayName("Should calendar multiple")
    void shouldCalendarMultiple() {
        primePage.goTo("/calendar");
        primePage.removeTopBar();
        LocalDate date1 = LocalDate.of(2022, 1, 1);
        LocalDate date2 = LocalDate.of(2080, 3, 20);
        LocalDate date3 = LocalDate.of(1901, 12, 31);
        PlayAuto.getInstance(page).prime().calendar().selectMultiple(5, List.of(date1, date2, date3));
        page.waitForTimeout(2000);
    }

    @Order(8)
    @Test
    @DisplayName("Should calendar range")
    void shouldCalendarRange() {
        primePage.goTo("/calendar");
        primePage.removeTopBar();
        PlayAuto.getInstance(page).prime().calendar().selectRange(6, LocalDate.now(), LocalDate.now().plusDays(1));
        page.waitForTimeout(2000);
    }
}
