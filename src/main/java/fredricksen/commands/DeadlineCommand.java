package fredricksen.commands;

import fredricksen.tasks.Deadline;
import fredricksen.tasks.TaskList;

/**
 * Represents a "Deadline" Command, which extends the Command class.
 * A "Deadline" Command is a command that creates a DeadlineCommand object with
 * the user input command and the TaskList tasks to store the task.
 */
public class DeadlineCommand extends Command {
    private TaskList tasks;
    private String fullCommand;

    /**
     * Constructs a DeadlineCommand instance with the specified user input command
     * and the TaskList to store the Deadline task in.
     *
     * @param fullCommand The user input command to be executed.
     * @param tasks the TaskList to store the various tasks.
     */
    public DeadlineCommand(String fullCommand, TaskList tasks) {
        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }

    /**
     * Check whether the formatted Date for the task is valid.
     *
     * @param task the Deadline task formatted time to check.
     *
     * @return A boolean based on whether the user input date portion of the command
     *          is valid or invalid.
     */
    public boolean checkIfDateInvalid(Deadline task) {
        return task.toString().contains("Invalid Date");
    }

    public String formatInvalidDateString() {
        return "Please enter date in the correct format!";
    }

    /**
     * Executes the Deadline command.
     * It creates a new Deadline instance.
     * It triggers the displayTask method in the base class to display the add task message
     * and then adds the Deadline instance into the TaskList containing all current Task type object.
     *
     * @return A String that is the message to be displayed after successfully adding a Deadline task.
     */
    @Override
    public String execute() {
        Deadline newDlTask = new Deadline(this.fullCommand, "D", false);
        if (checkIfDateInvalid(newDlTask)) {
            return formatInvalidDateString();
        }
        this.tasks.addTask(newDlTask);
        return displayTask(newDlTask, this.tasks);
    }
}
