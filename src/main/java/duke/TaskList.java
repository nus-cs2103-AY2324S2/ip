package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the tasklist and executes the tasks given
 * (i.e. list, write, addTask, mark, unmark, delete).
 * Contains an ArrayList<Task> tasklist and Storage s.
 */

public class TaskList {
    private ArrayList<Task> tasklist = new ArrayList<Task>();
    private Storage s;

    static final String FILE_NOT_FOUND = "file not found! try again xx";
    static final String INVALID_TASK_RESPONSE = "ENTER INSTRUCTION";
    static final String INVALID_TASK_MARK = "No such task to mark.";
    static final String INVALID_TASK_UNMARK = "No such task to unmark.";
    static final String INVALID_TASK_DELETE = "No such task to delete.";
    static final String TASK_NOT_FOUND = "sowwie babez no matching tasks!";


    public TaskList(Storage s) {
        this.s = s;
    }

    /**
     * Writes current task to the file which is stored in Storage s.
     *
     * @throws IOException When File f cannot be found.
     */
    public void write() throws IOException {
        for (int i = 0; i < tasklist.size(); i++) {
            tasklist.get(i).writeToFile(s.getFile());
        }
    }

    public void clearCurrentTasks() {
        tasklist.clear();
    }

    /**
     * Lists all tasks from previous iterations of Duke.run() which are stored in
     * Storage s as well as current tasks in current iterations of Duke.run().
     *
     * @return String representation of the list
     */
    public String list() {
        String str = "";
        try {
            str = "All tasks: \n" +  s.getFileContent() + "\n" + "Current tasks: \n" + getCurrentList();
        } catch (FileNotFoundException e) {
            str = FILE_NOT_FOUND;
        }
        return str;
    }

    /**
     * Lists the current list in current iteration of Duke.run().
     *
     * @return String representation of current tasklist
     */
    public String getCurrentList() {
        String str = "";
        for (int i = 0; i < tasklist.size(); i++) {
            int j = i + 1;
            str += "    " + j + ". " + tasklist.get(i).getCat()
                    + tasklist.get(i).marked() + " "
                    + tasklist.get(i).getTask() + tasklist.get(i).getDetails() + "\n";
        }
        return str;
    }

    /**
     * Adds Todo task to tasklist.
     *
     * @param task Todo task to be added to tasklist.
     * @return String representation of adding todo task to tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task and leaves blank (e.g. todo).
     */
    public String addTask(Todo task) throws StringIndexOutOfBoundsException {
        String str = "";
        try {
            tasklist.add(task);
            str = "Got it. I've added this task: \n" + task.add() + "\n" +
                    "Now you have " + tasklist.size() + " tasks in the list.";

        } catch (StringIndexOutOfBoundsException e) {
            str = INVALID_TASK_RESPONSE;
        }
        return str;
    }

    /**
     * Adds Deadline task to tasklist.
     *
     * @param task Deadline task to be added to tasklist.
     * @return String representation of adding deadline task to tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task and leaves blank (e.g. deadline).
     */
    public String addTask(Deadline task) throws StringIndexOutOfBoundsException {
        String str = "";
        try {
            tasklist.add(task);
            str = "Got it. I've added this task: \n" + task.add() +  "\n" +
                    "Now you have " + tasklist.size() + " tasks in the list.";

        } catch (StringIndexOutOfBoundsException e) {
            str = INVALID_TASK_RESPONSE;
        }
        return str;
    }

    /**
     * Adds Event task to tasklist.
     *
     * @param task Event task to be added to tasklist.
     * @return String representation of adding event task to tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task and leaves blank (e.g. event).
     */

    public String addTask(Event task) throws StringIndexOutOfBoundsException {
        String str = "";
        try {
            tasklist.add(task);
            str = "Got it. I've added this task: \n" + task.add() + "\n" +
                    "Now you have " + tasklist.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            str = INVALID_TASK_RESPONSE;
        }
        return str;
    }

    /**
     * Marks task in tasklist as done.
     *
     * @param number Task number in current list to be marked.
     * @return String representation of marking task in tasklist.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public String mark(int number) throws IndexOutOfBoundsException {
        String str = "";
        try {
            Task task = tasklist.get(number);
            task.setDone();
            str = "Nice! I've marked this task as done: \n" +
                    task.mark(number);
        } catch (IndexOutOfBoundsException e) {
            str = INVALID_TASK_MARK;
        }
        return str;
    }

    /**
     * Unmarks task in tasklist.
     *
     * @param number Task number in current list to be unmarked.
     * @return String representation of unmarking task in tasklist.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public String unmark(int number) throws IndexOutOfBoundsException {
        String str = "";
        try {
            Task task = tasklist.get(number);
            task.setNotDone();
            str = "Ok, I've marked this task as not done yet: \n" +
                    task.unmark(number);
        } catch (IndexOutOfBoundsException e) {
            str = INVALID_TASK_UNMARK;
        }
        return str;
    }

    /**
     * Deletes task from tasklist.
     *
     * @param number Task number in current list to be deleted.
     * @return String representation of deleting task from tasklist.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public String delete(int number) throws IndexOutOfBoundsException {
        String str = "";
        try {
            Task task = tasklist.get(number);
            tasklist.remove(number);
            str = "Noted. I've removed this task: \n" + task.delete() + "\n" +
            "Now you have " + tasklist.size() + " tasks in the current list.";
        } catch (IndexOutOfBoundsException e) {
            str = INVALID_TASK_DELETE;
        }
        return str;
    }

    /**
     * Finds a task from tasklist.
     *
     * @param str User input string to find.
     * @return String representation of finding task in tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task what to find (e.g. find).
     */

    public String find(String str) throws StringIndexOutOfBoundsException {
        String ret = "";
        try {
            int j = 1;
            for (int i = 0; i < tasklist.size(); i++) {
                if (tasklist.get(i).isFound(str)) {
                    if (j == 1) {
                        ret = "Here are the matching tasks in your list: \n";
                    }
                    ret += "    " + j + ". " + tasklist.get(i).getCat()
                                        + tasklist.get(i).marked() + " "
                                        + tasklist.get(i).getTask()
                                        + tasklist.get(i).getDetails() + "\n";
                    j++;
                }
            }
            if (j == 1) {
                ret = TASK_NOT_FOUND;
            }
        } catch (StringIndexOutOfBoundsException e) {
            ret = INVALID_TASK_RESPONSE;
        }
        return ret;
    }
}

