package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class TaskListTest {
    private static LocalDate DATE = LocalDate.parse("1999-12-03");
    private static String EXPECTED_OUTPUT = "1.[D] Return Book by Dec 3 1999\n2.[T] Read has yet to be completed\n[E] Eat Book by Dec 3 1999\n";
    
    @Test
    public void testShowList() {
        Deadline deadline = new Deadline("Return Book", DATE);
        Todo todo = new Todo("Read");
        Event event = new Event("Eat Book", DATE);

        TaskList taskList = new TaskList();

        taskList.addTask(deadline);
        taskList.addTask(todo);
        taskList.addTask(event);

        String output = taskList.showList();

        assertEquals(EXPECTED_OUTPUT, output);
    }
}
