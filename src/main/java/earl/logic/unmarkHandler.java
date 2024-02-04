package earl.logic;

import earl.exceptions.EarlException;
import earl.util.Parser;
import earl.util.TaskList;
import earl.util.Ui;

public final class unmarkHandler extends Handler {
    private final String[] COMMAND;

    public unmarkHandler(String[] command) {
        COMMAND = command;
    }

    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            int idx = Parser.parseIndex(COMMAND[1]);
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
