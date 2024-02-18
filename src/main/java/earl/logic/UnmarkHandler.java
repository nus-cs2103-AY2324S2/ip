package earl.logic;

import earl.exceptions.EarlException;
import earl.exceptions.ParserException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the unmark command.
 */
public final class UnmarkHandler extends MassOperableHandler {

    /** Class constructor. */
    public UnmarkHandler(String args) {
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
                boolean success = tasks.get(idx).markUndone();
                String feedback = idx + 1 + "." + tasks.get(idx);
                if (!success) {
                    feedback = feedback + " status unchanged.";
                }
                addDisplayEntry(feedback);
            }
            addDisplayEntry("Item(s) persist as undone.");

            ui.makeResponse(getDisplayEntriesReversed());
        } catch (ParserException e) {
            throw new EarlException(ui.appendNewline(
                    "The indices' format is fraught with invalidity.")
                    + ui.leftPad("Example format: 1 4-7 9-10"));
        } catch (Exception e) {
            throw new EarlException("Command hath faltered: "
                    + "obscure employment of unmark.");
        }
    }
}
