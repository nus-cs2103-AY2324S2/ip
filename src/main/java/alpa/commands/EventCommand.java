package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.utils.DateTimeUtils;
import alpa.utils.Storage;
import alpa.tasks.Event;
import alpa.tasks.TaskList;
import alpa.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents a command to add an event task.
 */
public class EventCommand implements Command {
    private final String details;

    /**
     * Constructs an EventCommand object with the specified details.
     *
     * @param details the details of the event task
     */
    public EventCommand(String details) {
        this.details = details;
    }

    /**
     * Executes the event command, adding the event task to the task list, displaying the added task,
     * and saving the tasks to storage.
     *
     * @param taskList the task list
     * @param ui the user interface
     * @param storage the storage
     * @throws AlpaException if there is an error executing the command
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
        try {
            Pattern fromPattern = Pattern.compile("\\s*/from\\s*");
            Pattern toPattern = Pattern.compile("\\s*/to\\s*");
            Matcher fromMatcher = fromPattern.matcher(details);
            Matcher toMatcher = toPattern.matcher(details);
            if (!fromMatcher.find() || !toMatcher.find()) {
                throw new AlpaException("\nInvalid event format, human! Please use '/from' and '/to' to specify the event time.");
            }
            int fromIndex = fromMatcher.start();
            int toIndex = toMatcher.start();
            String description = details.substring(0, fromIndex).trim();
            String startStr = details.substring(fromIndex + fromMatcher.group().length(), toIndex).trim();
            String endStr = details.substring(toIndex + toMatcher.group().length()).trim();
            LocalDateTime startDateTime = DateTimeUtils.parseDateTime(startStr);
            LocalDateTime endDateTime = DateTimeUtils.tryParseEndDateTime(endStr, startDateTime.toLocalDate());
            if (endDateTime.isBefore(startDateTime)) {
                throw new AlpaException("\nEnd time cannot be before start time, human!");
            }
            Event event = new Event(description, startDateTime, endDateTime);
            taskList.addTask(event);
            ui.showAddedTask(event, taskList.getSize());
            storage.saveTasks(taskList.getTasks());
        } catch (DateTimeParseException e) {
            throw new AlpaException("\nInvalid date and time format, human!! Start: '" + details + "'.");
        }
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise
     */
    @Override
    public boolean isExit() {
        return false;
    }
}


