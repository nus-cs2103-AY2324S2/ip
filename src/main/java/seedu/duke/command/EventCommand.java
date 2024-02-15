package seedu.duke.command;

import java.time.LocalDateTime;

import seedu.duke.common.Messages;
import seedu.duke.common.TaskList;
import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;


/**
 * Represents an event command initiated by the user. <code>EventCommand</code> would create an event task
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_USAGE = "event: it creates an event task.\n"
            + "Example: event foo /from 2022-01-01 10:00 /to 2022-01-01 18:00";
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor of the event command
     *
     * @param description The description of the event
     * @param startDate   The start date of the event
     * @param endDate     The end date of the event
     */
    public EventCommand(String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates an event task and add it to the TaskList, then show the result to the user.
     * @throws InvalidInputException If the start date is later than the end date
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException,
            DuplicateTaskException {
        if (startDate.isAfter(endDate)) {
            throw new InvalidInputException(String.format(Messages.MESSAGE_INVALID_INPUT_VALUE,
                    "Event start date cannot be after the end date"));
        }

        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task instanceof Event) {
                boolean isSameDescription = task.getDescription().equals(description);
                boolean isSameStartDate = ((Event) task).getStartDate().equals(startDate);
                boolean isSameEndDate = ((Event) task).getEndDate().equals(endDate);
                boolean isSameStatus = !task.getHasDone();
                if (isSameDescription && isSameStatus && isSameStartDate && isSameEndDate) {
                    throw new DuplicateTaskException(task);
                }
            }
        }


        Event event = new Event(description, false, startDate, endDate);
        taskList.addTask(event);
        ui.generateNewTaskResponse(event, taskList);
    }
}
