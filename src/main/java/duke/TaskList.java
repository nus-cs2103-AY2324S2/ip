package duke;

import java.util.ArrayList;

/**
 * TaskList Class is responsible for housing the taskList array and methods for adding and removing tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<Task> findList = new ArrayList<>();

    /**
     * Empty constructor for TaskList.
     */
    public TaskList() {
    }

    /**
     * Marks a mask as complete.
     * @param index
     */
    public void markTask(int index) {
        Task currentTask = taskList.get(index);
        System.out.println("\tWe have completed this task!");
        currentTask.mark();
        System.out.println("\t" + currentTask.getTaskType() + " " + currentTask.getStatus()
                + " " + currentTask.getTask());
    }

    /**
     * Deletes a task from the TaskList.
     * @param index
     */
    public void deleteTask(int index) {
        Task currentTask = taskList.get(index);
        System.out.println("\tTask has been deleted!");
        System.out.println("\t" + currentTask.getTaskType() + " " + currentTask.getStatus()
                + " " + currentTask.getTask());

        taskList.remove(index);
    }

    /**
     * Unmarks a task as completed.
     * @param index
     */
    public void unmarkTask(int index) {
        Task currentTask = taskList.get(index);
        System.out.println("\tOops, task unmarked!");
        currentTask.unmark();
        System.out.println("\t" + currentTask.getTaskType() + " " + currentTask.getStatus()
                + " " + currentTask.getTask());
    }

    /**
     * Lists all the tasks currently created.
     */
    public void listTask() {
        System.out.println("\tThese are the tasks we currently have: ");

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println("\t" + (i + 1) + ". " + currentTask.toString());
        }

        System.out.println("\tWe have " + (taskList.size()) + " tasks.");
    }

    /**
     * Adds new task to the TaskListt.
     * @param first
     * @param second
     */

    public void addTask(String first, String second) {
        Task newTask;

        if (first.equals("todo")) {
            newTask = new ToDo(second, "T");
            this.taskList.add(newTask);
            System.out.println("\t" + newTask.announcement());
            System.out.println("\t\t" + newTask);
        } else if (first.equals("deadline")) {
            String[] secondaryInputSplit = second.split(" /");
            newTask = new Deadline(secondaryInputSplit[0], "D", secondaryInputSplit[1]);
            this.taskList.add(newTask);
            System.out.println("\t" + newTask.announcement());
            System.out.println("\t\t" + newTask);
        } else if (first.equals("event")) {
            String[] secondaryInputSplit = second.split(" /");
            newTask = new Event(secondaryInputSplit[0], "E", secondaryInputSplit[1],
                    secondaryInputSplit[2]);
            this.taskList.add(newTask);
            System.out.println("\t" + newTask.announcement());
            System.out.println("\t\t" + newTask);
        } else {
            System.out.println("\tInvalid Task");
        }
    }

    /**
     * Method returns all task that has the matching word as desc.
     * @param desc
     */
    public void findTask(String desc) {
        findList.clear();
        for (Task task : taskList) {
            if (task.toString().contains(desc)) {
                findList.add(task);
            }
        }

        for (int i = 0; i < findList.size(); i++) {
            Task currentTask = findList.get(i);
            System.out.println("\t" + (i + 1) + ". " + currentTask.toString());
        }

        System.out.println("\tWe have " + (findList.size()) + " matching tasks with the word "
                + desc + ".");
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return number of tasks.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the ArrayList of tasks
     * @return Arraylist of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Gets a particular task at index.
     * @param index
     * @return Task at index value.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
