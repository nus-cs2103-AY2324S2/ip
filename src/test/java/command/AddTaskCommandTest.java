package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import iggly.command.AddTaskCommand;
import iggly.duke.Storage;
import iggly.model.Deadline;
import iggly.model.Event;
import iggly.model.Task;
import iggly.model.TaskList;
import iggly.model.ToDo;

public class AddTaskCommandTest {
    @AfterEach
    public void cleanup() {
        File file = new File("test.dat");
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }
    @Test
    public void executeAddTodoTest() {
        ToDo todo = new ToDo("todo");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("test.dat");

        AddTaskCommand addTaskCommand = new AddTaskCommand(todo, taskList);
        addTaskCommand.execute(storage);

        ArrayList<Task> updatedTaskList = taskList.getTaskList();
        assertEquals(1, updatedTaskList.size());
        assertEquals(todo, updatedTaskList.get(0));
    }

    @Test
    public void executeAddEventTest() {
        String from = "01-01-2024 0000";
        String to = "01-01-2024 2359";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HHmm");
        LocalDateTime startTime = LocalDateTime.parse(from, formatter);
        LocalDateTime endTime = LocalDateTime.parse(to, formatter);
        Event event = new Event("event", startTime, endTime);
        TaskList taskList = new TaskList();
        Storage storage = new Storage("test.dat");

        AddTaskCommand addTaskCommand = new AddTaskCommand(event, taskList);
        addTaskCommand.execute(storage);

        ArrayList<Task> updatedTaskList = taskList.getTaskList();
        assertEquals(1, updatedTaskList.size());
        assertEquals(event, updatedTaskList.get(0));
    }

    @Test
    public void executeAddDeadlineTest() {
        String by = "01-01-2024 0000";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HHmm");
        LocalDateTime time = LocalDateTime.parse(by, formatter);
        Deadline deadline = new Deadline("deadline", time);
        TaskList taskList = new TaskList();
        Storage storage = new Storage("test.dat");

        AddTaskCommand addTaskCommand = new AddTaskCommand(deadline, taskList);
        addTaskCommand.execute(storage);

        ArrayList<Task> updatedTaskList = taskList.getTaskList();
        assertEquals(1, updatedTaskList.size());
        assertEquals(deadline, updatedTaskList.get(0));
    }
}
