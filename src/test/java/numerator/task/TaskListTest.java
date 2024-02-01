package numerator.task;

import numerator.task.Task;
import numerator.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void markAsDone_emptyList_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markAsDone(0));
    }

    @Test
    public void markAsDone_negativeOutOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.addToDo("test");
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markAsDone(-1));
    }

    @Test
    public void markAsDone_positiveOutOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.addToDo("test");
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markAsDone(99));
    }

    @Test
    public void markAsDone_validTask_success() {
        TaskList taskList = new TaskList();
        Task task = taskList.addToDo("test");
        assert task != null;
        assert (!task.isDone);
        taskList.markAsDone(0);
        assert (task.isDone);

    }


}














