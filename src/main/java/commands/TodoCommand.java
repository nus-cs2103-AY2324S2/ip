package commands;

import exception.TodoFormatException;
import exception.UncleBobException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
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
            storage.appendToFile(todo.getSymbol() + "/" + todo.getStatus() + "/"
                + todo.getDescription() + "\n");
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    };
}