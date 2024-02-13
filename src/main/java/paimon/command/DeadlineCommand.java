package paimon.command;

import paimon.ChatException;
import paimon.UiHandler;
import paimon.DateParser;
import paimon.task.DeadlineTask;
import paimon.task.Task;
import paimon.task.TaskList;

import java.time.LocalDateTime;


/**
 * Represents a command to add a deadline task to the task list. This command includes the task description
 * and the end date string, which is parsed to a {@link LocalDateTime} object to create a {@link DeadlineTask}.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String endDateString;

    /**
     * Constructs a DeadlineCommand with the specified task description and end date string.
     *
     * @param description   The description of the deadline task.
     * @param endDateString The end date of the task as a string, to be parsed to {@link LocalDateTime}.
     */
    public DeadlineCommand(String description, String endDateString) {
        this.description = description;
        this.endDateString = endDateString;
    }

    /**
     * Executes the command by parsing the end date string, creating a new {@link DeadlineTask},
     * and adding it to the task list. Notifies the user through the UI handler.
     *
     * @param tasks The task list to which the deadline task is added.
     * @param ui    The UI handler for interacting with the user.
     * @throws ChatException If the end date string cannot be parsed into a valid date/time.
     */
    public void execute(TaskList tasks, UiHandler ui) throws ChatException {
        try {
            LocalDateTime endDate = DateParser.parseDate(endDateString);
            Task deadlineTask = new DeadlineTask(this.description, endDate);
            tasks.addTask(deadlineTask);
            ui.addTaskResponse(deadlineTask.getTask(), tasks.getSize());
        } catch (ChatException e) {
            throw e;
        }
    }

    /**
     * Indicates that executing this command does not signal the application to exit.
     *
     * @return false always, as the deadline command does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }

}