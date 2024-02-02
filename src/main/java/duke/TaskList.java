package duke;

import java.util.ArrayList;

import duke.exceptions.CorruptedLogException;
import duke.tasks.Task;

/**
 * This class represents a list that holds the user's tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    private Storage manager;

    /**
     * Constructor for task list meant for testing.
     */
    public TaskList() {
        this.manager = null;
    }

    /**
     * Constructor for task list.
     * @param manager The storage manager for this task list.
     */
    public TaskList(Storage manager) {
        this.manager = manager;
        // Try to load on initialisation
        loadTaskList();
    }

    private void loadTaskList() {
        try {
            this.manager.loadHistory(this.tasks);
        } catch (CorruptedLogException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function is used to test that adding tasks to internal task list is functional.
     *
     * @param t The task to add.
     * @return The output to be printed by the TaskList.
     */
    public String addTaskTest(Task t) {
        this.tasks.add(t);
        String toPrint = "_________________________\n"
            + "added: " + t.getFullStatus() + "\n"
            + "_________________________\n"
            + "Now you have " + this.tasks.size() + " items in your list!\n";
        return toPrint;
    }
    /**
     * Saves current state of the task list into the log.
     */
    public void saveTaskList() {
        this.manager.writeLog(this.tasks);
    }
    /**
     * Deletes the task at index i.
     *
     * @param i Index to delete the task at.
     * @return The output to print.
     */
    public String deleteTask(int i) {
        Task deleted = this.tasks.remove(i);
        String printOut = "_________________________\n"
            + "Finished with this? Good Job!" + "\n"
            + deleted.getFullStatus() + "\n"
            + "_________________________\n"
            + "Now you have " + this.tasks.size() + " items in your list!\n";
        this.saveTaskList();
        return printOut;
    }

    /**
     * Adds task to the task list.
     *
     * @param t The task to add.
     * @return The output to print.
     */
    public String addTask(Task t) {
        this.tasks.add(t);
        String toPrint = "_________________________\n"
            + "added: " + t.getFullStatus() + "\n"
            + "_________________________\n" + "Now you have " + this.tasks.size()
            + " items in your list!\n";
        this.saveTaskList();
        return toPrint;
    }

    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param idx The index to mark the task done.
     * @return The output to print out.
     */
    public String markTask(int idx) {
        Task focusTask = this.tasks.get(idx);
        focusTask.mark();
        String printOut = "_________________________\n"
            + "Marking this done!" + "\n"
            + focusTask.getFullStatus() + "\n"
            + "_________________________\n";
        this.saveTaskList();
        return printOut;
    }
    /**
     * Marks a task as not completed.
     *
     * @param idx The index to unmark the task done.
     * @return The output to print out.
     */
    public String unmarkTask(int idx) {
        Task focusTask = this.tasks.get(idx);
        focusTask.unmark();
        String printOut = "_________________________\n"
            + "Marking this done!" + "\n"
            + focusTask.getFullStatus() + "\n"
            + "_________________________\n";
        this.saveTaskList();
        return printOut;
    }
    /**
     * Prints current tasks in the list as well as their respective states.
     *
     * @return The output of the task list to be printed.
     */
    public String showTaskList() {
        String ret = "_________________________\n"
            + "Get off your ass and starting doing work!" + "\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            ret += (i + 1)
                + "." + curr.getFullStatus();
        }
        ret += "_________________________\n";
        return ret;
    }

    /**
     * Lists current tasks related to keyword given.
     *
     * @param keyword The keyword we are concerned about.
     * @return The list of searched items to be printed out.
     */
    public String listKeywords(String keyword) {
        String ret = "_________________________\n"
            + "Asking shuheng for tasks related to " + keyword + "...\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            if (!curr.checkKeyword(keyword)) {
                continue;
            }
            ret += (i + 1) + "." + curr.getFullStatus();
        }
        ret += "_________________________\n";
        return ret;
    }
}
