package yoda.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {

    @Test
    public void addTask_newTask_taskAdded() throws Exception {
        TaskList taskList = new TaskList(null);
        Todo todo = new Todo("Read book");

        taskList.addTask(todo);

        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.getTask(1));
    }

    @Test
    public void addTask_nullTask_noChange() {
        TaskList taskList = new TaskList(null);

        taskList.addTask(null);

        assertEquals(0, taskList.size());
    }
}
