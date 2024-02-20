package notduke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    private LocalDateTime by = LocalDateTime.of(2024, 1, 1, 0, 0);
    private Deadlines test = new Deadlines("Test", by, false);

    @Test
    public void testSaveOutput() {
        assertEquals(test.saveOutput(), String.format("D | 0 | Test | %s", by));
    }

    @Test
    public void testTaskInfo() {
        assertEquals(test.taskInfo(), "[D][ ] Test (by: Jan 1 2024, 0000hrs)"
                + System.lineSeparator());
    }

    @Test
    public void testHappenOn() {
        LocalDate withinDate1 = LocalDate.of(2024, 1, 1);
        assertEquals(test.happenOn(withinDate1), "[D][ ] Test (by: Jan 1 2024, 0000hrs)" + System.lineSeparator());
        LocalDate notWithinDate = LocalDate.of(2024, 2, 1);
        assertEquals(test.happenOn(notWithinDate), "");
    }
}
