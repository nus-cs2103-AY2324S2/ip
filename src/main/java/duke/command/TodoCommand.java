package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/*
 * This TodoCommand class is a subclass of Command class. It is used to create a new Todo task
 * and add it to the task list.
 * It takes in a description String.
 */
public class TodoCommand extends Command {
    protected String description;

    /*
     * Constructs TodoCommand object with description as a String.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.showTaskAdded(todo, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}