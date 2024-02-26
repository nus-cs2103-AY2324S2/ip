package paimon.command;

import java.time.LocalDateTime;

import paimon.ChatException;
import paimon.task.DeadlineTask;
import paimon.task.DoAfterTask;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.util.DateParser;
import paimon.util.UiHandler;


/**
 * Represents a command to add a DoAfter task to the task list. This command includes the task description
 * and the begin date string, which is parsed to a {@link LocalDateTime} object to create a {@link DeadlineTask}.
 */
public class DoAfterCommand extends Command {
    private final String description;
    private final String beginDateString;

    /**
     * Constructs a DeadlineCommand with the specified task description and end date string.
     *
     * @param description   The description of the deadline task.
     * @param endDateString The end date of the task as a string, to be parsed to {@link LocalDateTime}.
     */
    public DoAfterCommand(String description, String endDateString) {
        this.description = description;
        this.beginDateString = endDateString;
    }

    /**
     * Executes the command by parsing the end date string, creating a new {@link DeadlineTask},
     * and adding it to the task list. Notifies the user through the UI handler.
     *
     * @param tasks The task list to which the deadline task is added.
     * @return A String to be displayed.
     * @throws ChatException If the end date string cannot be parsed into a valid date/time.
     */
    public String execute(TaskList tasks) throws ChatException {
        LocalDateTime beginDate = DateParser.parseDate(beginDateString);
        Task doAfterTask = new DoAfterTask(this.description, beginDate);
        tasks.addTask(doAfterTask);
        return UiHandler.getAddTaskMessage(doAfterTask.getTask(), tasks.getSize());
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
