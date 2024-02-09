package duke;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    @Test
    public void EmptyListTest() {
        TaskList t1 = new TaskList();
        TaskList t2 = new TaskList();
        assertEquals(t1.size(),t2.size());
    }

    @Test
    public void AddToListTest() {
        TaskList t1 = new TaskList();
        int sizeBeforeAdd = t1.size();
        t1.addTask(new Todo("Search for internships!"));
        int sizeAfterAdd = t1.size();
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }

}
