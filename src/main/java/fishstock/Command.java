package fishstock;

import javafx.application.Platform;

class Command {
    private static final String LIST_PREFIX = "Here are the tasks in your list:\n";
    private static final String MARK_PREFIX = "Did you actually finish this? \uD83E\uDD14:\n  ";
    private static final String UNMARK_PREFIX = "I knew you didn't finish it! \uD83D\uDE0F:\n  ";
    private static final String DELETE_PREFIX = "This task has been removed:\n  ";
    private static final String FIND_PREFIX = "Here are the matching tasks in your list:\n";
    private static final String UNDO_PREFIX = "List has been restored to the previous state:\n";
    private static final String ADD_TASK_PREFIX = "This task has been added:\n  ";
    private static final String UNKNOWN_COMMAND = "OH NOSE! Wakaranai... :(";

    /**
     * List of keywords to run respective commands.
     */
    public enum Keyword {
        INVALID, BYE, LIST, MARK, UNMARK, DELETE, FIND, UNDO,
        TODO("T"), DEADLINE("D"), EVENT("E");

        private String shortened;
        Keyword() {
        }
        Keyword(String shortened) {
            this.shortened = shortened;
        }

        public String getShortened() {
            return shortened;
        }

        public static Keyword findShortened(String shortened) {
            for (Keyword keyword : Keyword.values()) {
                if (shortened.equals(keyword.shortened)) {
                    return keyword;
                }
            }
            return Keyword.INVALID;
        }
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
     * @param input The user input.
     * @return The output from the command.
     */
    protected static String runCommand(TaskList list, UserInput input) {
        Keyword keyword = input.getCommandType();

        try {
            switch (keyword) {
            case BYE:
                Platform.exit();
                return ""; // Won't reach here
            case LIST:
                return LIST_PREFIX + list.toString();
            case MARK:
                return MARK_PREFIX + list.markTask(input);
            case UNMARK:
                return UNMARK_PREFIX + list.unmarkTask(input);
            case DELETE:
                return DELETE_PREFIX + list.deleteTask(input) + getTaskCount(list);
            case FIND:
                return FIND_PREFIX + list.findTasks(input);
            case UNDO:
                list.restoreState();
                return UNDO_PREFIX + list.toString();
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                return ADD_TASK_PREFIX + list.addTask(input) + getTaskCount(list);
            default:
                return UNKNOWN_COMMAND;
            }
        } catch (FishStockException e) {
            return e.getMessage();
        }
    }
}
