package guanguan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void getTaskTest_success() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("Complete CS2103T");
        taskList.add(task);

        assertEquals(task, taskList.get(0));
    }

    @Test
    public void getTaskTest_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<>());
            Task task = new Todo("Complete CS2103T");
            taskList.add(task);
            assertEquals(task, taskList.get(2));
            fail();
        } catch (GgException e) {
            assertEquals("Invalid task ID", e.getMessage());
        }
    }

    @Test
    public void getAllTaskTest() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>());

        Task task = new Todo("Complete CS2103T");
        Deadline deadline = new Deadline("Dance Practice", LocalDate.parse("2024-01-01"));

        taskList.add(task);
        taskList.add(deadline);

        assertEquals(2, taskList.size());
        assertEquals(task, taskList.get(0));
        assertEquals(deadline, taskList.get(1));
    }

    @Test
    public void addTaskTest() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("Complete CS2103T");
        taskList.add(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void deleteTaskTest_success() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>());

        Task task = new Todo("Complete CS2103T");
        Deadline deadline = new Deadline("Dance Practice", LocalDate.parse("2024-01-01"));

        taskList.add(task);
        taskList.add(deadline);

        taskList.remove(0);

        assertEquals(1, taskList.size());
        assertEquals(deadline, taskList.get(0));
    }

    @Test
    public void deleteTaskTest_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<>());

            Task task = new Todo("Complete CS2103T");
            Deadline deadline = new Deadline("Dance Practice", LocalDate.parse("2024-01-01"));

            taskList.add(task);
            taskList.add(deadline);

            taskList.remove(5);
            fail();
        } catch (GgException e) {
            assertEquals("Invalid task ID", e.getMessage());
        }
    }

    @Test
    public void sizeTest() {
        TaskList taskList = new TaskList(new ArrayList<>());

        Task task = new Todo("Complete CS2103T");
        Deadline deadline = new Deadline("Dance Practice", LocalDate.parse("2024-01-01"));

        taskList.add(task);
        taskList.add(deadline);

        assertEquals(2, taskList.size());
    }

    @Test
    public void isEmptyTest() {
        TaskList taskList = new TaskList(new ArrayList<>());

        assertTrue(taskList.isEmpty());
    }
}
