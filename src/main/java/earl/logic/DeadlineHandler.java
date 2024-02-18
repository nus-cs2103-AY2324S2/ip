package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.TaskType;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the deadline command.
 */
public class DeadlineHandler extends TaskHandler {


    /** Class constructor. */
    public DeadlineHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            String[] data = args.split("\\s+/by\\s+");
            String[] response = addTask(tasks, TaskType.DEADLINE, data);
            ui.makeResponse(response);
        } catch (EarlException e) {
            throw new EarlException(ui.appendNewline(e.getMessage())
                    + ui.leftPad(ui.appendNewline("Example use:"))
                    + ui.leftPad("deadline <task name> "
                            + "/by <dd/mm/yyyy hhmm>"));
        }
    }
}
