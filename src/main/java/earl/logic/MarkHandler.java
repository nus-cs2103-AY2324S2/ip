package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the mark command.
 */
public final class MarkHandler extends MassOperableHandler {

    /**
     * Class constructor.
     */
    public MarkHandler(String args) {
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
                boolean success = tasks.get(idx).markAsDone();
                String feedback = idx + 1 + "." + tasks.get(idx);
                if (!success) {
                    feedback = feedback + " status unchanged.";
                }
                addDisplayEntry(feedback);
            }
            addDisplayEntry("Item(s) duly accomplished.");
            ui.makeResponse(getDisplay());
        } catch (EarlException e) {
            throw e;
        } catch (Exception e) {
            throw new EarlException("Command hath faltered: "
                    + "obscure employment of mark.");
        }
    }
}
