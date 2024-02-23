package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private final TaskList taskManager = new TaskList();

    @Test
    public void testGetTask() {
        // Retrieve task that doesn't exist
        try {
            taskManager.getTask(0);
            fail();
        } catch (Exception e) {
            assertEquals("Task not found!", e.getMessage());
        }

        // Retrieve task that exists
        taskManager.addTask(new Todo("Implement feature A"));
        String todoDescription = "";
        try {
            todoDescription = taskManager.getTask(1).getDescription();
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }
        assertEquals("Implement feature A", todoDescription);
    }

    @Test
    public void testAddTask() {
        assertEquals(0, taskManager.size());
        Todo task = new Todo("Revise for midterms");
        taskManager.addTask(task);
        assertEquals(1, taskManager.size());
    }

    @Test
    public void testSearchTasks() {
        taskManager.addTask(new Todo("Revise for midterms"));
        taskManager.addTask(new Deadline("Submit project proposal", "tomorrow"));
        taskManager.addTask(new Event("Group project meeting", "Mon 4pm", "5pm"));

        taskManager.searchTasks("project");
    }
 
    @Test
    public void testDeleteTask() {
        taskManager.addTask(new Deadline("Submit project proposal", "Mon 4pm"));

        // Delete existing task
        try {
            taskManager.removeTask(1);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }

        // Delete task that doesn't exist
        try {
            taskManager.removeTask(-1);
            fail();
        } catch (Exception e) {
            assertEquals("Task not found!", e.getMessage());
        }
    }

    @Test
    public void testMarkTask() {
        taskManager.addTask(new Deadline("Submit project proposal", "Mon 4pm"));

        // Mark task that doesn't exist
        try {
            taskManager.completeTask(5);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }

        // Mark task that exists
        try {
            taskManager.completeTask(1);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }

        // Check if task is marked successfully
        try {
            Task task = taskManager.getTask(1);
            assertEquals("Submit project proposal", task.getDescription());
            assertEquals(true, task.isDone());
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }
    }

    @Test
    public void testUnmarkTask() {
        taskManager.addTask(new Deadline("Submit project proposal", "Mon 4pm"));

        // Unmark task that doesn't exist
        try {
            taskManager.uncompleteTask(5);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }

        // Uncomplete task that exists
        try {
            taskManager.uncompleteTask(1);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }

        // Check if task has been marked
        try {
            Task task = taskManager.getTask(1);
            assertEquals("Submit project proposal", task.getDescription());
            assertEquals(false, task.isDone());
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }
    }


    @Test
    public void testUndo() {
        taskManager.addTask(new Todo("Revise for midterms"));
        taskManager.undo();

        assertEquals(0, taskManager.size());

        taskManager.addTask(new Deadline("Submit project proposal", "tomorrow"));
        taskManager.addTask(new Todo("Practice coding problems"));
        taskManager.addTask(new Event("Group project meeting", "Mon 4pm", "5pm"));

        // Delete existing task
        try {
            taskManager.removeTask(1);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }

        taskManager.undo();
        assertEquals(3, taskManager.size());
        taskManager.undo();
        assertEquals(2, taskManager.size());
        taskManager.undo();
        assertEquals(3, taskManager.size());

        // Mark and undo mark
        try {
            taskManager.completeTask(1);
            Task task = taskManager.getTask(1);
            assertEquals("Submit project proposal", task.getDescription());
            assertEquals(true, task.isDone());
            taskManager.undo();
            task = taskManager.getTask(1);
            assertEquals(false, task.isDone());
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }

        // Unmark and undo unmark
        try {
            taskManager.completeTask(2);
            taskManager.uncompleteTask(2);
            Task task = taskManager.getTask(2);
            assertEquals("Practice coding problems", task.getDescription());
            assertEquals(false, task.isDone());
            taskManager.undo();
            task = taskManager.getTask(2);
            assertEquals(true, task.isDone());
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Task not found!", e.getMessage());
        }
    }
}
