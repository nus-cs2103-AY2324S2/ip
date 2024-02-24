package fishstock;

import javafx.application.Platform;

class Logic {
    private static final String LIST_PREFIX = "Here are the tasks in your list:\n";
    private static final String MARK_PREFIX = "Did you actually finish this? \uD83E\uDD14:\n  ";
    private static final String UNMARK_PREFIX = "I knew you didn't finish it! \uD83D\uDE0F:\n  ";
    private static final String DELETE_PREFIX = "This task has been removed:\n  ";
    private static final String FIND_PREFIX = "Here are the matching tasks in your list:\n";
    private static final String UNDO_PREFIX = "List has been restored to the previous state:\n";
    private static final String HELP_PREFIX = "Here's a list of available commands:\n\n";
    private static final String ADD_TASK_PREFIX = "This task has been added:\n  ";
    private static final String UNKNOWN_COMMAND_TEXT = "OH NOSE! Wakaranai... :(";

    private static String runCommand(TaskList list, UserInput input) throws FishStockException {
        Command command = input.getCommandType();

        switch (command) {
        case BYE:
            Platform.exit();
            return ""; // Won't reach here
        case LIST:
            return LIST_PREFIX + list;
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
            return UNDO_PREFIX + list;
        case HELP:
            return HELP_PREFIX + Command.toList();
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            return ADD_TASK_PREFIX + list.addTask(input) + getTaskCount(list);
        default:
            return UNKNOWN_COMMAND_TEXT;
        }
    }

    /**
     * Runs the command from the UserInput.
     *
     * @param list The list of Tasks to execute on.
     * @param input The user input.
     * @return The output from the command.
     */
    protected static String run(TaskList list, UserInput input) {
        try {
            String result = runCommand(list, input);
            return result;
        } catch (FishStockException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the number of tasks in list.
     *
     * @param list The list of Tasks.
     * @return A user-readable String format of task count.
     */
    private static String getTaskCount(TaskList list) {
        return "\nNow you have " + list.getSize() + " task(s) in total.";
    }
}
