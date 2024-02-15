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
            ui.buildResponse("A new deadline, by virtue of your decree,");
            ui.buildResponse(
                    "hath been appended to the roster of responsibilities.");
            ui.buildResponse(ui.leftPad(added.toString()));
            ui.buildResponse("The ledger of tasks bears witness to "
                    + tasks.getSize() + " endeavours.");
            ui.completeResponse();
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    ui.appendNewline("An error befalls.")
                            + ui.appendNewline("Example use:")
                            + ui.leftPad("deadline <name> /by <due>"));
        } catch (Exception e) {
            throw new EarlException(
                    ui.appendNewline("Command hath faltered: "
                            + "obscure employment of mark."));
        }
    }
}
