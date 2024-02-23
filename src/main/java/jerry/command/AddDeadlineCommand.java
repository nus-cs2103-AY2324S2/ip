package jerry.command;

import jerry.Deadline;
import jerry.TaskList;
import jerry.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a new Deadline task to the task list.
 * <p>
 * Deadline tasks have a specific due date along with a description. This command
 * facilitates adding such tasks to the application's task list.
 */
public class AddDeadlineCommand extends Command {

    private final String commandDetails;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified UI, task list, description, and deadline.
     *
     * @param ui          The UI component for interacting with the user.
     * @param tasks       The task list to which the new deadline task will be added.
     * @param commandDetails The description of the deadline task.
     */
    public AddDeadlineCommand(Ui ui, TaskList tasks, String commandDetails) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the addition of a Deadline task to the task list.
     * <p>
     * A new Deadline task with the provided description and due date is created and added to the task list.
     * The user is then informed of the successful task addition.
     */
    @Override
    public String execute() {
        try {
            if (!commandDetails.contains(" /by ")) {
                throw new CommandFormatException("Wrong format, please include deadline "
                        + "\nUsage: deadline <task description> /by <date/time>");
            }
            String[] deadlineParts = commandDetails.split(" /by ", 2);
            String description = deadlineParts[0];
            String by = deadlineParts[1];
            Deadline deadline = new Deadline(description, by);
            if (!deadline.byIsNull()) {
                tasks.addTask(deadline);
                return ui.showAdded(deadline, tasks);
            } else {
                return ui.showWrong();
            }
        } catch (DateTimeParseException e) {
            return ("Invalid date format. Please use yyyy-MM-dd.");
        } catch (CommandFormatException e) {
            return ui.showMessage(e.getMessage());
        }
    }
}
