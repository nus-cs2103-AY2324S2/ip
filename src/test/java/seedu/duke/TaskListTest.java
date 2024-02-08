package seedu.duke;

import seedu.duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void getSize_properAddition_success() throws Exception{
        assertEquals(0, new TaskList().getSize());
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        assertEquals(1, taskList.getSize());
    }
}
