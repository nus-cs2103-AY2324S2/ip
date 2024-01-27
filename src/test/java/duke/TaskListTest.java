package duke;

import duke.TaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;
import java.util.ArrayList;

public class TaskListTest {

    @Test
    void testGetLength() {
        ArrayList<Task> input = new ArrayList<>();
        input.add(new Todo("todo task"));
        input.add(new Deadline("deadline task", LocalDate.parse("2002-11-21")));
        TaskList tl = new TaskList(input);

        assertEquals(2, tl.getListLength());

        input.remove(0);
        tl = new TaskList(input);
        assertEquals(1, tl.getListLength());
    }
}
