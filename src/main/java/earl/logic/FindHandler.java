package earl.logic;

import java.util.ArrayList;

import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for the find command.
 */
public class FindHandler extends Handler {

    private final String [] command;

    /**
     * Class constructor.
     *
     * @param command  the user input that invoked this handler
     */
    public FindHandler(String[] command) {
        this.command = command;
    }

    @Override
    public void handle(TaskList tasks, Ui ui) {
        int n = tasks.getSize();
        String pattern = command[1];
        ArrayList<String> temp = new ArrayList<>();
        int c = 0;
        for (int i = 0; i < n; ++i) {
            if (tasks.get(i).toString().contains(pattern)) {
                temp.add(i + 1 + "." + tasks.get(i).toString());
                ++c; // increment count
            }
        }
        if (!temp.isEmpty()) {
            temp.add(0, "There are " + c + " matching entries.");
            ui.makeResponse(temp.toArray(new String[c + 1]));
        } else {
            ui.makeResponse("There are no matches.");
        }
    }
}
