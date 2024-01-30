package commands;

import java.util.ArrayList;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;
import tasks.Task;

public class Find implements Command {
    private final String query;

    public Find(String query) {
        this.query = query.toLowerCase(); // make query case-insensitive
    }

    public void execute(TaskList taskList, UI ui) throws ConvoBotException {
        ArrayList<String> matchingTaskStrings = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String desc = taskList.getTaskDescription(i).toLowerCase(); // query is case-insensitive
            if (desc.contains(query)) {
                matchingTaskStrings.add(taskList.getTaskString(i));
            }
        }
        ui.showMatchingTasks(matchingTaskStrings);
    }

    public boolean isExit() {
        return false;
    }
}
