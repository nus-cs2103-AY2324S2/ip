import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import simpli.tasks.TaskList;

public class TaskListTest {
    @Test
    public void markTest() {
        TaskList taskList = new TaskList();

        taskList.addTodo(new String[] {"todo", "0", "test1", "", ""});

        taskList.mark(1);
        assertTrue(taskList.tasks().get(0).isDone());
    }

    @Test
    public void unmarkTest() {
        TaskList taskList = new TaskList();

        taskList.addTodo(new String[] {"todo", "0", "test1", "", ""});

        taskList.mark(1);
        taskList.unmark(1);
        assertFalse(taskList.tasks().get(0).isDone());
    }

    @Test
    public void addTodoTest() {
        TaskList taskList = new TaskList();

        taskList.addTodo(new String[] {"todo", "0", "test", "", ""});

        assertEquals("[T][ ] test", taskList.tasks().get(0).toString());
    }

    @Test
    public void addDeadlineTest() {
        TaskList taskList = new TaskList();

        taskList.addDeadline(new String[] {"deadline", "0", "test", "12/2/2022 1200", ""},
                new LocalDateTime[] {LocalDateTime.parse("2022-02-12T12:00:00")});

        assertEquals("[D][ ] test (by: 12/2/2022 1200)", taskList.tasks().get(0).toString());
    }

    @Test
    public void addEventTest() {
        TaskList taskList = new TaskList();

        taskList.addEvent(new String[] {"event", "0", "test", "12/2/2022 1200", "24/3/2022 1200"},
                new LocalDateTime[] {LocalDateTime.parse("2022-02-12T12:00:00"),
                        LocalDateTime.parse("2022-03-24T12:00:00")});

        assertEquals("[E][ ] test (from: 12/2/2022 1200 to: 24/3/2022 1200)",
                taskList.tasks().get(0).toString());
    }
}
