package duke.commands;

import java.time.Instant;

import duke.exceptions.InvalidArgumentException;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The ListCommand class defines a command to list tasks stored in the Duke
 * application
 *
 * @author Ryan NgWH
 */
public class ListCommand extends Command {
    /**
     * Date filter for the list command
     */
    private Instant date;

    /**
     * Creates a list command without any filters
     */
    public ListCommand() {
        super(false);
        this.date = null;
    }

    /**
     * Creates a list command with a date filter
     */
    public ListCommand(Instant date) {
        super(false);
        this.date = date;
    }

    /**
     * Executes the list command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidArgumentException {
        String tasks;
        if (this.date != null) {
            tasks = taskList.getTasks(this.date);
        } else {
            tasks = taskList.getTasks();
        }

        // Print tasks
        System.out.println(tasks);
    }

    /**
     * Indicates whether some other object is "equal to" this command
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ListCommand) {
            ListCommand command = (ListCommand) obj;

            return super.equals(command) && this.date.equals(command.date);
        }

        return false;
    }
}
