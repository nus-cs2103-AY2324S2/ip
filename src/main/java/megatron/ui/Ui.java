package megatron.ui;

import java.util.List;
import java.util.Scanner;

import megatron.task.Task;

/**
 * Ui class to manage CLI interactions
 */
public class Ui {

    /** Line separator */
    private static final String TXT_NODESC = "Please provide the description of your task!\n";

    /**
     * Returns welcome lines for user during initial start-up
     * @return introduction message
     */
    public static String intro() {
        return "Hello! I'm Megatron\n"
                + "Before I launch you straight into the sun, what can I do for you?\n";
    }

    /**
     * Reads keyboard commands by the user
     * @return String of instructions given
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Returns exit lines
     * @return exit message
     */
    public String exit() {
        String outroTxt = "Bye, I'm off to find the SPARK!\n";
        return outroTxt;
    }

    /**
     * Returns the list of tasks given
     *
     * @param store List of tasks to be printed
     * @return message for list of tasks
     */
    public String list(List<Task> store) {
        StringBuilder listingTxt = new StringBuilder("You humans... here are the tasks in your list:\n");
        for (int i = 0; i < store.size(); i++) {
            listingTxt.append(" ").append(i + 1).append(".").append(store.get(i)).append("\n");
        }
        return "" + listingTxt;
    }

    /**
     * Returns details of task added to the user
     *
     * @param newTask task that was added
     * @param numItems number of items currently in the list
     * @return details of task added message
     */
    public String addTask(Task newTask, int numItems) {
        String addTaskTxt = "You underestimate me. I've added this task:\n";
        return addTaskTxt + "\t" + newTask + "\n"
                + "Now you have " + numItems + " tasks in the list.\n";
    }

    /**
     * Returns details of task deleted
     *
     * @param toRemove task that was deleted
     * @param numItems number of items left in the list
     * @return details of task deletion message
     */
    public String deleteTask(Task toRemove, int numItems) {
        String deleteTxt = "I am LEADER of the DECEPTICONS. I will remove this task for you:\n";
        return deleteTxt + "\t" + toRemove + "\n"
                + "Now you have " + numItems + " tasks in the list.\n";
    }

    /**
     * Returns details of task updated
     *
     * @param updateTask task that was updated
     * @param isComplete completion status of updated task
     * @return task marked or unmarked message
     */
    public String mark(Task updateTask, boolean isComplete) {
        String markTxt = "Wow! I've marked this task as done:\n";
        String unmarkTxt = "Foolish humans, I've marked this task as not done yet:\n";
        String markFormTxt = "Human! To mark or unmark tasks, please do\n"
                + "\t(un)mark (number)\n";

        String toPrint = isComplete ? markTxt : unmarkTxt;
        return toPrint + "\t" + updateTask + "\n";
    }

    /**
     * Returns search results to user
     *
     * @param searchList containing list of task matched
     * @return search results message
     */
    public String find(List<Task> searchList) {
        StringBuilder findTxt = new StringBuilder("Quick, here's what I found:\n");
        for (int i = 0; i < searchList.size(); i++) {
            findTxt.append(" ").append(i + 1).append(".").append(searchList.get(i)).append("\n");
        }
        return "" + findTxt;
    }

    /**
     * Returns error message
     *
     * @param errorMsg containing details of the issue
     * @return error message
     */
    public String showError(String errorMsg) {
        if (errorMsg.equals("Description Blank")) {
            return TXT_NODESC;
        } else if (errorMsg.equals("Invalid Number")) {
            return "Invalid number given! :(\n";
        } else {
            return errorMsg;
        }
    }

    /**
     * Returns message that last command was already undone
     *
     * @return last command already undone message
     */
    public String lastCommandUndoed() {
        return "It seems you are trying to undo what has already been undone...";
    }

    /**
     * Returns message that add has been undone
     *
     * @return message for undo-ing task addition
     */
    public String undoAdd(Task reversedTask) {
        String undoAddTaskTxt = "You AUTOBOTS are so indecisive!\n";
        return undoAddTaskTxt + "\t" + reversedTask + "\n"
                + "has been removed.\n";
    }

    /**
     * Returns message that delete has been undone
     *
     * @return message for undo-ing task deletion
     */
    public String undoDelete(Task reversedTask) {
        String undoDeleteTaskTxt = "You are lucky I kept it!\n";
        return undoDeleteTaskTxt + "\t" + reversedTask + "\n"
                + "has been added back.\n";
    }

    /**
     * Returns message that nothing left to undo
     *
     * @return message for nothing to undo
     */
    public String nothingToUndo() {
        String nothingToUndoTxt = "There's nothing left to undo. I am MEGATRON!";
        return nothingToUndoTxt;
    }

    /**
     * Returns message that unmark / mark has been undone
     *
     * @return message for undo-ing mark or unmark
     */
    public String undoMark(boolean isComplete) {
        String undoMarkTxt = isComplete ? "re-marked " : "unmarked ";
        undoMarkTxt = "Who do you think I am?\n"
                + "Fine, I have " + undoMarkTxt
                + "the task for you.";
        return undoMarkTxt;
    }

}
