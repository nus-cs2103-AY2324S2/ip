package ciara.commands;

import java.time.Instant;

import ciara.exceptions.InvalidArgumentException;
import ciara.storage.TaskList;
import ciara.ui.Ui;

/**
 * The ListCommand class defines a command to list tasks stored in the Ciara
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
     * Visibility of the tasks to list
     */
    private boolean isArchived;

    /**
     * Creates a list command without any filters
     *
     * @param isArchived Visibility of the tasks to find
     *
     */
    public ListCommand(boolean isArchived) {
        super(false);
        this.date = null;
        this.isArchived = isArchived;
    }

    /**
     * Creates a list command with a date filter
     *
     * @param date       Date to filter
     * @param isArchived Visibility of the tasks to find
     */
    public ListCommand(Instant date, boolean isArchived) {
        super(false);
        this.date = date;
        this.isArchived = isArchived;
    }

    /**
     * Executes the command
     *
     * @param taskList Tasklist used for the command
     *
     * @return String containing the output of the command
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentException {
        String tasks;
        if (this.date != null) {
            tasks = taskList.getTasks(this.date, isArchived);
        } else {
            tasks = taskList.getTasks(isArchived);
        }

        return tasks;
    }

    /**
     * Executes the list command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidArgumentException {
        String successMessage = this.execute(taskList);

        // Print tasks
        System.out.println(successMessage);
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

            if (this.date != null && command.date != null) {
                return super.equals(command)
                        && this.date.equals(command.date)
                        && this.isArchived == command.isArchived;
            } else if (this.date == null && command.date == null) {
                return super.equals(command) && this.isArchived == command.isArchived;
            }
        }

        return false;
    }
}
