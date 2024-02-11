package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the mark command.
 */
public final class MarkHandler extends Handler implements MassOperable {

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
                ui.makeResponse("Error, no valid indices in range.");
                return;
            }
            for (int idx : indices) {
                boolean success = tasks.get(idx).markAsDone();
                if (!success) {
                    addDisplayEntry(idx + 1 + "." + tasks.get(idx)
                            + " already marked as done.");
                    continue;
                }
                addDisplayEntry(idx + 1 + "." + tasks.get(idx));
            }
            addDisplayEntry("Item(s) marked as done.");
            ui.makeResponse(getDisplay());
        } catch (EarlException e) {
            throw e;
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of mark.\n"
                    + "\t" + e.getMessage());
        }
    }
}
