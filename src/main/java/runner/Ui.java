package runner;
import task.Task;

/**
 * Class for Ui object.
 */
public class Ui {
    /**
     * Words shown when initiating.
     * @returns Greeting.
     */
    public static String start() {
        return "Hi, i'm Pororo" + '\n'
                + "How can i help you?\n" ;
    }

    /**
     * Words shown when terminating.
     * @returns Bye.
     */
    public static String ending() {
        return "Bye bye. Hope to see you soon!\n";
    }

    /**
     * Words shown when marking a Task.
     * @param t Task to be marked.
     * @returns Message after marking.
     */
    public static String markMSG(Task t) {
        return "Good job! I've marked this task as done:\n" + ("[X] " + t.getMSG());
    }

    /**
     * Words shown when unmarking a Task.
     * @param t Task to be unmarked.
     * @returns Message after unmarking.
     */
    public static String unmarkMSG(Task t) {
        return "Noted, I've marked this task as not done yet:\n" + ("[ ] " + t.getMSG());
    }


    /**
     * Words shown when deleting a Task.
     * @param t Task to be deleted.
     * @param n Tasks in TaskList afterwards.
     * @returns Message after deleting.
     */
    public static String deleteMSG(Task t, int n) {
        assert n >= 0 : "Index Invalid";
        return "Got it. I've removed this task:\n"
                + t + "\n"
                + "Now you have " + n + " tasks on the list.";
    }

    /**
     * Words shown when adding a Task.
     * @param t Task to be added.
     * @param n Tasks in TaskList afterwards.
     * @returns Message after adding.
     */
    public static String addMSG(Task t, int n) {
        return "Okay. I've added this task:\n"
                + t + "\n"
                + "Now you have " + n + " tasks in the list.";
    }

    /**
     * Show the TaskList of a Duke.
     * @returns Task information in a TaskList.
     */
    public static String showList(TaskList t, int i) {
        assert i == 1 || i == 0 : "Invalid Sign";
        String find_show  = (i == 1) ? "Pompom here are the tasks on your list:\n"
                : "Pompom here are the matching tasks in your list:\n";
        StringBuilder ans = new StringBuilder(find_show);
        for (Task tk : t.getList()) {
            String info = (t.getList().indexOf(tk)+1) + "." + tk.toString() + "\n";
            ans.append(info);
        }
        return ans.toString();
    }
}

