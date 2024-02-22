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
     *
     * @return String representation of the results found matching keyword.
     */
    @Override
    public String executeCommand() {
        int count = 1;
        String result = "Here are the list of tasks that matches your keyword: [" + keyword + "]\n";
        printMessage(result);
        for (Task t : taskList.getList()) {
            if (t.getTask().contains(keyword)) {
                System.out.println(count + ". " + t);
                result += count + ". " + t + "\n";
                count++;
            }
        }

        return result;
    }

    private static void printMessage(String result) {
        System.out.print(result);
    }
}
