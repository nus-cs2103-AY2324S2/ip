package squid.tasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squid.constants.FORMAT;
import squid.exceptions.SquidDateException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class DateTest {
    @BeforeEach
    public void setup() {

    }

    @Test
    public void testInitialization() {
        try {
            Date date = new Date("20:00, 27 Jan 2024");
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testInitialization_invalidInput_exceptionThrown() {
        assertThrows(SquidDateException.class, () -> {
            Date error = new Date("dawwadadaw");
        });
    }

    @Test
    public void testWordDate_today() {
        String expected = LocalDate.now().format(DateTimeFormatter.ofPattern(FORMAT.DATE));
        try {
            String actual = new Date("today").toString();
            Assertions.assertEquals(expected, actual);
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testWordDate_tomorrow() {
        String expected = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(FORMAT.DATE));
        try {
            String actual = new Date("tomorrow").toString();
            Assertions.assertEquals(expected, actual);
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testWordDate_tmr() {
        String expected = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(FORMAT.DATE));
        try {
            String actual = new Date("tmr").toString();
            Assertions.assertEquals(expected, actual);
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testWordTime_now() {
        String expected = LocalTime.now().format(DateTimeFormatter.ofPattern(FORMAT.TIME));
        try {
            String actual = new Date("now, 27 Jan 2025").toString().split(",")[0];
            Assertions.assertEquals(expected, actual);
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }


}

