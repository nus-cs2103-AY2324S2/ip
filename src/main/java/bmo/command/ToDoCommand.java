package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;
import bmo.task.Task;
import bmo.task.ToDo;

/**
 * Represents a to-do command to be executed.
 */
public class ToDoCommand extends Command{

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(this.desc);
        tasks.add(newTask);
        ui.printAddTask(newTask, tasks.size());
    }
}
