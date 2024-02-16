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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String task = message.split(" ", 2)[1];
            String description = task.split(" /from ", 2)[0];
            String fromBy = task.split(" /from ", 2)[1];
            String from = fromBy.split(" /to ", 2)[0];
            String to = fromBy.split(" /to ", 2)[1];
            LocalDate fromDate = null;
            LocalDate toDate = null;
            try {
                fromDate = LocalDate.parse(from);
                toDate = LocalDate.parse(to);
            } catch (DateTimeParseException e) {
                ui.showWrongFormat();
                ui.showDateFormat();
            }
            if (fromDate != null && toDate != null) {
                taskList.createEvent(description, fromDate, toDate);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showWrongFormat();
            ui.showEventFormat();
        }
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return false;
    }
}
