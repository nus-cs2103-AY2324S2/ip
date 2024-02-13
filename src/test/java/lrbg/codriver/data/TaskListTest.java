package lrbg.codriver.data;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test Class for TaskList.
 */
public class TaskListTest {
    @Test
    public void toSaveStringTest() {
        try {
            TaskList tl = new TaskList();
            tl.addTask(new Todo("1234"));
            tl.addTask(new Deadline("4567", LocalDate.parse("2021-08-25")));
            tl.addTask(new Event("8901", LocalDate.parse("2021-08-25"), LocalDate.parse("2021-08-26")));
            tl.markTask(1);
            assertEquals("T|1|1234\nD|0|4567|2021-08-25\nE|0|8901|2021-08-25~2021-08-26\n", tl.toFileSaveString());
        } catch (Exception e) {
            fail();
        }
    }

}
