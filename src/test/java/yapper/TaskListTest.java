package yapper;

import yapper.tasks.TaskList;
import yapper.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test

    void testAddTaskShouldIncreaseTaskListSize() {
        // Arrange
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo test", false);

        // Act
        taskList.addTask(todo);

        // Assert
        assertEquals(1, taskList.getTasks().size());
    }
}

