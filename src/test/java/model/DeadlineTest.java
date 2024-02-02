package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void dateParsing_properDate_dateParsedCorrectlyForSaving(){
        Deadline deadline = new Deadline("test description", "2024-02-01");
        assertEquals(deadline.fileSavingString(), "D | 0 | test description | 2024-02-01");
    }

    @Test
    public void dateParsing_properDatetime_dateParsedCorrectlyForPrinting(){
        Deadline deadline = new Deadline("test description", "2024-02-01");
        assertEquals(deadline.toString(), "[D][ ] test description (by: Feb 01 2024 0000)");
    }
}





