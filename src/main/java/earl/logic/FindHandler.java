package earl.logic;

import java.util.stream.Stream;

import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the find command.
 */
public class FindHandler extends Handler {

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
            ui.makeResponse("There are no matches.");
            return;
        }
        String header = "There are " + matches.length + " matching entries.";
        String[] result = Stream.concat(Stream.of(header), Stream.of(matches))
                .toArray(String[]::new);
        ui.makeResponse(result);
    }
}
