package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new {@code ListCommand}.
     *
     * Lists all tasks in the task list.
     */
    public ListCommand() {
    }

    /**
     * {@inheritDoc}
     *
     * Lists all tasks in the task list.
     * Displays a message to the user if there are no tasks in the list.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            System.out.println("\t\tThere is nothing on your agenda");
        }
        else {
            System.out.println("\t\tThese are the things on your agenda today");
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task t = tasks.get(i - 1);
                System.out.println("\t\t" + i + "." + t.toString());
            }
        }
        System.out.println("");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
