package datuk;

import java.util.ArrayList;
import datuk.task.Task;

/**
 * UI class handles interactions with the user.
 */

public class UI {
    public UI() {
    }

    /**
     * Returns the initial message.
     *
     */
    public String startMsg(String reminder) {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings friend! I am Datuk\n"
                + "How can I serve you today? ^_^' \n\n");
        sb.append(reminder);
        return sb.toString();
    }

    /**
     * Returns parting message.
     *
     * @return
     */
    public String byeMsg() {
        return "Farewell!\n";
    }

    /**
     * Returns error message.
     *
     * @param de
     * @return
     */
    public String showError(DatukException de) {
        return de.toString();
    }

    /**
     * Returns an ordered list of Tasks.
     *
     * @param taskList an ArrayList of all the Tasks
     */
    public String printList(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("These are all your tasks:\n");

        if (taskList.isEmpty()) {
            sb.append("\tOh noes! The list is empty! :(");
            return sb.toString();
        }

        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\t" + (i + 1) + ". " + taskList.get(i) + "\n");
        }

        return sb.toString();
    }

    /**
     * Returns an ordered list of Tasks found.
     *
     * @param taskList an ArrayList of Tasks found.
     * @return an ordered list.
     */
    public String printFindList(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        if (taskList.isEmpty()) {
            sb.append("\tOh noes! The list is empty! :(");
            return sb.toString();
        }

        sb.append("These are all the tasks related:\n");

        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\t" + (i + 1) + ". " + taskList.get(i) + "\n");
        }

        return sb.toString();
    }

    /**
     * Returns a message of the task added and the number of remaining tasks.
     *
     * @param t The object of the Task added to the list
     * @param size The new size of the list.
     */
    public String showAddMsg(Task t, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Understood. Added the following:\n");
        sb.append("\t " + t + "\n");
        sb.append("You have " + size + " remaining tasks.");

        return sb.toString();
    }

    /**
     * Returns a message of the task deleted and the number of remaining tasks.
     *
     * @param t The object of the Task removed from the list
     * @param size The new size of the list.
     */
    public String showDeleteMsg(Task t, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Removed the following: \n");
        sb.append("\t" + t + "\n");
        sb.append((size-1) + " tasks remaining.");
        return sb.toString();
    }

    /**
     * Returns a String of the Task that has been completed.
     *
     * @param desc the description of the Task.
     */
    public String showMark(String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("I have set this task < " + desc + " > as completed." );
        return sb.toString();
    }

    /**
     * Returns a String of the Task that has not been completed.
     *
     * @param desc the description of the Task.
     */
    public String showUnmark(String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("I have set this task < " + desc + " > as incomplete." );
        return sb.toString();
    }

    /**
     * Returns a String of the Task that is going to expire the earliest.
     *
     * @param desc the description of the Task.
     * @param date that the task is due
     */
    public String showReminder(String desc, String... date) {
        StringBuilder sb = new StringBuilder();

        assert date.length == 1 : "There should only be one date";

        if (desc.equals("none")) {
            sb.append("There are no tasks due soon");
        } else {
            sb.append("REMINDER: Task " + desc + " expiring on " + date[0]);
        }

        return sb.toString();
    }
}
