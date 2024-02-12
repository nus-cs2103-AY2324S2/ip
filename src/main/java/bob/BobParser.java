package bob;

/**
 * Parse user input into commands.
 */
public class BobParser {

    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";
    public static final String SORT_COMMAND = "sort";

    private BobUI ui;
    private BobTaskList taskList;

    public BobParser setUi(BobUI ui) {
        this.ui = ui;
        return this;
    }

    public BobParser setTaskList(BobTaskList taskList) {
        this.taskList = taskList;
        return this;
    }

    /**
     * Process user input and calls the appropriate handler.
     *
     * @param input User input in plain text.
     */
    public String processInput(String input) {

        assert !input.trim().isEmpty(); // should not accept empty input.

        String command = input.split("\\s+")[0];

        try {
            switch (command) {
            case BobParser.LIST_COMMAND:
                return ui.getTaskListText(false, taskList.getList());
            case BobParser.MARK_COMMAND:
            case BobParser.UNMARK_COMMAND:
                return taskList.handleTaskMarking(input);
            case BobParser.TODO_COMMAND:
            case BobParser.DEADLINE_COMMAND:
            case BobParser.EVENT_COMMAND:
                return taskList.handleTaskCreation(input);
            case BobParser.DELETE_COMMAND:
                return taskList.handleTaskDeletion(input);
            case BobParser.FIND_COMMAND:
                return taskList.handleFindTask(input);
            case BobParser.SORT_COMMAND:
                return taskList.handleSortTasks(input)
                        + " " + ui.getTaskListText(false, taskList.getList());
            default:
                throw new BobException.InvalidCommand(BobErrorMessages.UNKNOWN_COMMAND);
            }
        } catch (BobException e) {
            return ui.getErrorText(e);
        }
    }
}
