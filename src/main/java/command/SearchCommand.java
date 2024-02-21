package command;

import java.time.LocalDateTime;

import andelu.AndeluException;
import andelu.DateTimeManager;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;
import task.Deadline;
import task.Event;
import task.Task;


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
     * Returns a response from Andelu.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException If missing the date.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        assert input != null : "input should not be null";
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new AndeluException("Missing the date!");
        }

        LocalDateTime dateInput = DateTimeManager.convertStringToLocalDateTime(splitInput[1].trim() + "T00:00");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks on " + input + ":\n");

        int index = 1;
        for (Task i : tasks.getTasks()) {
            if (i instanceof Deadline) {
                if (((Deadline) i).getBy().toLocalDate().isEqual(dateInput.toLocalDate())) {
                    stringBuilder.append((index++) + "." + i.toString() + "\n");
                }

            } else if (i instanceof Event) {
                if (((Event) i).getStart().toLocalDate().isEqual(dateInput.toLocalDate())) {
                    stringBuilder.append((index++) + "." + i.toString() + "\n");
                }
            }
        }

        if (index == 1) {
            stringBuilder.append("There are no task on this date\n");
        }
        return stringBuilder.toString();
    }
}
