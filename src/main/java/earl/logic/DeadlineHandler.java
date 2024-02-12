package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Task;
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
            Task added = tasks.add(TaskType.DEADLINE.createTask(data));
            ui.buildResponse("Added new deadline.");
            ui.buildResponse(ui.leftPad(added.toString()));
            ui.buildResponse("There are " + tasks.getSize()
                    + " task(s) tracked.");
            ui.completeResponse();
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    ui.appendNewline("Error, invalid deadline format.")
                            + ui.appendNewline("Example use:")
                            + ui.leftPad("deadline <name> /by <due>"));
        } catch (Exception e) {
            throw new EarlException(
                    ui.appendNewline("Error, unknown use of deadline.")
                            + ui.leftPad(e.getMessage()));
        }
    }
}
