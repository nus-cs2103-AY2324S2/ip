package alpa.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import alpa.exceptions.AlpaException;
import alpa.tasks.Deadline;
import alpa.tasks.TaskList;
import alpa.utils.DateTimeUtils;
import alpa.utils.Storage;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand implements Command {
    private final String details;

    /**
     * Constructs a DeadlineCommand object with the specified details.
     *
     * @param details the details of the deadline task
     */
    public DeadlineCommand(String details) {
        this.details = details;
    }

    /**
     * Represents a command to add a deadline task to the task list.
     * The deadline task includes a description and a deadline date and time.
     * The command parses the input details and adds the deadline task to the task list.
     * It also saves the updated task list to the storage.
     *
     * @param taskList the task list to add the deadline task to
     * @param storage the storage to save the updated task list
     * @return a message indicating the successful addition of the deadline task
     * @throws AlpaException if the deadline format is invalid or the date/time format is invalid
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws AlpaException {
        try {
            String[] parts = this.details.split("\\s*/by\\s*", 2);
            if (parts.length < 2) {
                throw new AlpaException("Invalid deadline format, human! Use '/by' to specify the deadline.");
            }
            String description = parts[0].trim();
            String deadlineStr = parts[1].trim();
            LocalDateTime parsedDeadlineDateTime = DateTimeUtils.parseDeadlineDateTime(deadlineStr);
            Deadline deadline = new Deadline(description, parsedDeadlineDateTime);
            taskList.addTask(deadline);
            storage.saveTasks(taskList.getTasks());
            int size = taskList.getSize();
            return String.format("You added a task human!\n  %s\nNow you have %d tasks in your list!", deadline, size);
        } catch (DateTimeParseException e) {
            throw new AlpaException("Invalid date or time format, human!!");
        }
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
