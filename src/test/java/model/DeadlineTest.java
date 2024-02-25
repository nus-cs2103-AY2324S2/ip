package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void dateParsing_dateParsedCorrectlyForSaving(){
        Deadline deadline = new Deadline("test description", "2024-02-01");
        assertEquals("D | 0 | test description | 2024-02-01 | ", deadline.fileSavingString());
    }

    @Test
    public void dateParsing_dateParsedCorrectlyForPrinting(){
        Deadline deadline = new Deadline("test description", "2024-02-01");
        assertEquals("[D][ ] test description (by: Feb 01 2024 0000)", deadline.toString());
    }

    @Test
    public void datetimeParsing_datetimeParsedCorrectlyForPrinting(){
        Deadline deadline = new Deadline("test description", "2024-03-05 1800");
        assertEquals("[D][ ] test description (by: Mar 05 2024 1800)", deadline.toString());
    }

}





