package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.others.DateFormatter;



public class DateFormatterTest {

    private DateFormatter correctForm = new DateFormatter("2019-10-15");
    private DateFormatter incorrectForm = new DateFormatter("2019-10-1");

    @Test
    public void isValidCorrectDateTest() {
        assertEquals(true, correctForm.hasValidDate());
    }

    @Test
    public void isValidIncorrectDateTest() {
        assertEquals(false, incorrectForm.hasValidDate());
    }

    @Test
    public void convertDateTest() {
        assertEquals("Oct 15 2019", correctForm.convertDate());
    }
}
