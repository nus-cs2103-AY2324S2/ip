package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to run Event Command.
 *
 * @author KohGuanZeh
 */
public class EventCommand extends Command {
    private final String eventDescription;
    private final LocalDateTime eventFrom;
    private final LocalDateTime eventTo;

    /**
     * Creates a Command that runs to add an Event task.
     *
     * @param input Input of Event information.
     * @throws CommandException Exception when there is a formatting error in the input.
     */
    public EventCommand(String input) throws CommandException {
        input = input.trim();
        try {
            String[] tokens = input.split("/from");
            this.eventDescription = tokens[0].trim();
            if (eventDescription.isEmpty()) {
                throw new CommandException("Error. Unable to create task.\nFormat: " + Event.INPUT_EVENT_FORMAT);
            }
            tokens = tokens[1].split("/to");
            this.eventFrom = LocalDateTime.parse(tokens[0].trim(), Task.INPUT_DATETIME_FORMAT);
            this.eventTo = LocalDateTime.parse(tokens[1].trim(), Task.INPUT_DATETIME_FORMAT);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new CommandException("Error. Unable to create task.\nFormat: " + Event.INPUT_EVENT_FORMAT);
        }
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.addTask(new Event(this.eventDescription, this.eventFrom, this.eventTo)));
        storage.save(taskList.toDataString());
    }
}
