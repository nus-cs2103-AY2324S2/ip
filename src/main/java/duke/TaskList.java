package duke;

import java.util.ArrayList;

import duke.exceptions.CorruptedLogException;
import duke.tasks.Task;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    private Storage manager;

    public TaskList() {
        this.manager = null;
    }

    public TaskList(Storage manager) {
        this.manager = manager;
        // Try to load on initialisation
        loadTaskList();
    }

    public void loadTaskList() {
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

    public void saveTaskList() {
        this.manager.writeLog(this.tasks);
    }

    public void deleteTask(int i) {
        Task deleted = this.tasks.remove(i);
        String printOut = "_________________________\n"
            + "Finished with this? Good Job!" + "\n"
            + deleted.getFullStatus() + "\n"
            + "_________________________\n"
            + "Now you have " + this.tasks.size() + " items in your list!\n";
        System.out.println(printOut);
        this.saveTaskList();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        String toPrint = "_________________________\n"
            + "added: " + t.getFullStatus() + "\n"
            + "_________________________\n" + "Now you have " + this.tasks.size()
            + " items in your list!\n";
        System.out.println(toPrint);
        this.saveTaskList();
    }

    public int getLength() {
        return this.tasks.size();
    }

    public void markTask(int idx) {
        Task focusTask = this.tasks.get(idx);
        focusTask.mark();
        String printOut = "_________________________\n"
            + "Marking this done!" + "\n"
            + focusTask.getFullStatus() + "\n"
            + "_________________________\n";
        System.out.println(printOut);
        this.saveTaskList();
    }

    public void unmarkTask(int idx) {
        Task focusTask = this.tasks.get(idx);
        focusTask.unmark();
        String printOut = "_________________________\n"
            + "Marking this done!" + "\n"
            + focusTask.getFullStatus() + "\n"
            + "_________________________\n";
        System.out.println(printOut);
        this.saveTaskList();
    }

    public void showTaskList() {
        System.out.println("_________________________\n"
            + "Get off your ass and starting doing work!" + "\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            System.out.println((i + 1)
                + "." + curr.getFullStatus());
        }
        System.out.println("_________________________\n");
    }

}
