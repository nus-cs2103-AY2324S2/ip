package awex;

import java.io.IOException;
import java.util.LinkedList;
import tasks.Task;

/**
 * Represents an object providing all possible UI responses to the user.
 */
public class Ui {
    /**
     * Prints first message when user activates Awex.
     */
    public String greeting() {
        return "Hello! I'm AWEX!\n"
                + "What can I do for you?";
    }

    /**
     * Prints last message after user deactivates Awex.
     */
    public String farewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints explainer message when user gives erroneous inputs.
     */
    public String allInstructions() {
        return "Input type must be one of:\n"
                + "  1. list\n"
                + "  2. mark <task number>\n"
                + "  3. unmark <task number>\n"
                + "  4. todo <task>\n"
                + "  5. deadline <task> /by <YYYY-MM-DD hh:mm>\n"
                + "  6. event <task> /from <YYYY-MM-DD hh:mm> /to <YYYY-MM-DD hh:mm>\n"
                + "  7. delete <task number>\n"
                + "  8. find <item>\n"
                + "Type 'bye' to exit.";
    }

    /**
     * Prints error message if list is empty.
     */
    public String emptyListMessage() {
        return "List is empty.";
    }

    /**
     * Prints tasks saved in list and their details.
     *
     * @param list TaskList with saved tasks
     */
    public String showListMessage(TaskList list) {
        String str = "Here are the tasks in your list:\n";
        int len = list.size();
        for (int i = 1; i <= len; i++) {
            str += i + "." + list.get(i - 1).showAll() + "\n";
        }
        return str;
    }

    /**
     * Prints
     *
     * @param tasks TaskList with saved tasks
     * @param str String each task description to be compared to
     */
    public String showFindMessage(TaskList tasks, String str) {
        String stri = "Here are the matching tasks in your list:\n";
        LinkedList<Task> list = tasks.find(str);
        int len = list.size();
        for (int i = 1; i <= len; i++) {
            stri += i + "." + list.get(i - 1).showAll() + "\n";
        }
        return stri;
    }

    /**
     * Prints explainer message when user gives erroneous inputs when marking, unmarking or deleting tasks.
     */
    public String wrongMarkDeleteFormatMessage(String str) {
        return "Format should be '" + str + " <task number>'";
    }

    /**
     * Prints explainer message when user provides inaccessible list index.
     */
    public String wrongIndexMessage(int i, int len) {
        if (i == 0) {
            return "Pick a value between 1 and " + len + ".";
        } else {
            return "List has only " + len + " tasks.";
        }
    }

    /**
     * Prints notification message when task deleted successfully.
     */
    public String deleteTaskMessage(int i, TaskList list) {
        return "Noted. I've removed this task:\n"
                + "  " + list.remove(i - 1).showAll() + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Prints notification message when task marked/unmarked successfully.
     */
    public String changeStatusMessage(String str, Task t) {
        if (str.equals("mark")) {
            return "Nice! I've marked this task as done:\n" + "  " + t.showAll();
        } else {
            return "OK, I've marked this task as not done yet:\n" + "  " + t.showAll();
        }
    }

    /**
     * Prints explainer message when user provides erroneous inputs while making tasks.
     */
    public String failedTaskCreationMessage(String str) {
        if (str.equals("todo")) {
            return "Format should be 'todo <task>'";
        } else if (str.equals("deadline")) {
            return "Format should be 'deadline <task> /by <YYYY-MM-DD hh:mm>'";
        } else {
            return "Format should be 'event <task> /from <YYYY-MM-DD hh:mm> /to <YYYY-MM-DD hh:mm>'";
        }
    }

    /**
     * Prints notification message when task saved successfully.
     */
    public String newTaskAddedMessage(int i, Task t) {
        return "Got it. I've added this task:\n"
                + "  " + t.showAll() + "\n"
                + "Now you have " + i + " tasks in the list.";
    }
}