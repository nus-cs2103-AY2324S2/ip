package eueu;

import eueu.task.Deadline;
import eueu.task.Event;
import eueu.task.Task;
import eueu.task.Todo;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.ArrayList;

/**
 * Represents the tasklist and executes the tasks given
 * (i.e. list, write, addTask, mark, unmark, delete, find).
 * Contains an ArrayList<Task> tasklist and Storage s.
 */

public class TaskList {
    private ArrayList<Task> tasklist = new ArrayList<>(); //tasklist needs to copy everything from file at the start
    private Storage s;
    static final String FILE_NOT_FOUND = "file not found! try again xx";
    static final String INVALID_TASK_RESPONSE = "ENTER INSTRUCTION";
    static final String INVALID_TASK_MARK = "No such task to mark.";
    static final String INVALID_TASK_UNMARK = "No such task to unmark.";
    static final String INVALID_TASK_DELETE = "No such task to delete.";
    static final String TASK_NOT_FOUND = "sowwie babez no matching tasks!";
    static final String CLEAR_LIST = "YAY BB! your list is cleared :)";
    static final String TASKLIST_FILE = "data/EUEU.txt";




    public TaskList(Storage s) {
        this.s = s;
    }

    /**
     * Writes current task to the file which is stored in Storage s.
     *
     * @throws IOException When File f cannot be found.
     */
    public void write(Task task) throws IOException {
        assert tasklist != null : "TaskList should not be null";
        task.writeToFile(s.getFile());
    }

    public void write() throws IOException {
        assert tasklist != null : "TaskList should not be null";
        clearList();
        for (int i = 0; i < tasklist.size(); i++) {
            tasklist.get(i).writeToFile(s.getFile());
        }
        clearTasklist();
        s.getSavedTasks(tasklist);
    }

    /**
     * Lists all tasks from previous iterations of Eueu.run() which are stored in
     * Storage s as well as current tasks in current iterations of Eueu.run().
     *
     * @return String representation of the list
     */

    public String list() {
        assert s != null : "Storage s should not be null";

        String str = "";
        try {
            tasklist.clear();
            s.getSavedTasks(tasklist);
            str = "Here are your tasks: \n";
            for (int i = 0; i < tasklist.size(); i++) {
                int j = i + 1;
                str += "    " + j + ". " + tasklist.get(i).add() + "\n";
            }
            return str;
        } catch (FileNotFoundException e) {
            str = FILE_NOT_FOUND;
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
    public String addTask(Todo task) throws StringIndexOutOfBoundsException, IOException {
        assert tasklist != null : "TaskList should not be null";

        String str = "";
        try {
            tasklist.add(task);
            str = "Got it. I've added this task: \n" + task.add() + "\n" +
                    "Now you have " + tasklist.size() + " tasks in the list.";

        } catch (StringIndexOutOfBoundsException e) {
            str = INVALID_TASK_RESPONSE;
        }

        write(task);
        return str;
    }

    /**
     * Adds Deadline task to tasklist.
     *
     * @param task Deadline task to be added to tasklist.
     * @return String representation of adding deadline task to tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task and leaves blank (e.g. deadline).
     */
    public String addTask(Deadline task) throws StringIndexOutOfBoundsException, IOException {
        assert tasklist != null : "TaskList should not be null";

        String str = "";
        try {
            tasklist.add(task);
            str = "Got it. I've added this task: \n" + task.add() +  "\n" +
                    "Now you have " + tasklist.size() + " tasks in the list.";

        } catch (StringIndexOutOfBoundsException e) {
            str = INVALID_TASK_RESPONSE;
        }

        write(task);
        return str;
    }

    /**
     * Adds Event task to tasklist.
     *
     * @param task Event task to be added to tasklist.
     * @return String representation of adding event task to tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task and leaves blank (e.g. event).
     */

    public String addTask(Event task) throws StringIndexOutOfBoundsException, IOException {
        assert tasklist != null : "TaskList should not be null";

        String str = "";
        try {
            tasklist.add(task);
            str = "Got it. I've added this task: \n" + task.add() + "\n" +
                    "Now you have " + tasklist.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            str = INVALID_TASK_RESPONSE;
        }

        write(task);
        return str;
    }

    /**
     * Marks task in tasklist as done.
     *
     * @param number Task number in current list to be marked.
     * @return String representation of marking task in tasklist.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public String mark(int number) throws IndexOutOfBoundsException, IOException {
        assert tasklist != null : "TaskList should not be null";

        String str = "";
        try {
            Task task = tasklist.get(number);
            task.setDone();
            str = "Nice! I've marked this task as done: \n" +
                    task.add();
        } catch (IndexOutOfBoundsException e) {
            str = INVALID_TASK_MARK;
        }
        this.clearList();
        this.write();
        clearTasklist();
        s.getSavedTasks(tasklist);
        return str;
    }

    /**
     * Unmarks task in tasklist.
     *
     * @param number Task number in current list to be unmarked.
     * @return String representation of unmarking task in tasklist.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public String unmark(int number) throws IndexOutOfBoundsException, IOException {
        assert tasklist != null : "TaskList should not be null";

        String str = "";
        try {
            Task task = tasklist.get(number);
            task.setNotDone();
            str = "Ok, I've marked this task as not done yet: \n" +
                    task.add();
        } catch (IndexOutOfBoundsException e) {
            str = INVALID_TASK_UNMARK;
        }
        this.write();
        return str;
    }

    /**
     * Deletes task from tasklist.
     *
     * @param number Task number in current list to be deleted.
     * @return String representation of deleting task from tasklist.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public String delete(int number) throws IndexOutOfBoundsException, IOException {
        assert tasklist != null : "TaskList should not be null";

        String str = "";
        try {
            Task task = tasklist.get(number);
            tasklist.remove(number);
            str = "Noted. I've removed this task: \n" + task.add() + "\n" +
            "Now you have " + tasklist.size() + " tasks in the current list.";
        } catch (IndexOutOfBoundsException e) {
            str = INVALID_TASK_DELETE;
        }
        this.write();
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
        assert tasklist != null : "TaskList should not be null";

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

    /**
     * Clears the contents of the task list file.
     *
     * @return A confirmation message indicating that the list has been cleared.
     * @throws IOException if an I/O error occurs while attempting to clear the file.
     */
    public String clearList() throws IOException {
        FileWriter fw = new FileWriter(TASKLIST_FILE, false);
        fw.close();
        return CLEAR_LIST;

    }

    /**
     * Clears the tasklist.
     *
     * @throws IOException if an I/O error occurs while attempting to clear the task list.
     */
    public void clearTasklist() throws IOException {
        tasklist.clear();
    }
}

