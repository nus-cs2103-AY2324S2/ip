package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.TaskListException;
import duke.utils.FormatException;
import duke.utils.Parser;

/**
 * Class to run Event Command.
 *
 * @author KohGuanZeh
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Creates a Command that runs to add an Event task.
     *
     * @param input Input of Event information.
     * @throws CommandException Exception to inform user of formatting error in input.
     */
    public EventCommand(String input) throws CommandException {
        try {
            this.event = Parser.parseEventInput(input);
        } catch (FormatException e) {
            throw new CommandException("Error. Unable to create task.\nFormat: " + Event.INPUT_EVENT_FORMAT);
        }
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        String message = taskList.addTask(this.event);
        storage.save(taskList.toDataString());
        return message;
    }
}
