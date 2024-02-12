package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class to manage CLI interactions
 */
public class Ui {

    /** Line separator */
    private static final String TXT_NODESC = "Please provide the description of your task!\n";

    /**
     * Prints welcome lines for user during initial start-up
     */
    public String intro() {
        return "Hello! I'm Megatron\n"
                + "What can I do for you?\n";
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
     * Prints exit lines to the user
     */
    public String exit() {
        String outroTxt = "Bye. Hope to see you again soon!\n";
        return outroTxt;
    }

    /**
     * Prints the list of tasks given
     *
     * @param store List of tasks to be printed
     */
    public String list(List<Task> store) {
        StringBuilder listingTxt = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < store.size(); i++) {
            listingTxt.append(" ").append(i + 1).append(".").append(store.get(i)).append("\n");
        }
        return "" + listingTxt;
    }

    /**
     * Prints details of task added to the user
     *
     * @param newTask task that was added
     * @param numItems number of items currently in the list
     */
    public String addTask(Task newTask, int numItems) {
        String addTaskTxt = "Got it. I've added this task:\n";
        return addTaskTxt + "\t" + newTask + "\n"
                + "Now you have " + numItems + " tasks in the list.\n";
    }

    /**
     * Prints details of task deleted to the user
     *
     * @param toRemove task that was deleted
     * @param numItems number of items left in the list
     */
    public String deleteTask(Task toRemove, int numItems) {
        String deleteTxt = "Noted. I will remove this task for you:\n";
        return deleteTxt + "\t" + toRemove + "\n"
                + "Now you have " + numItems + " tasks in the list.\n";
    }

    /**
     * Prints details of task updated to the user
     *
     * @param updateTask task that was updated
     * @param isComplete completion status of updated task
     */
    public String mark(Task updateTask, boolean isComplete) {
        String markTxt = "Nice! I've marked this task as done:\n";
        String unmarkTxt = "OK, I've marked this task as not done yet:\n";
        String markFormTxt = "Sorry! To mark or unmark tasks, please do\n"
                + "\t(un)mark (number)\n";

        String toPrint = isComplete ? markTxt : unmarkTxt;
        return toPrint + "\t" + updateTask + "\n";
    }

    /**
     * Prints search results to user
     *
     * @param searchList containing list of task matched
     */
    public String find(List<Task> searchList) {
        StringBuilder findTxt = new StringBuilder("Here's what I found:\n");
        for (int i = 0; i < searchList.size(); i++) {
            findTxt.append(" ").append(i + 1).append(".").append(searchList.get(i)).append("\n");
        }
        return "" + findTxt;
    }

    /**
     * Prints error line to the user
     *
     * @param errorMsg containing details of the issue
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
}
