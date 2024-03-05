package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * {@inheritDoc}
 *
 * This subclass executes when the user types in deadline command.
 */
public class DeadlineCommand extends Command {
    private final String message;

    /**
     * Constructs the class DeadlineCommand
     *
     * @param message The command line that the user types in.
     */
    public DeadlineCommand(String message) {
        super();
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Storage storage) {
        try {
            String task = message.split(" ", 2)[1];
            String description = task.split(" /by ", 2)[0];
            String by = task.split(" /by ", 2)[1];
            LocalDate byDate;
            try {
                byDate = LocalDate.parse(by);
            } catch (DateTimeParseException e) {
                return Ui.showWrongFormat() + "\n" + Ui.showDateFormat();
            }
            return taskList.createDeadline(description, byDate);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.showWrongFormat() + "/n" + Ui.showDeadlineFormat();
        }
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return false;
    }
}
