package duke.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import duke.TaskList;
import duke.task.Task;

/**
 * Class for finding requested tasks.
 */
public class Find implements Command {
    private ArrayList<Task> foundList;

    public Find(String keyWord, TaskList tasks) {
        this.foundList = new ArrayList<>();
        Pattern pattern = Pattern.compile(keyWord, Pattern.CASE_INSENSITIVE);
        for (Task task : tasks.getTaskList()) {
            if (pattern.matcher(task.getDescription()).find()) {
                foundList.add(task);
            }
        }
    }

    @Override
    public String reply() {
        if (foundList.size() == 0) {
            return "No task matches :(\n";
        }
        String reply = "    Here are the matching tasks in your list:\n";
        int i = 0;
        for (Task task : foundList) {
            reply += String.format("    %s.%s\n", ++i, task);
        }
        return reply;
    }
}
