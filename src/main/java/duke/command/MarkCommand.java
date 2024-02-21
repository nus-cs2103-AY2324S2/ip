package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * {@inheritDoc}
 *
 * This subclass executes when the user types in mark command.
 */
public class MarkCommand extends Command {
    private final String message;

    /**
     * Constructs the class MarkCommand.
     *
     * @param message The command line that the user types in.
     */
    public MarkCommand(String message) {
        super();
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String number = message.split(" ")[1];
            int integerNumber = Integer.parseInt(number);
            return taskList.mark(integerNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showForgetTaskNumber() + "\n" + ui.showMarkFormat();
        } catch (NumberFormatException e) {
            return ui.showWrongFormat() + "\n" + ui.showMarkFormat();
        }
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return false;
    }
}
