package task;

import parser.ParseExecutionable;
import util.Messages;

/**
 * Represents an ActionTask.
 *
 * This class is the representation of a ActionTask, that defines a certain action.
 * It implements from ParseExecutionable to execute it's required action after being parsed.
 */
public class ActionTask implements ParseExecutionable {
    public static final String LIST_TYPE = "list";
    public static final String DELETE_TYPE = "delete";
    public static final String MARK_TYPE = "mark";
    public static final String UNMARK_TYPE = "unmark";
    public static final String FIND_TYPE = "find";
    public static final String SORT_TYPE = "sort";
    public static final String BYE_TYPE = "bye";

    private ActionType action;
    private int taskId;
    private boolean isExit;
    private String searchKeyword;

    /**
     * Represents the commands that user can input, and a UNRECOGNIZED as well.
     *
     * This allows the application to handle various commands, and any addition
     * will be caught here as well. This ensures type safety in handling these
     * various actions.
     */
    public static enum ActionType {
        LIST, DELETE, MARK, UNMARK, BYE, UNRECOGNIZED, FIND, SORT, SORT_TODO, SORT_DEADLINE, SORT_EVENT
    }

    /**
     * Creates a ActionTask object.
     * This constructor is primarily used for actions that do not specify a specific Task.
     *
     * @param action The ActionType of the action.
     */
    public ActionTask(ActionType action) {
        this.action = action;
        this.isExit = false;
    }

    /**
     * Creates a ActionTask object.
     * This constructor is primarily used for actions that specify a specific Task.
     *
     * @param action The ActionType of the action.
     * @param taskId The specified Id for the action.
     */
    public ActionTask(ActionType action, int taskId) {
        this.action = action;
        this.taskId = taskId;
        this.isExit = false;
    }

    /**
     * Creates a ActionTask object.
     * This constructor is primarily used for the find action,
     * where we are searching for a specific Task/s.
     *
     * @param action The ActionType of the action.
     * @param searchKeyword The specified keyword for the search.
     */
    public ActionTask(ActionType action, String searchKeyword) {
        this.action = action;
        this.searchKeyword = searchKeyword;
        this.isExit = false;
    }

    /**
     * Informs the user/code whether this action is an exit action.
     */
    public boolean isItExitAction() {
        return this.isExit;
    }

    /**
     * Executes the necessary action created from the parsed results.
     * Will have different actions depending on the ActionType that is set in the object.
     * @param taskStorage The storage space where the action will take place.
     */
    @Override
    public String execute(TaskStorage taskStorage) {
        assert taskStorage != null : "TaskStorage should not be not instantiated!";
        switch (this.action) {
        case LIST:
            return this.listActions(taskStorage);
        case MARK:
            return this.markActions(taskStorage);
        case UNMARK:
            return this.unmarkActions(taskStorage);
        case DELETE:
            return this.deleteActions(taskStorage);
        case BYE:
            return this.byeActions(taskStorage);
        case SORT:
            return this.sortActions(taskStorage);
        case SORT_TODO:
            return this.sortToDoActions(taskStorage);
        case SORT_DEADLINE:
            return this.sortDeadlineActions(taskStorage);
        case SORT_EVENT:
            return this.sortEventActions(taskStorage);
        case FIND:
            return this.findActions(taskStorage);
        default:
            return Messages.MESSAGE_NO_SUCH_COMMAND;
        }
    }

    /**
     * Creates the String for the list command by listing all tasks in storage.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String listActions(TaskStorage taskStorage) {
        if (taskStorage.size() > 0) {
            return taskStorage.toString();
        }
        return Messages.MESSAGE_EMPTY_LIST;
    }

    /**
     * Marks the task specified by the user.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String markActions(TaskStorage taskStorage) {
        String printMessage = "Nicely done! I've marked this task as done: \n";
        String resultMessage = taskStorage.markTask(this.taskId, true);
        if (resultMessage.equals(Messages.MESSAGE_NO_SUCH_ELEMENTS)) {
            return resultMessage;
        }
        printMessage += resultMessage;
        return printMessage;
    }

    /**
     * Unmarks the task specified by the user.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String unmarkActions(TaskStorage taskStorage) {
        String printMessage = "Hey you! I've marked this task as not done, yet: \n";
        String resultMessage = taskStorage.markTask(this.taskId, false);
        if (resultMessage.equals(Messages.MESSAGE_NO_SUCH_ELEMENTS)) {
            return resultMessage;
        }
        printMessage += resultMessage;
        return printMessage;
    }

    /**
     * Deletes the task specified by the user.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String deleteActions(TaskStorage taskStorage) {
        String printMessage = "Alright-o, I have deleted the following task: \n";
        String resultMessage = taskStorage.removeTask(this.taskId);
        if (resultMessage.equals(Messages.MESSAGE_NO_SUCH_ELEMENTS)) {
            return resultMessage;
        }
        printMessage += resultMessage;
        return printMessage;
    }

    /**
     * Sorts the session StorageList in the user session.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String sortActions(TaskStorage taskStorage) {
        String printMessage = "Sorted the Tasks for you: \n";
        TaskStorage sortedTaskStorage = taskStorage.sortStorageList();
        printMessage += sortedTaskStorage.toString();
        return printMessage;
    }

    /**
     * Sorts only the ToDo tasks in StorageList in the user session.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String sortToDoActions(TaskStorage taskStorage) {
        String printMessage = "Sorted the Todos for you: \n";
        printMessage += taskStorage.sortToDoStorageList().toString();
        return printMessage;
    }

    /**
     * Sorts only the Deadline tasks in StorageList in the user session.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String sortDeadlineActions(TaskStorage taskStorage) {
        String printMessage = "Sorted the Deadlines for you: \n";
        printMessage += taskStorage.sortDeadlineStorageList().toString();
        return printMessage;
    }

    /**
     * Sorts only the Event tasks in StorageList in the user session.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String sortEventActions(TaskStorage taskStorage) {
        String printMessage = "Sorted the Events for you: \n";
        printMessage += taskStorage.sortEventStorageList().toString();
        return printMessage;
    }

    /**
     * Closes the application for the user.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String byeActions(TaskStorage taskStorage) {
        this.isExit = true;
        return "Bye, hope to see you again soon!";
    }

    /**
     * Searches for the task specified by the user in his session storage.
     *
     * @param taskStorage the current user session storage of task.
     * @return the String created from doing this action, for the user to see.
     */
    private String findActions(TaskStorage taskStorage) {
        String result = "Found: \n";
        if (taskStorage.size() > 0) {
            String response = taskStorage.searchForTask(this.searchKeyword);
            if (response.length() == 0) {
                return Messages.MESSAGE_SEARCH_NO_RESULTS;
            }
            return result + response;
        }
        return Messages.MESSAGE_EMPTY_LIST;
    }
}
