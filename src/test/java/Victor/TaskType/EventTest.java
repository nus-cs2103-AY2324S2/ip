package victor.tasktype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import victor.tasklist.TaskList;

public class EventTest {
    @Test
    public void firstEventTest() {
        TaskList tasks = new TaskList();
        Task eventTest = new Event("Event Test 1", true, "2024-04-04 11:00", "2024-04-04 16:00");

        tasks.addTask(eventTest);

        assertEquals(tasks.getPosValue(0), eventTest);
        assertEquals("[E][X] Event Test 1 (from: Apr 04 2024 11:00 am to: Apr 04 2024 16:00 pm)", eventTest.toString());
    }
}
