package earl.logic;

import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the list command.
 */
public final class ListHandler extends Handler {

    public ListHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) {
        if (!args.isEmpty()) {
            ui.makeResponse("No arguments should trail in the wake "
                    + "of this command.");
            return;
        }
        if (tasks.isEmpty()) {
            ui.makeResponse("A void of contents prevails.");
            return;
        }
        ui.buildResponse("The ledger of tasks:");
        ui.buildResponse(tasks.getAsIndexedStream().toArray(String[]::new));
        ui.completeResponse();
    }
}
