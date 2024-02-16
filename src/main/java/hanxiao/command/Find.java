package hanxiao.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import hanxiao.TaskList;
import hanxiao.task.Task;

/**
 * Class for finding requested tasks.
 */
public class Find implements Command {
    private ArrayList<Task> foundList;

    /**
     * Constructor for find.
     *
     * @param keyWord Keyword for find.
     * @param tasks Task list to search with.
     */
    public Find(String keyWord, TaskList tasks) {
        assert keyWord.length() > 0 : "should have at least one character in keyword";
        this.foundList = new ArrayList<>();
        Pattern pattern = Pattern.compile(keyWord, Pattern.CASE_INSENSITIVE);
        for (Task task : tasks.getTaskList()) {
            if (pattern.matcher(task.getDescription()).find()) {
                foundList.add(task);
            }
        }
    }

    /**
     * Reply with the list of matched tasks.
     *
     * @return List of matched tasks.
     */
    @Override
    public String reply() {
        if (foundList.size() == 0) {
            return "No task matches :(\n";
        }
        String reply = "    Here are the matching tasks in your list:\n";
        int i = 0;
        for (Task task : foundList) {
            reply += String.format("%s.%s\n", ++i, task);
        }
        return reply;
    }
}
