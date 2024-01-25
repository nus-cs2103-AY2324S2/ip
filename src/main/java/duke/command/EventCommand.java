package duke.command;


import duke.common.Messages;
import duke.common.TaskList;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Event;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_USAGE = "event: it creates an event task.\n" +
            "Example: event foo /from 2022-01-01 10:00 /to 2022-01-01 18:00";
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventCommand(String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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
