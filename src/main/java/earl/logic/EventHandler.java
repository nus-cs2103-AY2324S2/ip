package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.TaskType;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the event command.
 */
public class EventHandler extends Handler {

    /** Class constructor. */
    public EventHandler(String... args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) throws EarlException {
        try {
            tasks.add(TaskType.EVENT.createTask(args));
            ui.makeResponse("Added new event.",
                    "\t" + tasks.get(tasks.getSize() - 1),
                    "There are " + tasks.getSize() + " task(s) tracked.");
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException("Error, invalid event format.\n"
                    + "\tExample use:\n\t"
                    + "\tevent <task_name> /from <start> /to <end>");
        } catch (Exception e) {
            throw new EarlException("Error, unknown use of event.\n"
                    + e.getMessage());
        }
    }
}
