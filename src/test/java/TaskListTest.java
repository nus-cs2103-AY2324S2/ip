import org.junit.jupiter.api.Test;
import solaire.data.exception.SolaireException;
import solaire.data.task.Deadline;
import solaire.data.task.Event;
import solaire.data.task.Task;
import solaire.data.task.Todo;
import solaire.tasklist.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testDeleteOutOfBound() throws SolaireException {

        // dummy data
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Todo("eat lunch"));
        tasks.add(new Deadline("assignment", "2024-02-27"));
        tasks.add(new Event("coldplay", "tomorrow", "end Feb"));

        TaskList tl = new TaskList(tasks);

        // test case 1
        String input1 = "delete 4";
        tl.processRemoveFromList(input1);

        assertEquals(tl.getTaskList().size(), 3);
        // test case 2
        String input2 = "delete -1";
        tl.processRemoveFromList(input2);
        assertEquals(tl.getTaskList().size(), 3);
    }
}
