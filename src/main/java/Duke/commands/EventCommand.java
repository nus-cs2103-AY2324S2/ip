package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidEventException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class for EventCommand
 */
public class EventCommand extends Command {
    private String[] words;
    /**
     * Constructs an EventCommand object with the given array of command words.
     *
     * @param words The array of strings containing the command and its arguments.
     */
    public EventCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("event");
        }
        int startIdx = words[1].indexOf("/from");
        int endIdx = words[1].indexOf("/to");
        if (startIdx == -1 || endIdx == -1) {
            throw new InvalidEventException();
        }
        String[] dates = words[1].substring(startIdx).split("/from | /to ");
        if (dates.length != 3) {
            throw new InvalidEventException();
        }
        Task newTask = new Event(words[1].substring(0, startIdx),
                dates[1],
                dates[2]);
        ui.displayAdd(tasks.addTask(newTask), tasks.getItems().size());
        storage.addToWriteFile(newTask);
        return false;
    }
}
