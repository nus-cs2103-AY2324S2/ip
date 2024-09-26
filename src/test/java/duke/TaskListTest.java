package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void testAdd() throws DukeException {
        TaskList list = new TaskList();
        ToDo todo = new ToDo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-24");
        Event event = new Event("project meeting", "2024-02-24 14:00", "2024-02-24 16:00");

        list.addTask(todo);
        list.addTask(deadline);
        list.addTask(event);

        assertEquals(3, list.size());
        assertEquals(todo, list.getTask(0));
        assertEquals(deadline, list.getTask(1));
        assertEquals(event, list.getTask(2));
        assertEquals("[T][ ] read book", list.getTask(0).toString());
        assertEquals("[D][ ] return book (by: Feb 24 2024)", list.getTask(1).toString());
        assertEquals("[E][ ] project meeting (from: Feb 24 2024 14:00 to: Feb 24 2024 16:00)", list.getTask(2).toString());
    }
}
