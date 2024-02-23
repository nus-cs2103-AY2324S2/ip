package notduke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventsTest {
    private LocalDateTime from = LocalDateTime.of(2024, 1, 1, 0, 0);
    private LocalDateTime to = LocalDateTime.of(2024, 1, 30, 23, 59);
    private Events test = new Events("Test", from, to, false);

    @Test
    public void testSaveOutput() {
        assertEquals(test.saveOutput(), String.format("E | 0 | Test | %s/%s", from, to));
    }

    @Test
    public void testTaskInfo() {
        assertEquals(test.taskInfo(), "[E][ ] Test (from: Jan 1 2024, 0000hrs to: Jan 30 2024, 2359hrs)"
                + System.lineSeparator());
    }

    @Test
    public void testHappenOn() {
        LocalDate withinDate1 = LocalDate.of(2024, 1, 15);
        assertEquals(test.happenOn(withinDate1), "[E][ ] Test (from: Jan 1 2024, 0000hrs to: Jan 30 2024, 2359hrs)"
                + System.lineSeparator());
        LocalDate withinDate2 = LocalDate.of(2024, 1, 1);
        assertEquals(test.happenOn(withinDate2), "[E][ ] Test (from: Jan 1 2024, 0000hrs to: Jan 30 2024, 2359hrs)"
                + System.lineSeparator());
        LocalDate withinDate3 = LocalDate.of(2024, 1, 30);
        assertEquals(test.happenOn(withinDate3), "[E][ ] Test (from: Jan 1 2024, 0000hrs to: Jan 30 2024, 2359hrs)"
                + System.lineSeparator());
        LocalDate notWithinDate = LocalDate.of(2024, 2, 1);
        assertEquals(test.happenOn(notWithinDate), "");
    }
}
