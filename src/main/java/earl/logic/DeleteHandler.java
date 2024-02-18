package earl.logic;

import earl.exceptions.EarlException;
import earl.exceptions.ParserException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the delete command.
 */
public final class DeleteHandler extends MassOperableHandler {

    /** Class constructor. */
    public DeleteHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            Integer[] indices = getValidIndices(tasks, args);
            if (indices.length == 0) {
                ui.makeResponse("An error proclaims the absence of "
                        + "valid indices in range.");
                return;
            }

            for (int idx : indices) {
                addDisplayEntry(idx + 1 + "." + tasks.delete(idx));
            }
            addDisplayEntry("Item(s) heretofore have been expunged.");

            ui.makeResponse(getDisplayEntriesReversed());
        } catch (ParserException e) {
            throw new EarlException(ui.appendNewline(
                    "The indices' format is fraught with invalidity.")
                    + ui.leftPad("Example format: 1 4-7 9-10"));
        } catch (Exception e) {
            throw new EarlException("Command hath faltered: "
                    + "obscure employment of delete.");
        }
    }
}
