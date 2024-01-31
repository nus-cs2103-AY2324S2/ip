package commands;

import exception.TodoFormatException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private static final String SUCCESS_MESSAGE = "Got it. Uncle added this todo:\n\t\t %s"
            + "\n\t Now you have %s task(s) in the list.";

    private final String message;

    public TodoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws TodoFormatException {
        if (message.isEmpty()) {
            throw new TodoFormatException();
        }
        Task todo = new Todo(message);
        tasks.addTasks(todo);
        try {
            storage.appendToFile(tasks);
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }
        ui.showToUser(String.format(SUCCESS_MESSAGE, todo, tasks.numTasks()));
    };
}