package kervyn.Tasks;

import java.util.ArrayList;


/**
 * Represents a list of tasks and provides operations for managing tasks.
 */
public class TaskList {
    // Contains task-related operations
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }


    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param taskList The ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param userTasks The ArrayList of Task objects to be listed.
     * @return Returns 1 if the list operation was successful, 0 otherwise.
     */
    public short listTasks(ArrayList<Task> userTasks) {
        System.out.println("\tHere are the tasks on your list:");
        for (int i = 0; i < userTasks.size(); i++) {
            Task task = userTasks.get(i);
            char check = task.getStatus() ? 'X' : ' ';
            char type = task.getCapitalType();
            switch (type) {
                case 'T':
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " +  "[" + check + "] " + task.getDescription());
                    break;
                case 'D':
                    Deadline deadlineTask = (Deadline) task;
                    if (deadlineTask == null) {
                        return 0;
                    }
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " + "[" + check + "] " + deadlineTask.getDescription() + " (by: " + deadlineTask.getDeadline() + ")");
                    break;
                case 'E':
                    Event eventTask = (Event) task;
                    if (eventTask == null) {
                        return 0;
                    }
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " + "[" + check + "] "  + eventTask.getDescription() + " (from: " + eventTask.getStartDate() + " to: " + eventTask.getEndDate() + ")");
                    break;
            }
        }
        return 1;
    }

    /**
     * Marks a task as completed in the task list.
     *
     * @param userTasks The ArrayList of Task objects.
     * @param processedUserInput The user input processed into an array of Strings.
     * @return Returns 1 if the mark operation was successful, 0 otherwise.
     */
    public short markTask(ArrayList<Task> userTasks, String[] processedUserInput) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            if (task.getStatus()) {
                taskAlreadyMarked();
            } else {
                System.out.println("\tNice! I've marked this task as done:");
                task.updateStatus();
                char check = task.getStatus() ? 'X' : ' ';
                char type = task.getCapitalType();
                System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            }

            return 1;
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to mark a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
        return 0;
    }

    /**
     * Unmarks a task as not completed in the task list.
     *
     * @param userTasks The ArrayList of Task objects.
     * @param processedUserInput The user input processed into an array of Strings.
     * @return Returns 1 if the unmark operation was successful, 0 otherwise.
     */
    public short unMarkTask(ArrayList<Task> userTasks, String[] processedUserInput) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            if (!task.getStatus()) {
                taskAlreadyUnMarked();
            } else {
                System.out.println("\tOK, I've marked this task as not done yet:");
                task.updateStatus();
                char check = task.getStatus() ? 'X' : ' ';
                char type = task.getCapitalType();
                System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            }

            return 1;
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to unmark a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
        return 0;
    }

    /**
     * Private method to handle the scenario when a task is already marked.
     */
    private static void taskAlreadyMarked() {
        System.out.println("\tUh oh! It looks like this task is already marked as done, please try again with another task!");
    }

    /**
     * Private method to handle the scenario when a task is already unmarked.
     */
    private static void taskAlreadyUnMarked() {
        System.out.println("\tUh oh! It looks like this task is already marked as not done, please try again with another task!");
    }

    /**
     * Removes a task from the task list.
     *
     * @param userTasks The ArrayList of Task objects.
     * @param processedUserInput The user input processed into an array of Strings.
     */
    public void removeTask(ArrayList<Task> userTasks, String[] processedUserInput) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            System.out.println("\tOK, I've removed this task as per your request:");
            char check = task.getStatus() ? 'X' : ' ';
            char type = task.getCapitalType();
            System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            userTasks.remove(task);
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to delete a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
    }
}
