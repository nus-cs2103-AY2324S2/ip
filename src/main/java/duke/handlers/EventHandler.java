package duke.handlers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.command.DukeException;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.tasks.Task;

/**
 * Handles inputs related to event tasks.
 */
public class EventHandler {

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
     * @return String           Indicates if task was successfully completed.
     */
    public String addEvent(String input, Storage storage, TaskList taskList, Ui ui) {
        try {
            return event(input, storage, taskList, ui);
        } catch (DukeException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to add new event and update storage.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           Indicates if task was successfully completed.
     * @throws DukeException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String event(String input, Storage storage, TaskList taskList, Ui ui) throws DukeException {
        if (input.matches("")) {
            throw new DukeException("This event is the highlight of the social \"calen-darling.\""
                    + "\r\nGot all the details?");
        }

        String[] event = input.split("/to | /from");
        if (event.length < 3) {
            throw new DukeException("This event is the highlight of the social \"calen-darling.\""
                    + "\r\nGot all the details?");
        }

        LocalDateTime from;
        LocalTime to;
        try {
            TimeHandler th = new TimeHandler();
            from = th.parseDateTime(event[1].strip());
            to = th.parseTime(event[2].strip());
        } catch (DukeException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }

        if (from.toLocalTime().isAfter(to)) {
            throw new DukeException("Is this even an event? Invalid start and end time");
        }

        Task task = taskList.addEvent(event[0].strip(), from, to);
        try {
            storage.addNewTask(task);
        } catch (IOException e) {
            return ui.printErrorMessage(e.getMessage());
        }
        return ui.printAddTask(task.toString(), taskList.getNumOfTasks());
    }
}
