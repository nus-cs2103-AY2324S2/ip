package earl.logic;

import earl.util.TaskList;
import earl.util.Ui;

import java.util.ArrayList;

public class FindHandler extends Handler {

    private final String [] COMMAND;
    public FindHandler(String[] command) {
        COMMAND = command;
    }

    @Override
    public void handle(TaskList tasks, Ui ui) {
        int n = tasks.getSize();
        String pattern = COMMAND[1];
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
