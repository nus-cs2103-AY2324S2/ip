package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DateTimeManager;

import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDateTime;

/**
 * A Search command to search the tasks based on date.
 * A subclass of Command class.
 */
public class SearchCommand extends Command {

    /** The information from the user to search tasks. */
    private String input;

    /**
     * Creates a constructor with userInput as argument.
     *
     * @param userInput The information to search a task based on a date.
     */
    public SearchCommand(String userInput) {
        this.input = userInput;
    }


    /**
     * Searches tasks based on the date.
     * If no date is inputted, an error message is returned.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException If missing the date.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("Missing the date!");
        }

        LocalDateTime dateInput = DateTimeManager.convertStringToLocalDateTime(splitInput[1].trim() +"T00:00");

        ui.printAnyStatement("Here are the tasks on " + input +":");

        int index = 1;
        for (Task i : tasks.getTasks()) {
            if (i instanceof Deadline) {
                if (((Deadline) i).getBy().toLocalDate().isEqual(dateInput.toLocalDate())) {
                    ui.printAnyStatement((index++) + "." + i.toString());
                }

            } else if (i instanceof Event) {
                if (((Event) i).getStart().toLocalDate().isEqual(dateInput.toLocalDate())) {
                    ui.printAnyStatement((index++) + "." + i.toString());
                }
            }
        }

        if (index == 1) {
            ui.printAnyStatement("There are no task on this date");
        }
    }
}
