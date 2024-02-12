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
            ui.buildResponse("Added new event.");
            ui.buildResponse(ui.leftPad(added.toString()));
            ui.buildResponse("There are " + tasks.getSize()
                    + " task(s) tracked.");
            ui.completeResponse();
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    ui.appendNewline("Error, invalid event format.")
                            + ui.appendNewline("Example use:")
                            + ui.leftPad("event <name>"
                                    + " /from <start> /to <end>"));
        } catch (Exception e) {
            throw new EarlException(
                    ui.appendNewline("Error, unknown use of event.")
                            + ui.leftPad(e.getMessage()));
        }
    }
}
