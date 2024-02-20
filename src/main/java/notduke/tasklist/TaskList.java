package notduke.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;

import notduke.notdukeexception.NotDukeException;
import notduke.notdukeexception.NotDukeTaskNotFound;
import notduke.task.Task;

/**
 * A List that is used to contain the Tasks inputted by the user.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     * @param saveFile An ArrayList containing the Tasks from previous usage
     */
    public TaskList(ArrayList<Task> saveFile) {
        this.list = saveFile;
    }

    /**
     * Returns a string to be printed which indicates that the adding of the new Task to the TaskList was successful.
     * Adds the new task into the list as well.
     * @param newTask The task to be added into the TaskList
     * @return The string to be printed that indicates add was successful
     */
    public String add(Task newTask) {
        list.add(newTask);
        return "Got it. I've added this task:\n" + newTask.taskInfo() + listStatus();
    }

    /**
     * Returns a string to be printed which contains all the String representation of the tasks in the TaskList.
     * @return a string representation of all the task in the TaskList.
     */
    public String list() {
        assert (list.size() >= 0);
        if (list.size() == 0) {
            return "The list is empty\n";
        }

        int index = 1;
        String output = "Here are the tasks in your list\n";
        for (Task item : list) {
            output = output + index + ".";
            index++;
            output += item.taskInfo();
        }
        return output;
    }

    /**
     * Returns a string to be printed which informs the user how many elements are in the TaskList.
     * @return a string with the size of the TaskList
     */
    public String listStatus() {
        int length = list.size();
        assert (list.size() >= 0);
        return "Now you have " + length + " task" + (length > 1 ? "s" : "") + " in the list.\n";
    }

    /**
     * Returns a string to be printed which indicates that the deleting of task at index in TaskList was successful.
     * Removes the task at index given from the list as well.
     * @param index the index of the task to be deleted
     * @return a string that indicates delete was successful
     * @throws NotDukeException if index > number of tasks
     */
    public String delete(int index) throws NotDukeException {
        try {
            Task removed = list.remove(index - 1);
            removed.taskInfo();
            return "Noted. I've removed this duke.task:\n" + listStatus();
        } catch (IndexOutOfBoundsException e3) {
            throw new NotDukeTaskNotFound(index);
        }
    }

    /**
     * Returns a string to be printed which indicates that the marking of task at index in TaskList was successful.
     * Marks the task at index given from the list as well.
     * @param index the index of the task to be marked
     * @return a string that indicates mark was successful
     * @throws NotDukeException if index > number of tasks or task was already marked
     */
    public String mark(int index) throws NotDukeException {
        try {
            Task task = list.get(index - 1);
            return task.mark();
        } catch (IndexOutOfBoundsException e3) {
            throw new NotDukeTaskNotFound(index);
        }
    }

    /**
     * Returns a string to be printed which indicates that the unmarking of task at index in TaskList was successful.
     * Unmarks the task at index given from the list as well.
     * @param index the index of the task to be unmarked
     * @return a string that indicates unmark was successful
     * @throws NotDukeException if index > number of tasks or task was already unmarked
     */
    public String unmark(int index) throws NotDukeException {
        try {
            Task task = list.get(index - 1);
            return task.unmark();
        } catch (IndexOutOfBoundsException e3) {
            throw new NotDukeTaskNotFound(index);
        }
    }


    /**
     * Returns a string to be printed which represents the list of tasks which are occurring on the given date.
     * @param date The date to check which tasks are occurring
     * @return a string that represents the tasks occurring on the date
     */
    public String check(LocalDate date) {
        String output = String.format("Tasks on %s:\n", date);
        for (Task item : list) {
            output += item.happenOn(date);
        }
        return output;

    }

    /**
     * Returns a String representation of the TaskList to be used for saving the task. The String will contain
     * information such as the type, name, the status, dates of all the tasks.
     * @return a string that represents the whole TaskList
     */
    public String saveFormat() {
        String output = "";
        for (Task t: list) {
            output += t.saveOutput();
            output += "\n";
        }
        return output;
    }

    public String find(String match) {
        String output = "Here are the matching tasks in your list:\n";
        for (Task item : list) {
            output += item.printMatch(match);
        }
        return output;

    }

    public String remind() {
        String output = "Here are the tasks happening within the next 7 days:\n";
        for (Task item : list) {
            output += item.happenSoon();
        }
        return output;
    }
}
