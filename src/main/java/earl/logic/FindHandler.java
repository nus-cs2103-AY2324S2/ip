package earl.logic;

import java.util.ArrayList;

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
        int n = tasks.getSize();
        ArrayList<String> matches = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (tasks.get(i).toString().contains(args)) {
                matches.add(i + 1 + "." + tasks.get(i).toString());
                ++count;
            }
        }
        if (matches.isEmpty()) {
            ui.makeResponse("There are no matches.");
            return;
        }
        matches.add(0, "There are " + count + " matching entries.");
        ui.makeResponse(matches.toArray(new String[count + 1]));
    }
}
