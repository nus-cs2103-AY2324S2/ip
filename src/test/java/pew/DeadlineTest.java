package pew;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadline_getTask_success(){
        assertEquals("1. [D][ ] 2103T test (28/2/2024)", new Deadline(0, "2103T test", "28/2/2024").getTask());
    }

    @Test
    public void deadline_getTask_invalidDate_exceptionThrown(){
        try {
            PewException.validateDateTime("111/11/1111");
            assertEquals("fail", new Deadline(1, "fail", "111/11/1111").getTask());
        } catch (PewException d) {
            String[] dateFormats = {
                    "M/d/yyyy[ HHmm]",
                    "yyyy-MM-dd[ HHmm]",
                    "dd-MM-yyyy[ HHmm]",
                    "d/M/yyyy[ HHmm]",
                    "M-d-yyyy[ HHmm]",
                    "d-M-yyyy[ HHmm]"
            };
            assertEquals("Invalid date and time format, use the following formats: " +
                    "\n" + Arrays.toString(dateFormats), d.getMessage());
        }
    }
}
