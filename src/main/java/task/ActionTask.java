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
    public static final String BYE_TYPE = "bye";

    private ActionType action;
    private int taskId;
    private boolean isExit;
    private String searchKeyword;

    /**
     * Represents the commands that user can input, and a UNRECOGNIZED as well.
     *
     * This allows the application to handle various commands, and any addition
     * will be caught here as well.
     */
    public static enum ActionType {
        LIST, DELETE, MARK, UNMARK, BYE, UNRECOGNIZED, FIND
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
        String printMessage = "";
        switch (this.action) {
        case LIST:
            if (taskStorage.size() > 0) {
                return taskStorage.toString();
            }
            return Messages.MESSAGE_EMPTY_LIST;
        case MARK:
            printMessage += "Nicely done! I've marked this task as done: \n";
            String resultMessage = taskStorage.markTask(this.taskId, true);
            if (resultMessage.equals(Messages.MESSAGE_NO_SUCH_ELEMENTS)) {
                return resultMessage;
            }
            printMessage += resultMessage;
            return printMessage;
        case UNMARK:
            printMessage += "Hey you! I've marked this task as not done, yet: \n";
            resultMessage = taskStorage.markTask(this.taskId, false);
            if (resultMessage.equals(Messages.MESSAGE_NO_SUCH_ELEMENTS)) {
                return resultMessage;
            }
            printMessage += resultMessage;
            return printMessage;
        case DELETE:
            printMessage += "Alright-o, I have deleted the following task: \n";
            resultMessage = taskStorage.removeTask(this.taskId);
            if (resultMessage.equals(Messages.MESSAGE_NO_SUCH_ELEMENTS)) {
                return resultMessage;
            }
            printMessage += resultMessage;
            return printMessage;
        case BYE:
            this.isExit = true;
            printMessage = "Bye, hope to see you again soon!";
            return printMessage;
        case FIND:
            if (taskStorage.size() > 0) {
                return taskStorage.searchForTask(this.searchKeyword);
            }
            return Messages.MESSAGE_EMPTY_LIST;
        default:
            return "Unrecognized command";
        }
    }
}
