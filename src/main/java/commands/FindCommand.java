package commands;

import task.Task;

/**
 * Encapsulates a find command.
 */
public class FindCommand extends Command {

    private final String keyword;
    public static final String COMMAND = "find";

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches TaskList and return list of tasks based on supplied keyword.
     */
    @Override
    public void execute() {
        int count = 1;
        System.out.println("Here are the list of tasks that matches your keyword: [" + keyword + "]");
        for (Task t : tasks.getList()) {
            if (t.getTask().contains(keyword)) {
                System.out.println(count + ". " + t);
                count++;
            }
        }
    }
}
