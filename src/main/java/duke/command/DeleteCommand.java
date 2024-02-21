package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * {@inheritDoc}
 *
 * This subclass executes when the user types in delete command.
 */
public class DeleteCommand extends Command {
    private final String message;

    /**
     * Constructs the class DeleteCommand.
     *
     * @param message The command line that the user types in.
     */
    public DeleteCommand(String message) {
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
            return taskList.delete(integerNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showForgetTaskNumber() + "\n" + ui.showDeleteFormat();
        } catch (NumberFormatException e) {
            return ui.showWrongFormat() + "\n" + ui.showDeleteFormat();
        }
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return false;
    }
}
