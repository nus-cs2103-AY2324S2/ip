package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

public class TodoCommand extends Command {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Todo Command.
     *
     * @param description Description of Todo.
     * */
    public TodoCommand(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Todo Command.
     *
     * @param description Description of Todo.
     * @param isDone Boolean value if Event is done.
     * */
    public TodoCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Creates a new Todo and adds it to the current TaskList.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(description, isDone);
        tasks.addToList(task);
        return ui.addedTaskPrinter(task, tasks.taskNum());
    }

    /**
     * Informs if this command is an Exit command.
     *
     * @return Boolean value of true if this command is an exit command.
     * */
    @Override
    public boolean isExit() {
        return false;
    }
}
