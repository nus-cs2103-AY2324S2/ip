package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the mark command.
 */
public final class MarkHandler extends Handler {

    /**
     * Class constructor.
     */
    public MarkHandler(String... args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            int idx = Integer.parseInt(args[0]) - 1;
            if (mark(idx, tasks)) {
                ui.makeResponse("Item marked as done.",
                        "\t" + tasks.get(idx).toString());
            } else {
                ui.makeResponse("Item already marked as done.",
                        "\t" + tasks.get(idx).toString());
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

    /** Marks the task at the given index as done. Returns success. */
    public static boolean mark(int idx, TaskList tasks) {
        return tasks.get(idx).markAsDone();
    }
}
