package command;

import java.time.LocalDate;
import java.util.ArrayList;
import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Task;

/**
 * Represents a command that lists search results given a date
 */
public class SearchDateCommand extends Command {
    private final String date;

    /**
     * Constructs a new SearchDateCommand.
     *
     * @param date A string representing the date
     */
    public SearchDateCommand(String date) {
        this.date = date;
    }

    /**
     * Judges if 2 commands are searching the same date.
     *
     * @param other Another Command
     * @return true if the 2 commands are searching the same date, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof SearchDateCommand) {
            try {
                return Task.parseDate(((SearchDateCommand) other).date).isEqual(Task.parseDate(date));
            } catch (DukeException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Searches tasks that match the searching date, and lists all of them.
     *
     * @param storage  Involved in file management
     * @param taskList Active during the execution of the program
     * @return A string that contains all matching tasks
     * @throws DukeException
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        LocalDate localDate = Task.parseDate(date);
        return taskList.searchDate(localDate);
    }
}
