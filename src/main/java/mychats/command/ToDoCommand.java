package mychats.command;
import mychats.exception.MyChatsException;
import mychats.main.Storage;
import mychats.main.TaskList;
import mychats.main.Ui;
import mychats.task.Todo;

/**
 * Represents a command to add a to-do task to the task list .
 */
public class ToDoCommand extends Command {

    private String description;

    /**
     * Constructs a ToDoCommand with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the ToDoCommand by creating a new to-do task,
     * adding it to the task list, displaying the response using Ui,
     * and saving the updated task list using Storage.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws MyChatsException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MyChatsException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.addResponse(todo, tasks);
        storage.saveList(tasks.getTasks());
    }
}


