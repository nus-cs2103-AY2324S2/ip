package chatbot.value;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateStringValueTest {
    @Test
    public void of_date_expectedBehaviour() {
        DateStringValue d = new DateStringValue("Jan 02 2023");
        assertEquals("Jan 2 2023", d.toString());

        d = new DateStringValue("2023-01-02");
        assertEquals("Jan 2 2023", d.toString());

        d = new DateStringValue(" 2023-01-02 ");
        assertEquals("Jan 2 2023", d.toString());
    }

    @Test
    public void of_nonDate_expectedBehaviour() {
        DateStringValue d = new DateStringValue("Jan 02 23");
        assertEquals("Jan 02 23", d.toString());

        d = new DateStringValue("2023/01/02");
        assertEquals("2023/01/02", d.toString());

        d = new DateStringValue("today");
        assertEquals("today", d.toString());

        d = new DateStringValue("");
        assertEquals("", d.toString());
    }
}
