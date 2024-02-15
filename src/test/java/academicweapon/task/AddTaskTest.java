package academicweapon.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Testing");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }
}
