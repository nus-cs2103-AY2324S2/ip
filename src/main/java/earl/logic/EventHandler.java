package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Task;
import earl.tasks.TaskType;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the event command.
 */
public class EventHandler extends Handler {

    /** Class constructor. */
    public EventHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            String[] data = args.split("\\s+/(from|to)\\s+");
            Task added = tasks.add(TaskType.EVENT.createTask(data));
            ui.buildResponse("A new event, by virtue of your decree, ");
            ui.buildResponse(
                    "hath been appended to the roster of responsibilities.");
            ui.buildResponse(ui.leftPad(added.toString()));
            ui.buildResponse("The ledger of tasks bears witness to "
                    + tasks.getSize() + " endeavours.");
            ui.completeResponse();
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    ui.appendNewline("An error befalls. Example use:")
                            + ui.leftPad("event <name>"
                                    + " /from <start> /to <end>"));
        } catch (Exception e) {
            throw new EarlException("Command hath faltered: "
                    + "obscure employment of event.");
        }
    }
}
