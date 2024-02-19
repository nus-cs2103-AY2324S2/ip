package judy.command;

import judy.commands.AddTaskCommand;
import judy.storage.Storage;
import judy.task.*;
import judy.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddTaskCommandTest {
    @AfterEach
    public void clean() {
        File file = new File("testFile.txt");
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }
    @Test
    public void execute_addTodo_commandSuccessful() {
        Ui ui = new Ui();
        Todo t = new Todo(" Sample Todo");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("testFile.txt");
        AddTaskCommand addTaskCommand = new AddTaskCommand(t, taskList);
        addTaskCommand.execute(storage, ui);

        ArrayList<Task> updatedlist = taskList.getTaskList();
        assertEquals(1, taskList.getSize());
        assertEquals(t, updatedlist.get(0));
    }
    @Test
    public void execute_addDeadLine_commandSuccessful() {
        Ui ui = new Ui();
        String by = "2026-06-06 1159";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime byDateTime = LocalDateTime.parse(by, pattern);
        Deadline d = new Deadline(" Sample Deadline", byDateTime);
        TaskList taskList = new TaskList();
        Storage storage = new Storage("testFile.txt");
        AddTaskCommand addTaskCommand = new AddTaskCommand(d, taskList);
        addTaskCommand.execute(storage, ui);

        ArrayList<Task> updatedlist = taskList.getTaskList();
        assertEquals(1, taskList.getSize());
        assertEquals(d, updatedlist.get(0));
    }
    @Test
    public void execute_addEvent_commandSuccessful() {
        Ui ui = new Ui();
        String from = "2026-06-06 1600";
        String to = "2026-06-06 1900";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime fromDateTime = LocalDateTime.parse(from, pattern);
        LocalDateTime toDateTime = LocalDateTime.parse(to, pattern);
        Event e = new Event(" Sample Event", fromDateTime, toDateTime);
        TaskList taskList = new TaskList();
        Storage storage = new Storage("testFile.txt");
        AddTaskCommand addTaskCommand = new AddTaskCommand(e, taskList);
        addTaskCommand.execute(storage, ui);

        ArrayList<Task> updatedlist = taskList.getTaskList();
        assertEquals(1, taskList.getSize());
        assertEquals(e, updatedlist.get(0));
    }


}
