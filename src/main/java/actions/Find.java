package actions;

import java.util.ArrayList;

import tasks.Task;
import ui.Duke;

public class Find implements Action {
    private String search;

    public Find(String search) {
        this.search = search;
    }

    @Override
    public String execute(Duke bot) {
        ArrayList<Task> list = bot.getTaskList().getList();
        ArrayList<Task> matches = new ArrayList<>();

        for (Task t : list) {
            if (t.getDescription().contains(this.search)) {
                matches.add(t);
            }
        }

        if (matches.size() > 0) {
            StringBuilder stringOfMatches = new StringBuilder("Here are the matching tasks in your list:");
            int index = 1;
            for (Task task : matches) {
                stringOfMatches.append(String.format("%d. [%s] [%s] %s", index, task.getTypeIcon(), task.getStatusIcon(),
                        task.getDescription()));
                index++;
            }
            return stringOfMatches.toString();
        } else {
            return ("No matches found!");
        }
    }
}
