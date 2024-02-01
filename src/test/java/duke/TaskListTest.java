package duke;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTaskShouldIncreaseSize() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sample Task");

        int initialSize = taskList.getSize();
        taskList.addTask(task);

        assertEquals(initialSize + 1, taskList.getSize());
    }

    @Test
    public void deleteTaskShouldRemoveTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sample Task");
        taskList.addTask(task);

        int initialSize = taskList.getSize();
        taskList.deleteTask(0, new Ui());

        assertEquals(initialSize - 1, taskList.getSize());
    }

}
