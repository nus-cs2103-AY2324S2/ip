package pan;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import pan.exceptions.TaskIndexException;
import pan.enums.TaskStatus;

public class TaskListTest {

    @Test
    public void testMark_with_invalid_index() {
        int invalidIndex = -1;
        TaskList taskList = new TaskList(new Storage());
        assertThrows(TaskIndexException.class, () -> taskList.mark(invalidIndex));
    }

    @Test
    public void testMark_with_out_of_bounds_index() {
        int outOfBoundsIndex = 1;
        TaskList taskList = new TaskList(new Storage());
        assertThrows(TaskIndexException.class, () -> taskList.mark(outOfBoundsIndex));
    }

    @Test
    public void testMark_with_valid_index() {
        // user input would start from 1 in this case
        int validIndex = 1;
        TaskList taskList = new TaskList(new Storage());
        taskList.add(new Task("", TaskStatus.INCOMPLETE));
        assertEquals(taskList.getTasks().size(), 1);
        assertDoesNotThrow(() -> taskList.mark(validIndex));
    }
}
