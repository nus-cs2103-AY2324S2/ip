package duke;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;


/**
 * TaskList contains the task list.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * The constructor of Parser.
     *
     * @param taskList The task list which the command will modify.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = taskList;
    }

    /**
     * To show welcome message to user.
     *
     * @param ui The ui to get the input of the user.
     */
    public String welcome(Ui ui) {
        return Ui.showWelcome();
    }

    /**
     * To show goodbye message to user.
     *
     * @param ui The ui to get the input of the user.
     */
    public String bye(Ui ui) {
        return Ui.showGoodbyeMessage();
    }

    /**
     * To print the entire Task array.
     */
    public String showList() {
        String str = Ui.INDENT_SEPERATOR;
        if (taskList.isEmpty()) {
            str = str + "There are currently no tasks in your list";
        } else {
            str = str + Ui.INDENT + "Here are the tasks in your list:";
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                str = str + Ui.INDENT + (i + 1) + "." + t.toString();
            }
        }
        str = str + Ui.INDENT_SEPERATOR;
        return str;
    }

    /**
     * To mark task in the array list.
     * Show 'Task does not exist' if the position is out of range.
     *
     * @param position The index of the specified task in the array list
     *                 to be marked as completed.
     */
    public String mark(int position) {
        String str = "";
        // check for error
        if (position + 1 > taskList.size()) {
            str = str + Ui.INDENT_SEPERATOR + Ui.INDENT + "Task does not exist";
        } else if (position < 0) {
            str = str + Ui.INDENT_SEPERATOR + Ui.INDENT + "Task does not exist";

        } else {
            str = str + Ui.INDENT_SEPERATOR;
            Task t = taskList.get(position);
            if (t.getStatusIcon().equals(" ")) {
                str = str + Ui.INDENT + "Nice! I've marked this task as done:";
            }
            t.markAsDone();
            str = str + Ui.INDENT + "  " + t.toString();
        }
        str = str + Ui.INDENT_SEPERATOR;
        return str;
    }

    /**
     * To unmark task in the array list.
     * Show 'Task does not exist' if the position is out of range.
     *
     * @param position The index of the specified task in the array list
     *                 to be marked as incompleted.
     */
    public String unmark(int position) {
        String str = "";
        // check for error
        if (position + 1 > taskList.size()) {
            str = str + Ui.INDENT_SEPERATOR + Ui.INDENT + "Task does not exist";
        } else if (position < 0) {
            str = str + Ui.INDENT_SEPERATOR + Ui.INDENT + "Task does not exist";

        } else {
            str = str + Ui.INDENT_SEPERATOR;
            Task t = taskList.get(position);
            if (t.getStatusIcon().equals("X")) {
                str = str + Ui.INDENT + "OK, I've marked this task as not done yet:";
            }
            t.markAsUndone();
            str = str + Ui.INDENT + "  " + t.toString();
        }
        str = str + Ui.INDENT_SEPERATOR;
        return str;
    }

    /**
     * To add a new todo into taskList.
     *
     * @param t New todo to be added into taskList.
     */
    public String todo(Todo t) {
        taskList.add(t);
        String str = Ui.INDENT_SEPERATOR
                + Ui.INDENT + "Got it. I've added this task:"
                + Ui.INDENT + "  " + t.toString()
                + Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list."
                + Ui.INDENT_SEPERATOR;
        return str;
    }

    /**
     * To add a new deadline into taskList.
     *
     * @param t New deadline to be added into taskList.
     */
    public String deadline(Deadline t) {
        String str = Ui.INDENT_SEPERATOR;
        taskList.add(t);
        str = str + Ui.INDENT + "Got it. I've added this task:"
                + Ui.INDENT + "  " + t.toString()
                + Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list."
                + Ui.INDENT_SEPERATOR;
        return str;
    }

    /**
     * To add a new event into taskList.
     *
     * @param t New event to be added into taskList.
     */
    public String event(Event t) {
        String str = Ui.INDENT_SEPERATOR;
        taskList.add(t);
        str = str + Ui.INDENT + "Got it. I've added this task:"
                + Ui.INDENT + "  " + t.toString()
                + Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list."
                + Ui.INDENT_SEPERATOR;
        return str;
    }

    /**
     * To delete a specific task in the array list.
     * Show 'Task does not exist' if the position is out of range.
     *
     * @param position The index of the specified task in the array list
     *                 to be deleted.
     */
    public String delete(int position) {
        String str = "";
        Task t = taskList.get(position);
        // check for error
        if (position + 1 > taskList.size()) {
            str = str + Ui.INDENT + "Task.Task does not exist";
        } else if (position < 0) {
            str = str + Ui.INDENT + "Task.Task does not exist";
        } else {
            taskList.remove(t);
            str = str + Ui.INDENT_SEPERATOR
                    + Ui.INDENT + "Noted. I've removed this task:"
                    + Ui.INDENT + "  " + t.toString()
                    + Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.";
        }
        str = str + Ui.INDENT_SEPERATOR;
        return str;
    }

    /**
     * To print the tasks that contains the keyword.
     */
    public String find(String keyword) {
        String str = Ui.INDENT_SEPERATOR;

        if (taskList.isEmpty()) {
            str = str + "There are currently no tasks in your list";
        } else {
            //System.out.println("keyword:" + keyword + "end");
            str = str + Ui.INDENT + "Here are the matching tasks in your list:";
            int index = 1;
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                String description = t.getDescription();
                if (description.contains(keyword)) {
                    str = str + Ui.INDENT + index + "." + t.toString();
                    index += 1;
                }
            }
        }
        str = str + Ui.INDENT_SEPERATOR;
        return str;
    }

    /**
     * A getter function to get the current taskList.
     *
     * @return The current taskList.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
