package earl.logic;

import java.util.ArrayList;
import java.util.List;

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
    public static String[] list(TaskList tasks) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); ++i) {
            result.add(i + 1 + "." + tasks.get(i));
        }
        return result.toArray(new String[tasks.getSize()]);
    }
}
