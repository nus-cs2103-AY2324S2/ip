package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * {@inheritDoc}
 *
 * This subclass executes when the user types in event command.
 */
public class EventCommand extends Command {
    private final String message;

    /**
     * Constructs the class EventCommand.
     *
     * @param message The command line that the user types in.
     */
    public EventCommand(String message) {
        super();
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Storage storage) {
        try {
            String task = message.split(" ", 2)[1];
            String description = task.split(" /", 2)[0];
            String fromAndBy = task.split(" /", 2)[1];
            String from = "";
            String to = "";
            String firstPart = fromAndBy.split(" ", 2)[0];
            String remainingPart = fromAndBy.split(" ", 2)[1];
            if (firstPart.equalsIgnoreCase("from")) {
                from = remainingPart.split(" /to ", 2)[0];
                to = remainingPart.split(" /to ", 2)[1];
            } else if (firstPart.equalsIgnoreCase("to")) {
                to = remainingPart.split(" /from ", 2)[0];
                from = remainingPart.split(" /from ", 2)[1];
            }
            LocalDate fromDate;
            LocalDate toDate;
            try {
                fromDate = LocalDate.parse(from);
                toDate = LocalDate.parse(to);
            } catch (DateTimeParseException e) {
                return Ui.showWrongFormat() + "\n" + Ui.showDateFormat();
            }
            return taskList.createEvent(description, fromDate, toDate);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.showWrongFormat() + "\n" + Ui.showEventFormat();
        }
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return false;
    }
}
