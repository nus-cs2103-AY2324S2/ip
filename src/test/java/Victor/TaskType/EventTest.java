package victor.tasktype;

import victor.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTest() {
        TaskList tasks = new TaskList();
        Task eventTest = new Event("Event Test 1", false, "4th April 12pm ", "4pm");

        tasks.addTask(eventTest);

        assertEquals(tasks.getPosValue(0),eventTest);
        assertEquals("[E][ ] Event Test 1 (from: 4th April 12pm to: 4pm)",eventTest.toString());
    }
}
