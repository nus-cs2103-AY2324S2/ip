package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add new event.
 */
public class EventCommand implements Command {

    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new Event based on description, start and end date time extracted from input and adds to TaskList.
     *
     * @param list Holds the tasks added.
     * @param ui Display messages about executed operation.
     * @param storage Handles IO storage operation.
     * @throws DukeException If date time format is not valid.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String[] task = input.split("/from");
        String[] time = task[1].split("/to");
        try {
            Task t = new Event(task[0].substring(6).trim(), time[0].trim(), time[1].trim());
            list.add(t);
            ui.showAdded(t, list);
            storage.writeToFile(list);
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object a) {
        EventCommand ec = (EventCommand) a;
        return this.input.equals(ec.input);
    }
}
