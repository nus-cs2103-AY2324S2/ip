package duke.actions;

import java.util.ArrayList;

import duke.KBot.TaskManager;
import duke.tasks.Task;

public class FindTask extends Command {
    private String key;

    public FindTask(String key) {
        this.key = key;
    }

    @Override
    public String execute() {
        ArrayList<Task> found = new ArrayList<>();
        for (Task t : TaskManager.getTasks()) {
            if (t.find(key)) {
                found.add(t);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < found.size(); i++) {
            sb.append((i + 1)).append(". ").append(found.get(i)).append("\n");
        }
        return sb.toString();
    }
}