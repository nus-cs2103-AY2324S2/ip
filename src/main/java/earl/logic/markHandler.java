package earl.logic;

import earl.exceptions.EarlException;
import earl.util.Parser;
import earl.util.TaskList;
import earl.util.Ui;

public final class markHandler extends Handler {

    public final String[] COMMAND;

    public markHandler(String[] command) {
        COMMAND = command;
    }

    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            int idx = Parser.parseIndex(COMMAND[1]);
            if (tasks.mark(idx)) {
                ui.makeResponse("Item marked as done.");
            } else {
                ui.makeResponse("Item already marked as done.");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new EarlException(
                    "Error, not a valid item number within range.\n"
                            + "\tExample use:\n\tmark 3");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of mark.\n"
                    + e.getMessage());
        }
    }
}
