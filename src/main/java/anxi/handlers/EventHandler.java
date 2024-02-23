package anxi.handlers;

import java.time.LocalDateTime;
import java.time.LocalTime;

import anxi.command.AnxiException;
import anxi.command.Storage;
import anxi.command.TaskList;
import anxi.command.Ui;
import anxi.tasks.Task;

/**
 * Handles inputs related to event tasks.
 */
public class EventHandler extends Handler {

    /**
     * EventHandler constructor.
     */
    public EventHandler() {
    }

    /**
     * Adds new event task.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String       Indicates if task was successfully completed.
     */
    public String addEvent(String input, Storage storage, TaskList taskList, Ui ui) {
        try {
            return event(input, storage, taskList, ui);
        } catch (AnxiException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to add new event and update storage.
     *
     * @param input             Input command string.
     * @param storage           Instance of Storage class.
     * @param taskList          Instance of TaskList class.
     * @param ui                Instance of Ui class.
     * @return String           Indicates if task was successfully completed.
     * @throws AnxiException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String event(String input, Storage storage, TaskList taskList, Ui ui) throws AnxiException {
        if (input.isBlank()) {
            throw new AnxiException("This event is the highlight of the social calendar"
                    + "\nGot all the details?"
                    + "\n\nFormat: event <name> /from <date and time> /to <time>");
        }

        String[] event = input.split("/to | /from");
        if (event.length < 3) {
            throw new AnxiException("This event is the highlight of the social calendar"
                    + "\nGot all the details?"
                    + "\n\nFormat: event <name> /from <date and time> /to <time>");
        }

        LocalDateTime from = parseDateTime(event[1].strip());
        LocalTime to = parseTime(event[2].strip());

        if (from.toLocalTime().isAfter(to)) {
            throw new AnxiException("Is this even an event? Invalid start and end time");
        }

        Task task = taskList.addEvent(event[0].strip(), from, to);
        addTaskInStorage(storage, task);

        return ui.printAddTask(task.toString(), taskList.getNumOfTasks());
    }
}
