package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the unmark command.
 */
public final class UnmarkHandler extends Handler {

    /** Class constructor. */
    public UnmarkHandler(String... args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            int idx = Integer.parseInt(args[0]) - 1;
            if (unmark(idx, tasks)) {
                ui.makeResponse("Item marked as not done.",
                        "\t" + tasks.get(idx).toString());
            } else {
                ui.makeResponse("Item already marked as not done.",
                        "\t" + tasks.get(idx).toString());
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

    /** Marks the task at the given index as undone. Returns success. */
    public static boolean unmark(int idx, TaskList tasks) {
        return tasks.get(idx).markUndone();
    }
}
