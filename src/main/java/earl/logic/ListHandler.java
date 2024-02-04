package earl.logic;

import earl.util.TaskList;
import earl.util.Ui;

public final class ListHandler extends Handler {
    public void handle(TaskList tasks, Ui ui) {
        if (!tasks.isEmpty()) {
            int n = tasks.getSize();
            String[] temp = new String[n];
            for (int i = 0; i < n; ++i) {
                temp[i] = i + 1 + "." + tasks.get(i);
            }
            ui.makeResponse(temp);
        } else {
            ui.makeResponse("There is nothing to list.");
        }
    }
}
