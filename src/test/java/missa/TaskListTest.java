package missa;

import missa.task.Deadline;
import missa.task.Event;
import missa.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testGetTasks() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("todo1"));
        assertEquals("1. [T][ ] todo1\n", tasks.getTasks());
    }

    @Test
    public void getUpdatedData_onlyTodoTasks() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("todo1"));
        assertEquals("T | 0 | todo1", tasks.getUpdatedData());
    }

    @Test
    public void getUpdatedData_onlyDeadlineTasks() {
        TaskList tasks = new TaskList();
        LocalDateTime ddlTime = LocalDateTime.of(
                LocalDate.of(2024,3,3), LocalTime.of(3, 0));
        tasks.addTask(new Deadline("ddl", ddlTime));
        assertEquals("D | 0 | ddl | 2024-03-03T03:00", tasks.getUpdatedData());
    }

    @Test
    public void getUpdatedData_onlyEventTasks() {
        TaskList tasks = new TaskList();
        LocalDateTime eventTime1 = LocalDateTime.of(
                LocalDate.of(2024,3,3), LocalTime.of(3, 0));
        LocalDateTime eventTime2 = LocalDateTime.of(
                LocalDate.of(2024,3,3), LocalTime.of(4, 0));
        tasks.addTask(new Event("event1", eventTime1, eventTime2));
        assertEquals("E | 0 | event1 | 2024-03-03T03:00 | 2024-03-03T04:00", tasks.getUpdatedData());
    }
}
