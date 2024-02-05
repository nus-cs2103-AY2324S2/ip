package chatbot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddToDoTaskWithNonEmptyDescription() {
        TaskList taskList = new TaskList();
        String result = taskList.addList("todo Buy groceries");
        assertEquals("Got it. I've added this task:\n  [T][ ] Buy groceries\nNow you have 1 task in the list.", result);
    }

    @Test
    public void testAddToDoTaskWithEmptyDescription() {
        TaskList taskList = new TaskList();
        String result = taskList.addList("todo");
        assertEquals("Sorry Master Bruce. The description of a todo cannot be empty.", result);
    }

    @Test
    public void testAddDeadlineWithoutByKeyword() {
        TaskList taskList = new TaskList();
        String result = taskList.addList("deadline Return book 22/12/2021 1800");
        assertEquals("Sorry Master Bruce. Please specify the due date or time by including /by due-date.", result);
    }

    @Test
    public void testAddEventWithoutFromKeyword() {
        TaskList taskList = new TaskList();
        String result = taskList.addList("event Project meeting 12/12/2023 0900 /to 12/12/2023 1200");
        assertEquals("Sorry Master Bruce. Please specify the description, start time, and end time of the event by including /from start-time /to end-time.", result);
    }

    @Test
    public void testAddEventWithoutToKeyword() {
        TaskList taskList = new TaskList();
        String result = taskList.addList("event Project meeting /from 12/12/2023 0900 12/12/2023 1200");
        assertEquals("Sorry Master Bruce. Please specify the description, start time, and end time of the event by including /from start-time /to end-time.", result);
    }

    @Test
    public void testAddEventWithoutDescription() {
        TaskList taskList = new TaskList();
        String result = taskList.addList("event /from 12/12/2023 0900 /to 12/12/2023 1200");
        assertEquals("Sorry Master Bruce. The description of an event cannot be empty.", result);
    }

}