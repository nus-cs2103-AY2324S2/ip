package venus; // same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class TimeFormatterTest {
    @Test
    public void stringToTimeTest() {
        assertEquals(LocalDate.of(2019, 10, 01),
                TimeFormatter.stringToTime("2019-10-01"));
    }

    @Test
    public void stringToTimeTest2() {
        assertEquals(LocalDate.of(9999, 1, 1),
                TimeFormatter.stringToTime("9999-01-01"));
    }

    @Test
    public void loadTimeFromStringTest() {
        assertEquals(TimeFormatter.loadTimeFromString("Oct 12 3004"),
                LocalDate.of(3004, 10, 12));
    }

    @Test
    public void loadTimeFromStringTest2() {
        assertEquals(TimeFormatter.loadTimeFromString("Dec 31 9999"),
                LocalDate.of(9999, 12, 31));
    }

}
