package earl.logic;

import earl.util.TaskList;
import earl.util.Ui;

public final class unknownHandler extends Handler {
    public void handle(TaskList task, Ui ui) {
        ui.makeResponse("Error, unknown command.",
                "Valid commands:",
                "\tlist, mark, unmark, todo, deadline, event, delete");
    }
}
