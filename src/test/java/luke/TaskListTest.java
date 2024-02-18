package luke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate date1 = LocalDate.parse("27-08-2001", formatter);
    LocalDate date2 = LocalDate.parse("28-08-2001", formatter);

    Task[] tasks = {
            new Todo("stuff"),
            new Deadline("homework", date1),
            new Event("holiday", date1, date2)
    };

    @Test
    public void addTasks_validTasks_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        for (int i = 0; i < tasks.length; i++) {
            taskList.addTask(tasks[i]);
            assertEquals(tasks[i], taskList.getTasks().get(i));
        }
    }

    @Test
    public void addTasks_invalidTasks_failure() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(null);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void markTask_validTasks_success() throws TasklistException {
        TaskList taskList = new TaskList(new ArrayList<>());
        for (int i = 0; i < tasks.length; i++) {
            taskList.addTask(tasks[i]);
            assertEquals(false, taskList.getTasks().get(i).isDone);
        }
        for (int i = 0; i < tasks.length; i++) {
            taskList.markTask(i);
            assertEquals(true, taskList.getTasks().get(i).isDone);
        }
    }

    @Test
    public void deleteTasks_deleteExistingTasks_success() throws TasklistException {
        TaskList taskList = new TaskList(new ArrayList<>());
        for (int i = 0; i < tasks.length; i++) {
            taskList.addTask(tasks[i]);
        }
        for (int i = tasks.length - 1; i >= 0; i--) {
            taskList.deleteTask(i);
            assertEquals(i, taskList.getTasks().size());
        }
    }

}
