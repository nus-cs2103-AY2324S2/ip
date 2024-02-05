package duchess.util;

import duchess.tasks.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Create new empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Create new Task List object with previous tasks already loaded.
     *
     * @param taskList ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Print list of tasks.
     */
    public void printTaskList() {
        System.out.println("\t\tHere are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i - 1);
            System.out.println("\t\t" + i + "." + t);
        }
    }

    /**
     * Retrieve ArrayList of Task objects.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> retrieveTaskList() {
        return this.taskList;
    }

    /**
     * Delete Task object in ArrayList of Task objects.
     *
     * @param id id of task in list to be deleted.
     */
    public void deleteTask(int id) throws IndexOutOfBoundsException{
        String lineBreak = "\t\t------------------------------------------";
        Task t = taskList.remove(id - 1);
        System.out.println(lineBreak);
        System.out.println("\t\tNoted. I've removed this task:");
        System.out.println("\t\t  " + t);
        System.out.println("\t\tNow you have " + taskList.size() + " tasks in the list.");
        System.out.println(lineBreak);
    }

    /**
     * Create Task object in ArrayList of Task objects.
     *
     * @param task new Task object.
     */
    public void createTask(Task task) {
        taskList.add(task);
        System.out.println("\t\tGot it. I've added this task: \n\t\t  " + task);
        System.out.println("\t\tNow you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t\t------------------------------------------");
    }

    /**
     * Unmark Task object in ArrayList of Task objects.
     *
     * @param id id of task in list to be unmarked.
     */
    public void unmarkTask(int id) {
        Task t = taskList.get(id - 1);
        t.markAsUndone();
        System.out.println("\t\t------------------------------------------");
        System.out.println("\t\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t  " + t);
        System.out.println("\t\t------------------------------------------");
    }

    /**
     * Mark Task object in ArrayList of Task objects.
     *
     * @param id id of task in list to be marked.
     */
    public void markTask(int id) {
        Task t = taskList.get(id - 1);
        t.markAsDone();
        System.out.println("\t\t------------------------------------------");
        System.out.println("\t\tNice! I've marked this task as done:");
        System.out.println("\t\t  " + t);
        System.out.println("\t\t------------------------------------------");
    }

    /**
     * Get current number of Task objects in ArrayList.
     *
     * @return int number of Task objects.
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }
}
