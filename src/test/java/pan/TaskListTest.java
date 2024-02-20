package pan;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pan.enums.TaskStatus;
import pan.exceptions.TaskIndexException;

public class TaskListTest {

    @Test
    public void testMark_withInvalidIndex() {
        int invalidIndex = -1;
        TaskList taskList = new TaskList(new Storage());
        assertThrows(TaskIndexException.class, () -> taskList.mark(invalidIndex));
    }

    @Test
    public void testMark_withOutOfBoundsIndex() {
        TaskList taskList = new TaskList(new Storage());
        int invalidIndex = taskList.getTasks().size() + 1;
        assertThrows(TaskIndexException.class, () -> taskList.mark(invalidIndex));
    }

    @Test
    public void testMark_withValidIndex() {
        TaskList taskList = new TaskList(new Storage());
        taskList.add(new ToDos("", TaskStatus.INCOMPLETE));
        int validIndex = taskList.getTasks().size();
        assertDoesNotThrow(() -> taskList.mark(validIndex));
        assertDoesNotThrow(() -> taskList.delete(validIndex));
    }
}
