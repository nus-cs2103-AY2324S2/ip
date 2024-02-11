package fishstock;

import javafx.application.Platform;

class Command {
    private static final String LIST_PREFIX = "Here are the tasks in your list:\n";
    private static final String MARK_PREFIX = "Did you actually finish this? \uD83E\uDD14:\n  ";
    private static final String UNMARK_PREFIX = "I knew you didn't finish it! \uD83D\uDE0F:\n  ";
    private static final String DELETE_PREFIX = "This task has been removed:\n  ";
    private static final String FIND_PREFIX = "Here are the matching tasks in your list:\n";
    private static final String ADD_TASK_PREFIX = "This task has been added:\n  ";
    private static final String UNKNOWN_COMMAND = "OH NOSE! Wakaranai... :(";

    /**
     * List of keywords to run respective commands.
     */
    public enum Keyword {
        INVALID, BYE, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT
    }

    /**
     * Gets number of tasks from list.
     * @param list The list of Tasks.
     * @return A user-readable String format of task count.
     */
    private static String getTaskCount(TaskList list) {
        return "\nNow you have " + list.getSize() + " task(s) in total.";
    }

    /**
     * Executes the command.
     * @param list The list of Tasks to execute on.
     * @param keyword The command.
     * @param input The user input.
     * @return The output from the command.
     */
    protected static String runCommand(TaskList list, Keyword keyword, String input) {
        try {
            switch (keyword) {
            case BYE:
                Platform.exit();
                return ""; // Won't reach here
            case LIST:
                return LIST_PREFIX + list.toString();
            case MARK:
                return MARK_PREFIX + list.changeMark(keyword, input);
            case UNMARK:
                return UNMARK_PREFIX + list.changeMark(keyword, input);
            case DELETE:
                return DELETE_PREFIX + list.deleteTask(input) + getTaskCount(list);
            case FIND:
                return FIND_PREFIX + list.findTasks(input);
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                return ADD_TASK_PREFIX + list.addTask(keyword, input) + getTaskCount(list);
            default:
                return UNKNOWN_COMMAND;
            }
        } catch (FishStockException e) {
            return e.getMessage();
        }
    }
}
