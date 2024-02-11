package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.TaskType;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the deadline command.
 */
public class DeadlineHandler extends Handler {


    /** Class constructor. */
    public DeadlineHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            String[] data = args.split("\\s+/by\\s+");
            tasks.add(TaskType.DEADLINE.createTask(data));
            ui.makeResponse("Added new deadline.",
                    "\t" + tasks.get(tasks.getSize() - 1),
                    "There are " + tasks.getSize() + " task(s) tracked.");
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    "Error, invalid deadline format.\n"
                            + "\tExample use:\n\t"
                            + "\tdeadline <task_name> /by <end>");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of deadline.\n"
                    + e.getMessage());
        }
    }
}
