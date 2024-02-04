package earl.logic;

import earl.exceptions.EarlException;
import earl.util.Parser;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the delete command.
 */
public final class DeleteHandler extends Handler {
    private final String[] COMMAND;

    public DeleteHandler(String[] command) {
        COMMAND = command;
    }

    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            int idx = Parser.parseIndex(COMMAND[1]);
            ui.makeResponse("Item deleted.",
                    "\t" + tasks.delete(idx));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new EarlException(
                    "Error, not a valid item number within range.\n"
                            + "\tExample use:\n\tdelete 3");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of delete.\n"
                    + e.getMessage());
        }
    }
}
