package earl.logic;

import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for when an unknown command is recognised.
 */
public final class UnknownHandler extends Handler {

    @Override
    public void handle(TaskList task, Ui ui) {
        ui.makeResponse("Error, unknown command.",
                "Valid commands:",
                "\tlist, mark, unmark, todo, deadline, event",
                "\tdelete, find, bye");
    }
}
