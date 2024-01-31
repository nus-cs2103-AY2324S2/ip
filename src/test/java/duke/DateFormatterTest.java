package duke;

import duke.others.DateFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateFormatterTest {

    private DateFormatter correctForm = new DateFormatter("2019-10-15");
    private DateFormatter incorrectForm = new DateFormatter("2019-10-1");

    @Test
    public void isValidDate_forCorrectDate_Test(){
        assertEquals(true,correctForm.isValidDate());
    }

    @Test
    public void isValidDate_forIncorrectDate_Test(){
        assertEquals(false,incorrectForm.isValidDate());
    }

    @Test
    public void convertDateTest(){
        assertEquals("Oct 15 2019",correctForm.convertDate());
    }
}
