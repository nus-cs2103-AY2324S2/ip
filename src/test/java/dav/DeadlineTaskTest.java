package dav;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.format.DateTimeFormatter;

public class DeadlineTaskTest {

    @Test
    public void testParseTask() {
        String taskData = "D | 1 | Finish project | 2022-12-31 2359 | work, important";
        DeadlineTask deadlineTask = (DeadlineTask) DeadlineTask.parseTask(taskData);

        assertNotNull(deadlineTask);
        assertEquals("Finish project", deadlineTask.getDescription());
        assertEquals("2022-12-31 2359",
                        deadlineTask.getByDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        assertEquals(2, deadlineTask.getTags().size());
        assertTrue(deadlineTask.getTags().contains("work"));
        assertTrue(deadlineTask.getTags().contains("important"));
    }

    @Test
    public void testToDataString() {
        DeadlineTask deadlineTask = new DeadlineTask("Submit report", "2023-02-28", "1800");
        deadlineTask.addTag("urgent");

        String expectedDataString = "D | 0 | Submit report | 2023-02-28 1800 | urgent";
        assertEquals(expectedDataString, deadlineTask.toDataString());
    }
}

