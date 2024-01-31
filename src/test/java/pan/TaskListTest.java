package pan;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        int outOfBoundsIndex = 1;
        TaskList taskList = new TaskList(new Storage());
        assertThrows(TaskIndexException.class, () -> taskList.mark(outOfBoundsIndex));
    }

    @Test
    public void testMark_withValidIndex() {
        // user input would start from 1 in this case
        int validIndex = 1;
        TaskList taskList = new TaskList(new Storage());
        taskList.add(new Task("", TaskStatus.INCOMPLETE));
        assertEquals(taskList.getTasks().size(), 1);
        assertDoesNotThrow(() -> taskList.mark(validIndex));
    }
}
