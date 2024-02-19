package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.TaskType;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the todo command.
 */
public class TodoHandler extends TaskHandler {

    /** Class constructor. */
    public TodoHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            if (args.isEmpty()) {
                throw new EarlException("The description is devoid of detail.");
            }
            String[] response = addTask(tasks, TaskType.TODO, args);
            ui.makeResponse(response);
        } catch (EarlException e) {
            throw new EarlException(ui.appendNewline(e.getMessage())
                    + ui.leftPad(ui.appendNewline("Example use:"))
                    + ui.leftPad("todo <task name>"));
        }
    }
}
