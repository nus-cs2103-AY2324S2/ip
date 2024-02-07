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
        return "I am  Pororo, your personal task manager. How may I assist you today?";
    }

    /**
     * Words shown when terminating.
     */
    public static String ending() {
        return "It's great working with you! "
                + '\n' + "See you again soon. BYE!!!";
    }

    /**
     * Words shown when marking a Task.
     * @param t Task to be marked.
     */
    public static String markMSG(Task t) {
        return "Well Done! I've marked this task as done:\n" + ("[X] " + t.getMsg());
    }

    /**
     * Words shown when unmarking a Task.
     * @param t Task to be unmarked.
     */
    public static String unmarkMSG(Task t) {
        return "Noted, I've marked this task as not done yet:\n" + ("[ ] " + t.getMsg());
    }


    /**
     * Words shown when deleting a Task.
     * @param t Task to be deleted.
     * @param n Tasks in TaskList afterwards.
     */
    public static String deleteMSG(Task t, int n) {
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
        return "Noted. I've added this task:\n"
                + t + "\n"
                + "Now you have " + n + " tasks in the list.";
    }

    /**
     * Show the TaskList of a Duke.
     */
    public static String showList(TaskList t) {
        StringBuilder ans = new StringBuilder("Here are the tasks in your list:\n");
        for (Task tk : t.get_list()) {
            String info = (t.get_list().indexOf(tk)+1) + "." + tk.toString() + "\n";
            ans.append(info);
        }
        return ans.toString();
    }

    /**
     * Show the TaskList after find keywords.
     */
    public static String findList(TaskList t) {
        StringBuilder ans = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task tk : t.get_list()) {
            String info = (t.get_list().indexOf(tk)+1) + "." + tk.toString() + "\n";
            ans.append(info);
        }
        return ans.toString();
    }
}
