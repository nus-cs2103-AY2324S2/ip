package yippee.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yippee.exceptions.YippeeException;

public class DeadlineTest {
    @Test
    public void createCorrectTest() {
        String taskName = "a task";
        try {
            Deadline testDeadline = new Deadline(taskName, "2022-12-31");
            assertEquals("[D] [ ] a task (by: Dec 31 2022)", testDeadline.toString());
        } catch (YippeeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void createWrongTest() {
        String taskName = "a task";
        try {
            Deadline testDeadline = new Deadline(taskName, "huh");
        } catch (YippeeException e) {
            assertEquals(
                    "Invalid input format for date :( Please use the format yyyy-mm-dd instead!",
                    e.getMessage());
        }
    }
}
