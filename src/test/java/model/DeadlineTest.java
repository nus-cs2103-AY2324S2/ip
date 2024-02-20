package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void dateParsing_properDate_dateParsedCorrectlyForSaving(){
        Deadline deadline = new Deadline("test description", "2024-02-01");
        assertEquals("D | 0 | test description | 2024-02-01 | ", deadline.fileSavingString());
    }

    @Test
    public void dateParsing_properDatetime_dateParsedCorrectlyForPrinting(){
        Deadline deadline = new Deadline("test description", "2024-02-01");
        assertEquals("[D][ ] test description (by: Feb 01 2024 0000)", deadline.toString());
    }
}





