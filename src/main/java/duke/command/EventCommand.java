package duke.command;


import duke.common.Messages;
import duke.common.TaskList;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Event;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents an event command initiated by the user. <code>EventCommand</code> would create an event task
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_USAGE = "event: it creates an event task.\n" +
            "Example: event foo /from 2022-01-01 10:00 /to 2022-01-01 18:00";
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor of the event command
     * @param description The description of the event
     * @param startDate The start date of the event
     * @param endDate The end date of the event
     */
    public EventCommand(String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates an event task and add it to the TaskList, then show the result to the user.
     * @param taskList
     * @param ui
     * @param storage
     * @throws InvalidInputException If the start date is later than the end date
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (startDate.isAfter(endDate)) {
            throw new InvalidInputException(String.format(Messages.MESSAGE_INVALID_INPUT_VALUE,
                    "Event start date cannot be after the end date"));
        }
        Event event = new Event(description, false, startDate, endDate);
        taskList.addTask(event);
        ui.showNewTask(event, taskList);
    }
}
