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
            if (args.isEmpty()) {
                throw new EarlException("The description is devoid of detail.");
            }
            Task added = tasks.add(TaskType.TODO.createTask(args));
            ui.buildResponse("A new todo, by virtue of your decree,");
            ui.buildResponse(
                    "hath been appended to the roster of responsibilities.");
            ui.buildResponse(ui.leftPad(added.toString()));
            ui.buildResponse("The ledger of tasks bears witness to "
                    + tasks.getSize() + " endeavour(s).");
            ui.completeResponse();
        } catch (EarlException e) {
            throw e;
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException(
                    ui.appendNewline("An error befalls. Example use:")
                            + ui.leftPad("todo <name>"));
        } catch (Exception e) {
            throw new EarlException("Command hath faltered: "
                    + "obscure employment of todo.");
        }
    }
}
