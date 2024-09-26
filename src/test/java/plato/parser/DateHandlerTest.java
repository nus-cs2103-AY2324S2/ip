package plato.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import plato.PlatoException;

public class DateHandlerTest {

    @Test
    public void check_date_success() throws PlatoException {
        assertEquals(LocalDate.of(2023, 4, 12), DateHandler.checkDate("12-04-23").orElse(LocalDate.of(1999, 1, 1)));
    }

    @Test
    public void checkTime_success() throws PlatoException {
        assertEquals(LocalTime.of(18, 0), DateHandler.checkTime("1800 12-04-23").orElse(LocalTime.of(0, 0)));
    }

    @Test
    public void checkTime_dateyearfull_success() throws PlatoException {
        assertEquals(LocalTime.of(23, 59), DateHandler.checkTime("2359 15/02/2024").orElse(LocalTime.of(0, 0)));
    }
}
