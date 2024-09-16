package fredricksen.commands;

import fredricksen.tasks.Event;
import fredricksen.tasks.TaskList;

/**
 * Represents an "Event" Command, which extends the Command class.
 * An "Event" Command is a command that creates a EventCommand object with
 * the user input command and the TaskList tasks to store the task.
 */
public class EventCommand extends Command {
    private TaskList tasks;
    private String fullCommand;

    /**
     * Constructs an EventCommand instance with the specified user input command
     * and the TaskList to store the Event task in.
     *
     * @param fullCommand The user input command to be executed.
     * @param tasks the TaskList to store the various tasks.
     */
    public EventCommand(String fullCommand, TaskList tasks) {

        assert fullCommand.equals("") : "Should not happen";

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
    public boolean isDateInvalid(Event task) {
        return task.toString().contains("Invalid Date");
    }
    public boolean isDescInvalid(Event task) {
        return task.toString().contains("Invalid description");
    }

    /**
     * Format a String that displays when the date time is not formatted correctly.
     *
     * @return A String that is formatted to display the Invalid date message.
     */
    public String formatInvalidDateString() {
        return "Please enter date in the correct format!";
    }

    /**
     * Format a String that displays when the description is not formatted correctly.
     *
     * @return A String that is formatted to display the Invalid date message.
     */
    public String formatInvalidDescString() {
        return "Please enter description in the correct format!";
    }

    /**
     * Executes the Event command.
     * It creates a new Event instance.
     * Then it triggers the displayTask method in the base class to display the add task message
     * and then adds the Event instance into the TaskList containing all current Task type object.
     *
     * @return A String that is the message to be displayed after successfully adding an Event task.
     */
    @Override
    public String execute() {
        Event newEventTask = new Event(this.fullCommand, "E", false);
        if (isDateInvalid(newEventTask)) {
            return formatInvalidDateString();
        } else if (isDescInvalid(newEventTask)) {
            return formatInvalidDescString();
        }
        this.tasks.addTask(newEventTask);
        return displayTask(newEventTask, this.tasks);
    }
}
