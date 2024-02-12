package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Task;
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
            Task added = tasks.add(TaskType.TODO.createTask(args));
            ui.buildResponse("Added new todo.");
            ui.buildResponse(ui.leftPad(added.toString()));
            ui.buildResponse("There are " + tasks.getSize()
                    + " task(s) tracked.");
            ui.completeResponse();
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    ui.appendNewline("Error, missing task name. Example use:")
                            + ui.leftPad("todo <name>"));
        } catch (Exception e) {
            throw new EarlException(
                    ui.appendNewline("Error, unknown use of todo.")
                            + ui.leftPad(e.getMessage()));
        }
    }
}
