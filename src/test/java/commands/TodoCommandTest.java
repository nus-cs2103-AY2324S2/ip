package commands;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import storage.Storage;
import task.Task;
import tasklists.TaskList;
import ui.Ui;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {

    @Test
    public void todoCommand_checkFields() {
        String input = "buy groceries";
        TodoCommand command = new TodoCommand(input);
        assertEquals("todo", TodoCommand.COMMAND);
    }

    @Test
    public void todoCommand_validInput_successful() throws DukeException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskLs = storage.readFile();
        List<Task> ls = taskLs.getList();
        int size = ls.size();
        String input = "read book";
        TodoCommand command = new TodoCommand(input);
        command.setData(taskLs);
        command.executeCommand();

        assertEquals(size+1, taskLs.getList().size());
        size = taskLs.getList().size();
        assertEquals("read book", taskLs.get(size-1).getTask());
        assertEquals(new Task("read book"), taskLs.get(0));
    }

    @Test
    public void todoCommand_invalidInput_throwsException() throws DukeException {
        try {
            Ui ui = new Ui();
            Storage storage = new Storage();
            TaskList taskLs = storage.readFile();

            String input = "";
            TodoCommand command = new TodoCommand(input);
            command.setData(taskLs);
            command.executeCommand();
        } catch (DukeException e) {
            assertEquals("Hey! You've just entered an unnamed task... Try to give a description/name of your task :)",
                    e.getMessage());
        }
    }
}
