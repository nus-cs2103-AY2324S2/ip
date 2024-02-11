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
        if (tasks.isEmpty()) {
            ui.makeResponse("There is nothing to list.");
            return;
        }
        ui.makeResponse(tasks.getAsIndexedStream().toArray(String[]::new));
    }
}
