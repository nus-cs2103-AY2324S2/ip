package service;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceTest {
    // NOTE: Test Isolation, JUnit creates a new instance for each test
    private final TaskService taskService = new TaskService();

    @Test
    public void AddTodoTest() throws IOException {
        // NOTE: At the beginning, there should be no tasks
        assertEquals(
                this.taskService.getAllTasks(),
                ""
        );

        String taskName = "Some Task";
        this.taskService.addTodo(taskName);
    }
}
