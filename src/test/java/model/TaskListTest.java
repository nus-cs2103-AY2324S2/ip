package model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    public void init() {
        tasks = new TaskList();
    }

    @Test
    public void toString_emptyTaskList_success() {
        assertEquals("", tasks.toString());
    }

    @Test
    public void addTask_addToDoTask_success() {
        assertAll("add ToDo task tests",
            () -> assertTrue(tasks.addTask("ToDo test") instanceof ToDo,
                    "Task added is not an instance of ToDo.class"),
            () -> assertEquals(1, tasks.size(), "Task was not added")
        );
    }

    @Test
    public void addTask_addDeadlineTask_success() {
        LocalDateTime ldt = mock(LocalDateTime.class);
        assertAll("add Deadline task tests",
            () -> assertTrue(tasks.addTask("Deadline test", ldt) instanceof Deadline,
                    "Task added is not an instance of Deadline.class"),
            () -> assertEquals(1, tasks.size(), "Task was not added")
        );
    }

    @Test
    public void addTask_addEventTask_success() {
        LocalDateTime ldt1 = mock(LocalDateTime.class);
        LocalDateTime ldt2 = mock(LocalDateTime.class);
        assertAll("add ToDo task tests",
            () -> assertTrue(tasks.addTask("Event test", ldt1, ldt2) instanceof Event,
                    "Task added is not an instance of Event.class"),
            () -> assertEquals(1, tasks.size(), "Task was not added")
        );
    }
}
