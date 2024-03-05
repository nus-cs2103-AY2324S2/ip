package yippee.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yippee.exceptions.YippeeException;

public class EventTest {
    @Test
    public void createCorrectTest() {
        String taskName = "a task";
        try {
            Event testEvent = new Event(taskName, "2022-12-31", "2023-01-01");
            assertEquals("[E] [ ] a task (from: Dec 31 2022 to: Jan 1 2023)",
                    testEvent.toString());
        } catch (YippeeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void createWrongTest() {
        String taskName = "a task";
        try {
            Event testEvent = new Event(taskName, "2022-12-300", "2023-01-01");
        } catch (YippeeException e) {
            assertEquals(
                    "Invalid input format for date :( Please use the format yyyy-mm-dd instead!",
                    e.getMessage());
        }
    }
}
