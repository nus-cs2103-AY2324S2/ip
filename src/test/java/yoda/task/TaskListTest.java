package yoda.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import yoda.exceptions.InvalidTaskException;


public class TaskListTest {

    @Test
    public void addTask_newTask_taskAdded() throws Exception {
        TaskList taskList = new TaskList(null);
        Todo todo = new Todo("Read book");

        taskList.addTask(todo);

        assertEquals(1, taskList.getSize());
        assertEquals(todo, taskList.getTask(1));
    }

    @Test
    public void addTask_nullTask_noChange() {
        TaskList taskList = new TaskList(null);

        taskList.addTask(null);

        assertEquals(0, taskList.getSize());
    }

    @Test
    void deletingInvalidTask_ShouldThrowException() {
        TaskList taskList = new TaskList(null);
        assertThrows(InvalidTaskException.class, () -> {
            taskList.deleteTask(1);
        });
    }

    @Test
    void markingTaskAsDone_ShouldChangeTaskStatus() throws InvalidTaskException {
        TaskList taskList = new TaskList(null);
        Todo todo = new Todo("read book");
        taskList.addTask(todo);
        taskList.markTaskAsDone(1);
        assertTrue(taskList.getTask(1).isDone());
    }

    @Test
    void findingTaskByKeyword_ShouldReturnCorrectTasks() {
        TaskList taskList = new TaskList(null);
        Todo todo1 = new Todo("read book");
        Todo todo2 = new Todo("write book");
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        String foundTasks = taskList.findTasks("book");
        assertTrue(foundTasks.contains(todo1.getDescription()));
        assertTrue(foundTasks.contains(todo2.getDescription()));
    }
}
