package earl.logic;

import earl.exceptions.EarlException;
import earl.util.Parser;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the unmark command.
 */
public final class UnmarkHandler extends Handler {

    private final String[] command;

    /**
     * Class constructor.
     *
     * @param command  the user input that invoked this handler
     */
    public UnmarkHandler(String[] command) {
        this.command = command;
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            int idx = Parser.parseIndex(command[1]);
            if (tasks.unmark(idx)) {
                ui.makeResponse("Item marked as not done.");
            } else {
                ui.makeResponse("Item already marked as not done.");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new EarlException(
                    "Error, not a valid item number within range.\n"
                            + "\tExample use:\n\tunmark 3");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of unmark.\n"
                    + e.getMessage());
        }
    }
}
