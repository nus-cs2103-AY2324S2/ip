package seedu.klara;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.klara.task.Todo;

public class TaskListTest {
    @Test
    public void getSize_properAddition_success() throws Exception {
        assertEquals(0, new TaskList().getSize());
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void getSize_properDeletion_success() throws Exception {
        assertEquals(0, new TaskList().getSize());
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void getTask_properIndex_success() throws Exception {
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void markTaskDone_properIndex_success() throws Exception {
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.markTaskDone(0);
        assertEquals(true, taskList.getTask(0).isDone());
    }

    @Test
    public void markTaskUndone_properIndex_success() throws Exception {
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.markTaskDone(0);
        taskList.markTaskUndone(0);
        assertEquals(false, taskList.getTask(0).isDone());
    }

    @Test
    public void deleteTask_properIndex_success() throws Exception {
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void addTask_properTask_success() throws Exception {
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void getList_properList_success() throws Exception {
        Todo todo = new Todo("hi");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        assertEquals(todo, taskList.getList().get(0));
    }

}
