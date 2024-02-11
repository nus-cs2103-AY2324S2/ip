package earl.logic;

import java.util.stream.IntStream;

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
        String[] response = list(tasks);
        ui.makeResponse(response);
    }

    /** Returns the contents of tasks in printable format. */
    private static String[] list(TaskList tasks) {
        assert tasks.getSize() > 0;
        return IntStream.range(0, tasks.getSize())
                .mapToObj((x) -> x + 1 + "." + tasks.get(x))
                .toArray(String[]::new);
    }
}
