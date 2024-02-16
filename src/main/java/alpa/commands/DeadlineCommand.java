package alpa.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import alpa.exceptions.AlpaException;
import alpa.tasks.Deadline;
import alpa.tasks.TaskList;
import alpa.ui.Ui;
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
     * Executes the DeadlineCommand by parsing the details of the command and
     * adding a new Deadline task to the task list.
     * If the details are not in the correct format or the date/time is invalid, an AlpaException is thrown.
     *
     * @param taskList the task list to add the new Deadline task to
     * @param ui the user interface to display messages
     * @param storage the storage to save the updated task list
     * @throws AlpaException if the details are in an invalid format or the date/time is invalid
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
        try {
            String[] parts = this.details.split("\\s*/by\\s*", 2);
            if (parts.length < 2) {
                throw new AlpaException("\nInvalid deadline format, human! Use '/by' to specify the deadline.");
            }
            String description = parts[0].trim();
            String deadlineStr = parts[1].trim();
            LocalDateTime parsedDeadlineDateTime = DateTimeUtils.parseDeadlineDateTime(deadlineStr);
            Deadline deadline = new Deadline(description, parsedDeadlineDateTime);
            taskList.addTask(deadline);
            ui.showAddedTask(deadline, taskList.getSize());
            storage.saveTasks(taskList.getTasks());
        } catch (DateTimeParseException e) {
            throw new AlpaException("\nInvalid date or time format, human!!");
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
