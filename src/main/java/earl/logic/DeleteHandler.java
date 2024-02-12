package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the delete command.
 */
public final class DeleteHandler extends Handler implements MassOperable {

    /** Class constructor. */
    public DeleteHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            Integer[] indices = getValidIndices(tasks, args);
            if (indices.length == 0) {
                ui.makeResponse("Error, no valid indices in range.");
                return;
            }
            for (int idx : indices) {
                addDisplayEntry(idx + 1 + "." + tasks.delete(idx).toString());
            }
            addDisplayEntry("Item(s) deleted.");
            ui.makeResponse(getDisplay());
        } catch (EarlException e) {
            throw e;
        } catch (Exception e) {
            throw new EarlException(
                    ui.appendNewline("Error, unknown use of delete.")
                            + ui.leftPad(e.getMessage()));
        }
    }
}
