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

public class DateTimeTest {
    @BeforeEach
    public void setup() {

    }

    @Test
    public void testInitialization() {
        try {
            DateTime dateTime = new DateTime("20:00, 27 Jan 2024");
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testInitialization_invalidInput_exceptionThrown() {
        assertThrows(SquidDateException.class, () -> {
            DateTime error = new DateTime("dawwadadaw");
        });
    }

    @Test
    public void testWordDate_today() {
        String expected = LocalDate.now().format(DateTimeFormatter.ofPattern(FORMAT.DATE));
        try {
            String actual = new DateTime("today").toString();
            Assertions.assertEquals(expected, actual);
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testWordDate_tomorrow() {
        String expected = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(FORMAT.DATE));
        try {
            String actual = new DateTime("tomorrow").toString();
            Assertions.assertEquals(expected, actual);
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testWordDate_tmr() {
        String expected = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(FORMAT.DATE));
        try {
            String actual = new DateTime("tmr").toString();
            Assertions.assertEquals(expected, actual);
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testWordTime_now() {
        String expected = LocalTime.now().format(DateTimeFormatter.ofPattern(FORMAT.TIME));
        try {
            String actual = new DateTime("now, 27 Jan 2025").toString().split(",")[0];
            Assertions.assertEquals(expected, actual);
        } catch (SquidDateException e) {
            Assertions.fail();
        }
    }


}

