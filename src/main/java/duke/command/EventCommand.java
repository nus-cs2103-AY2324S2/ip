package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.util.DukeList;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     * @param dukeList Holds the tasks added.
     * @param ui Display messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException If date time format is not valid.
     */
    @Override
    public String execute(DukeList dukeList, Ui ui, Storage storage) throws DukeException {
        TaskList list = (TaskList) dukeList;
        String[] task = input.split("/from");
        assert (task.length == 2) : "Event command cannot be split at /from";
        String[] time = task[1].split("/to");
        assert (time.length == 2) : "Event command cannot be split at /to";
        try {
            Task t = createTask(task, time);
            list.add(t);
            storage.writeToFile(list);
            return ui.showAdded(t, list);
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }

    }

    private Task createTask(String[] task, String[] time) {
        Task t = new Event(task[0].substring(6).trim(), time[0].trim(), time[1].trim());
        return t;
    }

    @Override
    public boolean equals(Object a) {
        EventCommand ec = (EventCommand) a;
        return this.input.equals(ec.input);
    }
}
