package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Todo;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the todo command.
 */
public class TodoHandler extends Handler {

    private final String[] COMMAND;

    public TodoHandler(String[] command) {
        COMMAND = command;
    }

    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            tasks.add(new Todo(COMMAND[1]));
            ui.makeResponse("Added new todo.",
                    "\t" + tasks.get(tasks.getSize() - 1),
                    "There are " + tasks.getSize()
                            + " task(s) tracked.");
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException("Error, missing task name.\n"
                    + "\tExample use:\n\ttodo <task_name>");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of todo.\n"
                    + e.getMessage());
        }
    }
}