package earl.logic;

import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the find command.
 */
public class FindHandler extends MassOperableHandler {

    /** Class constructor. */
    public FindHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) {
        String[] matches = tasks.getAsIndexedStream()
                .filter((str) -> str.contains(args))
                .toArray(String[]::new);
        if (matches.length == 0) {
            ui.makeResponse("No matches have been discerned.");
            return;
        }
        String header = matches.length + " task(s) of congruence "
                + "have been uncovered.";
        ui.buildResponse(header);
        ui.buildResponse(matches);
        ui.completeResponse();
    }
}
