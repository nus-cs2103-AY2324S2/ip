package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_taskAdded_success() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read book", false);
        taskList.addTask(task);

        assertEquals(1, taskList.getTasks().size());
        assertEquals(task, taskList.getTasks().get(0));
    }
}
