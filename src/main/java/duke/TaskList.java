package duke;
import task.*;
import java.util.ArrayList;

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
    public void bye(Ui ui) {
        Ui.showGoodbyeMessage();
    }

    /**
     * To print the entire Task array.
     */
    public void showList() {
        System.out.println(Ui.INDENT_SEPERATOR);
        if (taskList.isEmpty()) {
            System.out.println("There are currently no tasks in your list");
        } else {
            System.out.println(Ui.INDENT + "Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                System.out.println(Ui.INDENT + (i + 1) + "." + t.toString());
            }
        }
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    /**
     * To mark task in the array list.
     * Show 'Task does not exist' if the position is out of range.
     *
     * @param position The index of the specified task in the array list
     *                 to be marked as completed.
     */
    public void mark(int position) {
        // check for error
        if (position + 1 > taskList.size()) {
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Task.Task does not exist");
        } else if (position < 0) {
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Task.Task does not exist");

        } else {
            System.out.println(Ui.INDENT_SEPERATOR);
            Task t = taskList.get(position);
            if (t.getStatusIcon().equals(" ")) {
                System.out.println(Ui.INDENT + "Nice! I've marked this task as done:");
            }
            t.markAsDone();
            System.out.println(Ui.INDENT + "  " + t.toString());
        }
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    /**
     * To unmark task in the array list.
     * Show 'Task does not exist' if the position is out of range.
     *
     * @param position The index of the specified task in the array list
     *                 to be marked as incompleted.
     */
    public void unmark(int position) {
        // check for error
        if (position + 1 > taskList.size()) {
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Task.Task does not exist");
        } else if (position < 0) {
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Task.Task does not exist");

        } else {
            System.out.println(Ui.INDENT_SEPERATOR);
            Task t = taskList.get(position);
            if (t.getStatusIcon().equals("X")) {
                System.out.println(Ui.INDENT + "OK, I've marked this task as not done yet:");
            }
            t.markAsUndone();
            System.out.println(Ui.INDENT + "  " + t.toString());
        }
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    /**
     * To add a new todo into taskList.
     *
     * @param t New todo to be added into taskList.
     */
    public void todo(Todo t)  {
        taskList.add(t);
        System.out.println(Ui.INDENT_SEPERATOR);
        System.out.println(Ui.INDENT + "Got it. I've added this task:");
        System.out.println(Ui.INDENT + "  " + t.toString());
        System.out.println(Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    /**
     * To add a new deadline into taskList.
     *
     * @param t New deadline to be added into taskList.
     */
    public void deadline(Deadline t) {
        System.out.println(Ui.INDENT_SEPERATOR);
        taskList.add(t);
        System.out.println(Ui.INDENT + "Got it. I've added this task:");
        System.out.println(Ui.INDENT + "  " + t.toString());
        System.out.println(Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    /**
     * To add a new event into taskList.
     *
     * @param t New event to be added into taskList.
     */
    public void event(Event t) {
        System.out.println(Ui.INDENT_SEPERATOR);
        taskList.add(t);
        System.out.println(Ui.INDENT + "Got it. I've added this task:");
        System.out.println(Ui.INDENT + "  " + t.toString());
        System.out.println(Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    /**
     * To delete a specific task in the array list.
     * Show 'Task does not exist' if the position is out of range.
     *
     * @param position The index of the specified task in the array list
     *                 to be deleted.
     */
    public void delete(int position) {
        Task t = taskList.get(position);
        // check for error
        if (position + 1 > taskList.size()) {
            System.out.println(Ui.INDENT + "Task.Task does not exist");
        } else if (position < 0) {
            System.out.println(Ui.INDENT + "Task.Task does not exist");
        } else {
            taskList.remove(t);
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Noted. I've removed this task:");
            System.out.println(Ui.INDENT + "  " + t.toString());
            System.out.println(Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        }
        System.out.println(Ui.INDENT_SEPERATOR);
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
