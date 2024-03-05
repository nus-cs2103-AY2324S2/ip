package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * {@inheritDoc}
 *
 * This subclass executes when the user types in unmark command.
 */
public class UnmarkCommand extends Command {
    public String message;

    /**
     * Constructs the class UnmarkCommand.
     *
     * @param message The command line that the user types in.
     */
    public UnmarkCommand(String message) {
        super();
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Storage storage) {
        try {
            String number = message.split(" ")[1];
            int integerNumber = Integer.parseInt(number);
            return taskList.unmark(integerNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.showForgetTaskNumber() + "\n" + Ui.showUnmarkFormat();
        } catch (NumberFormatException e) {
            return Ui.showUnmarkFormat();
        }
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return false;
    }
}
