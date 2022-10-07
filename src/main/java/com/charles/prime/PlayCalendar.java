package com.charles.prime;

import com.charles.prime.page.PlayAutoPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlayCalendar extends PlayAutoPage {

    public PlayCalendar(Page page) {
        this.page = page;
    }

    private static final String DEFAULT_PATTERN = "MM/dd/yyyy";

    private final Page page;

    public void selectBasic(Integer calendarIndex, LocalDate date) {
        selectBasic(calendarIndex, date, DEFAULT_PATTERN);
    }

    public void selectBasic(Integer calendarIndex, LocalDate date, String datePattern) {
        openCalendar(calendarIndex);
        selectYear(date);
        selectMonth(date);
        selectDay(date);
        validateCalendar(calendarIndex, date, datePattern);
    }

    public void selectMultiple(Integer calendarIndex, List<LocalDate> dates) {
        openCalendar(calendarIndex);
        dates.forEach(date -> {
            selectYear(date);
            selectMonth(date);
            selectDay(date);
        });
        validateCalendar(calendarIndex, dates, DEFAULT_PATTERN, ", ");
    }

    public void selectRange(Integer calendarIndex, LocalDate firstDateRange, LocalDate lastDateRange) {
        openCalendar(calendarIndex);
        selectYear(firstDateRange);
        selectMonth(firstDateRange);
        selectDay(firstDateRange);
        selectYear(lastDateRange);
        selectMonth(lastDateRange);
        selectDay(lastDateRange);
        validateCalendar(calendarIndex, List.of(firstDateRange, lastDateRange), DEFAULT_PATTERN, " - ");
    }

    private void clickFirstLastYear(LocalDate date, int firstYear) {
        if (date.getYear() < firstYear) {
            page.locator(CALENDAR_PREV).last().click();
            return;
        }
        page.locator(CALENDAR_NEXT).last().click();
    }

    private String[] getDatePickerDecadeParts() {
        String datePickerDecade = page.locator(CALENDAR_DECADE).textContent().trim();
        return datePickerDecade.split("-");
    }

    private Integer getFirstYear() {
        return Integer.parseInt(getDatePickerDecadeParts()[0].trim());
    }

    private Integer getLastYear() {
        return Integer.parseInt(getDatePickerDecadeParts()[1].trim());
    }

    private void loopFirstLastYear(LocalDate date) {
        int firstYear = getFirstYear();
        int lastYear = getLastYear();
        while (date.getYear() < firstYear || date.getYear() > lastYear) {
            clickFirstLastYear(date, firstYear);
            firstYear = getFirstYear();
            lastYear = getLastYear();
        }
    }

    private void openCalendar(Integer calendarIndex) {
        page.locator(CALENDAR).nth(calendarIndex).click();
    }

    private void selectDay(LocalDate date) {
        page.locator(String.format(CALENDAR_DAY, date.getDayOfMonth())).last().click();
    }

    private void selectMonth(LocalDate date) {
        page.locator(String.format(CALENDAR_MONTH_TEXT, date.getMonth().toString().substring(0, 3))).last().click(new Locator.ClickOptions().setForce(true));
    }

    private void selectYear(LocalDate date) {
        if (date.getYear() != LocalDate.now().getYear()) {
            page.locator(CALENDAR_YEAR).last().click();
            loopFirstLastYear(date);
            page.locator(String.format(CALENDAR_YEAR_TEXT, date.getYear())).click();
            return;
        }
        page.locator(CALENDAR_MONTH).last().click();
    }

    private void validateCalendar(Integer calendarIndex, LocalDate date, String datePattern) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(datePattern);
        String value = date.format(formatters);
        assertThat(page.locator(CALENDAR_INPUT).nth(calendarIndex)).hasValue(value);
    }

    private void validateCalendar(Integer calendarIndex, List<LocalDate> dates, String datePattern, String delimiter) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(datePattern);
        String values = "";
        for (LocalDate date : dates) {
            values = values.concat(date.format(formatters).concat(delimiter));
        }
        values = values.substring(0, values.length() - delimiter.length());
        assertThat(page.locator(CALENDAR_INPUT).nth(calendarIndex)).hasValue(values);
    }
}
