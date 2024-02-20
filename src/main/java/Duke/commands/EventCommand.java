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
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("event");
        }
        int startIdx = words[1].indexOf("/from");
        int endIdx = words[1].indexOf("/to");
        boolean hasInvalidEventFormat = startIdx == -1 || endIdx == -1;
        if (hasInvalidEventFormat) {
            throw new InvalidEventException();
        }
        String[] eventDates = words[1].substring(startIdx).split("/from | /to ");
        boolean hasIncorrectNumbers = eventDates.length != 3;
        if (hasIncorrectNumbers) {
            throw new InvalidEventException();
        }
        String eventDescription = words[1].substring(0, startIdx);
        String startDate = eventDates[1];
        String endDate = eventDates[2];
        Task newEvent = new Event(eventDescription, startDate, endDate);
        tasks.addTask(newEvent);
        storage.addToWriteFile(newEvent);
        return ui.addTaskMessage(newEvent, tasks.getNumberOfTasks());
    }
}
