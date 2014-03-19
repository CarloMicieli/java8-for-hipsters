package io.github.carlomicieli.java8;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class MonthTests {

    @Test
    public void shouldGetLocalizedDisplayNameForMonths() {
        Month feb = Month.FEBRUARY;

        String fullEnglish = feb.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String narrowFrench = feb.getDisplayName(TextStyle.NARROW, Locale.FRENCH);
        String shortGerman = feb.getDisplayName(TextStyle.SHORT, Locale.GERMAN);

        assertThat(fullEnglish, is(equalTo("February")));
        assertThat(narrowFrench, is(equalTo("F")));
        assertThat(shortGerman, is(equalTo("Feb")));
    }

    @Test
    public void shouldObtainInformationAboutTheMonth() {
        boolean leapYear = true;
        Month feb = Month.FEBRUARY;
        Month sept = Month.SEPTEMBER;

        assertThat(feb.getValue(), is(equalTo(2)));
        assertThat(feb.minLength(), is(equalTo(28)));
        assertThat(feb.maxLength(), is(equalTo(29)));
        assertThat(feb.firstMonthOfQuarter(), is(equalTo(Month.JANUARY)));
        assertThat(sept.firstDayOfYear(leapYear), is(equalTo(245)));
        assertThat(sept.firstDayOfYear(!leapYear), is(equalTo(244)));
    }

    @Test
    public void shouldGetMonthFromItsNumber() {
        Month feb = Month.of(2);
        assertThat(feb, is(equalTo(Month.FEBRUARY)));
    }

    @Test(expected = DateTimeException.class)
    public void shouldThrowDateTimeException_WhenMonthNumberIsInvalid() {
        Month.of(13);
    }

    @Test
    public void shouldAddMonths() {
        Month jan = Month.OCTOBER.plus(3L);
        Month nov = Month.OCTOBER.plus(1L);

        assertThat(nov, is(equalTo(Month.NOVEMBER)));
        assertThat(jan, is(equalTo(Month.JANUARY)));
    }

    @Test
    public void shouldSubtractMonths() {
        Month jul = Month.OCTOBER.minus(3L);
        Month sept = Month.OCTOBER.minus(1L);

        assertThat(sept, is(equalTo(Month.SEPTEMBER)));
        assertThat(jul, is(equalTo(Month.JULY)));
    }
}
