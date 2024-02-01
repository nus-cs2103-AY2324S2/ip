package botbot.task;

import botbot.exception.InvalidDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getRepToDoTest() {
        Task test = new ToDo("test");
        assertEquals(test.getRep(), "[T][ ] test");
    }

    @Test
    public void getRepDeadlineTest() throws InvalidDateException {
        Task test = new Deadline("test", "2022-02-02 14:00");
        assertEquals(test.getRep(), "[D][ ] test (by: 14:00 2022-02-02)");
    }
}
