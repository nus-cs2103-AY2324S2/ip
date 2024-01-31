package chatbot.value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateStringValueTest {
    @Test
    public void of_date_expectedBehaviour() {
        DateStringValue d = DateStringValue.of("Jan 02 2023");
        assertEquals("Jan 2 2023", d.toString());

        d = DateStringValue.of("2023-01-02");
        assertEquals("Jan 2 2023", d.toString());
    }

    @Test
    public void of_nonDate_expectedBehaviour() {
        DateStringValue d = DateStringValue.of("Jan 02 23");
        assertEquals("Jan 02 23", d.toString());

        d = DateStringValue.of("2023/01/02");
        assertEquals("2023/01/02", d.toString());

        d = DateStringValue.of("today");
        assertEquals("today", d.toString());

        d = DateStringValue.of("");
        assertEquals("", d.toString());
    }
}
