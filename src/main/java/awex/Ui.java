package awex;

import java.util.LinkedList;
import tasks.Task;

/**
 * Represents an object providing all possible UI responses to the user.
 */
public class Ui {
    /**
     * Returns String of first message when user activates Awex.
     *
     * @return String of greeting message
     */
    public String greeting() {
        return "Hello! I'm AWEX!\n"
                + "What can I do for you?";
    }

    /**
     * Returns String of last message after user deactivates Awex.
     *
     * @return String of farewell message
     */
    public String farewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns explainer message with user manual.
     *
     * @return String of instructions
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
     * Returns String of uncertainty message when user gives erroneous inputs
     *
     * @return String of uncertainty message
     */
    public String helpMessage() {
        return "Sorry, I didn't understand that.\nType 'help' for instructions!";
    }

    /**
     * Returns String of error message if list is empty.
     *
     * @return String of error message
     */
    public String emptyListMessage() {
        return "List is empty.";
    }

    /**
     * Returns String of tasks saved in list and their details
     *
     * @param list TaskList with saved tasks
     * @return String of all tasks
     */
    public String showListMessage(TaskList list) {
        String str = "Here are the tasks in your list:\n";
        int len = list.size();
        assert len >= 0;
        for (int i = 1; i <= len; i++) {
            str += i + "." + list.get(i - 1).showAll() + "\n";
        }
        return str;
    }

    /**
     * Returns String of tasks with a given string as a substring
     *
     * @param tasks TaskList with saved tasks
     * @param str String each task description to be compared to
     * @return String of tasks resembling substring
     */
    public String showFindMessage(TaskList tasks, String str) {
        String stri = "Here are the matching tasks in your list:\n";
        LinkedList<Task> list = tasks.find(str);
        int len = list.size();
        assert len >= 0;
        for (int i = 1; i <= len; i++) {
            stri += i + "." + list.get(i - 1).showAll() + "\n";
        }
        return stri;
    }

    /**
     * Returns String of explainer message when user gives erroneous inputs when marking, unmarking or deleting tasks.
     *
     * @param str the word "mark", "unmark", or "delete"
     * @return String of explainer message
     */
    public String wrongMarkDeleteFormatMessage(String str) {
        return "Format should be '" + str + " <task number>'";
    }

    /**
     * Returns String of explainer message when user provides inaccessible list index.
     *
     * @param i index given by user
     * @param len length of TaskList
     * @return String of explainer message
     */
    public String wrongIndexMessage(int i, int len) {
        assert len >= 0;
        if (i == 0) {
            return "Pick a value between 1 and " + len + ".";
        } else {
            return "List has only " + len + " tasks.";
        }
    }

    /**
     * Returns String of notification message when task deleted successfully.
     *
     * @param i index given by user
     * @param list list of tasks
     * @return String of notification message
     */
    public String deleteTaskMessage(int i, TaskList list) {
        return "Noted. I've removed this task:\n"
                + "  " + list.remove(i - 1).showAll() + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Returns String of notification message when task marked/unmarked successfully.
     *
     * @param str the word "mark" or "unmark"
     * @param t Task to be marked or unmarked
     * @return String of notification message
     */
    public String changeStatusMessage(String str, Task t) {
        if (str.equals("mark")) {
            return "Nice! I've marked this task as done:\n" + "  " + t.showAll();
        } else {
            return "OK, I've marked this task as not done yet:\n" + "  " + t.showAll();
        }
    }

    /**
     * Returns explainer message when user provides erroneous inputs while making tasks.
     *
     * @param str type of tasks
     * @return String of explainer message
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
     * Returns String of notification message when task saved successfully.
     *
     * @param i new length of list
     * @param t new Task added
     * @return String of notification message
     */
    public String newTaskAddedMessage(int i, Task t) {
        return "Got it. I've added this task:\n"
                + "  " + t.showAll() + "\n"
                + "Now you have " + i + " tasks in the list.";
    }
}