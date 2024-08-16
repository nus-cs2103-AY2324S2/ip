package gmo.command;

import gmo.task.Task;
import gmo.task.ToDo;
import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;

/**
 * Represents a to-do command to be executed.
 */
public class ToDoCommand extends Command {

    private final String desc;

    /**
     * Constructor for a to-do command.
     *
     * @param desc The description of the task.
     */
    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the to-do command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(this.desc);
        tasks.add(newTask);
        return ui.printAddTask(newTask, tasks.size());
    }
}
