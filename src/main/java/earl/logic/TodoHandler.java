package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.TaskType;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the todo command.
 */
public class TodoHandler extends Handler {

    /** Class constructor. */
    public TodoHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            tasks.add(TaskType.TODO.createTask(args));
            ui.makeResponse("Added new todo.",
                    "\t" + tasks.get(tasks.getSize() - 1),
                    "There are " + tasks.getSize() + " task(s) tracked.");
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException("Error, missing task name.\n"
                    + "\tExample use:\n\ttodo <task_name>");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of todo.\n"
                    + e.getMessage());
        }
    }
}
