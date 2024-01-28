package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Task;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The AddCommand class defines a command to add tasks to be stored in the Duke
 * application
 *
 * @author Ryan NgWH
 */
public class AddCommand extends Command {
    /**
     * Task to be added
     */
    private Task task;

    /**
     * Creates a add command
     *
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Executes the add command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        // Add task
        taskList.addTask(task);

        // Save to file
        taskList.saveTasks();

        // Print success message
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }
}
