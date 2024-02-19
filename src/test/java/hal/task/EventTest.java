package hal.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest(){
        assertEquals(new Event(false, "project meeting",
                        "2019-10-15", "2019-10-19").toString(),
                "[E][ ] project meeting (from: Oct 15 2019 to: Oct 19 2019)");
        assertEquals(new Event(true, "2103 meeting",
                        "2019-11-15", "2019-12-19").toString(),
                "[E][X] 2103 meeting (from: Nov 15 2019 to: Dec 19 2019)");
    }

    @Test
    public void getFileStringTest(){
        assertEquals(new Event(false, "project meeting",
                        "2019-10-15", "2019-10-19").getFileString(),
                "E | 0 | project meeting | 2019-10-15 | 2019-10-19");
        assertEquals(new Event(true, "2103 meeting",
                        "2019-11-15", "2019-12-19").getFileString(),
                "E | 1 | 2103 meeting | 2019-11-15 | 2019-12-19");
    }
}
