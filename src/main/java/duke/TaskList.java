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
        String str = "";
        if (taskList.isEmpty()) {
            return "There are currently no tasks in your list";
        }
        str = str + "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String temp = (i + 1) + "." + t.toString();
            str = str + temp + "\n";
        }
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
        if (position + 1 > taskList.size() || position < 0) {
            return "Task does not exist";
        }
        Task t = taskList.get(position);
        if (t.getStatusIcon().equals(" ")) {
            str = str + "Nice! I've marked this task as done:\n";
        } else {
            str = str + "This task is already done\n";
        }
        t.markAsDone();
        str = str + "  " + t.toString();
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
        if (position + 1 > taskList.size() || position < 0) {
            return "Task does not exist";
        }
        Task t = taskList.get(position);
        if (t.getStatusIcon().equals("X")) {
            str = str + "OK, I've marked this task as not done yet:\n";
        } else {
            str = str + "This task is not done yet\n";
        }
        t.markAsUndone();
        str = str + "  " + t.toString();
        return str;
    }

    /**
     * To add a new todo into taskList.
     *
     * @param t New todo to be added into taskList.
     */
    public String todo(Todo t) {
        taskList.add(t);
        return "Got it. I've added this task:\n"
                + t.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * To add a new deadline into taskList.
     *
     * @param t New deadline to be added into taskList.
     */
    public String deadline(Deadline t) {
        taskList.add(t);
        return "Got it. I've added this task:\n"
                + t.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * To add a new event into taskList.
     *
     * @param t New event to be added into taskList.
     */
    public String event(Event t) {
        taskList.add(t);
        return "Got it. I've added this task:\n"
                + t.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * To delete a specific task in the array list.
     * Show 'Task does not exist' if the position is out of range.
     *
     * @param position The index of the specified task in the array list
     *                 to be deleted.
     */
    public String delete(int position) {
        // check for error
        if (position + 1 > taskList.size() || position < 0) {
            return "Task does not exist";
        }
        Task t = taskList.get(position);
        taskList.remove(t);
        return "Noted. I've removed this task:\n"
                + t.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * To print the tasks that contains the keyword.
     */
    public String find(String keyword) {
        String str = "";

        if (taskList.isEmpty()) {
            str = str + "There are currently no tasks in your list";
        }
        str = str + "Here are the matching tasks in your list:\n";
        int index = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String description = t.getDescription();
            if (description.contains(keyword)) {
                str = str + index + "." + t.toString() + "\n";
                index += 1;
            }
        }

        return str;
    }

    /**
     * To update a specific task
     */
    public String update(String newDescription, int position) {
        // check for error
        if (position + 1 > taskList.size() || position < 0) {
            return "Task does not exist";
        }
        Task t = taskList.get(position);
        t.updateDescription(newDescription);
        return "I have updated the description\n"
                + t.toString();
    }

    /**
     * A getter function to get the current taskList.
     *
     * @return The current taskList.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task get(int position) {
        return taskList.get(position);
    }
}
