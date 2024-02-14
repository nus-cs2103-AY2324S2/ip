package runner;
import task.Task;

/**
 * Class for Ui object.
 */
public class Ui {
    /**
     * Words shown when initiating.
     */
    public static String start() {
        return "Hello! I'm Duke\nWhat can I do for you?\n" ;
    }

    /**
     * Words shown when terminating.
     */
    public static String ending() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Words shown when marking a Task.
     * @param t Task to be marked.
     */
    public static String markMSG(Task t) {
        return "Nice! I've marked this task as done:\n" + ("[X] " + t.getMSG());
    }

    /**
     * Words shown when unmarking a Task.
     * @param t Task to be unmarked.
     */
    public static String unmarkMSG(Task t) {
        return "OK, I've marked this task as not done yet:\n" + ("[ ] " + t.getMSG());
    }


    /**
     * Words shown when deleting a Task.
     * @param t Task to be deleted.
     * @param n Tasks in TaskList afterwards.
     */
    public static String deleteMSG(Task t, int n) {
        assert n >= 0 : "Index Invalid";
        return "Noted. I've removed this task:\n"
                + t + "\n"
                + "Now you have " + n + " tasks in the list.";
    }

    /**
     * Words shown when adding a Task.
     * @param t Task to be added.
     * @param n Tasks in TaskList afterwards.
     */
    public static String addMSG(Task t, int n) {
        return "Got it. I've added this task:\n"
                + t + "\n"
                + "Now you have " + n + " tasks in the list.";
    }

    /**
     * Show the TaskList of a Duke.
     */
    public static String showList(TaskList t, int i) {
        assert i == 1 || i == 0 : "Invalid Sign";
        String find_show  = (i == 1) ? "Here are the tasks in your list:\n"
                : "Here are the matching tasks in your list:\n";
        StringBuilder ans = new StringBuilder(find_show);
        for (Task tk : t.get_list()) {
            String info = (t.get_list().indexOf(tk)+1) + "." + tk.toString() + "\n";
            ans.append(info);
        }
        return ans.toString();
    }
}