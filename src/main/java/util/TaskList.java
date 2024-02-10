package util;

import exceptions.ChatBotException;
import tasks.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks and provides operations to manipulate the tasks.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private static final String FILE_PATH = "./data/duke/.txt";
    private List<Task> tasks;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task getTaskIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (this.tasks.size() < MAX_TASKS) {
            this.tasks.add(task);
            /*System.out.println("Got it. I've added this task:");
            System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
            System.out.println("Now you have " + this.tasks.size() + " task" +
                    (this.tasks.size() == 1 ? "" : "s") + " in the list"); */
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param number The index of the task to be deleted.
     * @throws ChatBotException If the task list is empty or the specified index is invalid.
     */
    public Task deleteTask(int number) throws ChatBotException {
        if (this.tasks.size() == 0) {
            throw new ChatBotException("Oops! There are no tasks in the list.");
        }
        if (number > this.tasks.size()) {
            throw new ChatBotException("Oops! Number entered does not exist in the list.");
        }
        Task remainingTasks = this.tasks.remove(number - 1);
       /* System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + remainingTasks.toString());
        System.out.println("Now you have " + this.tasks.size() + " task" +
                (this.tasks.size() == 1 ? "" : "s") + " in the list"); */
        return remainingTasks;
    }

    public String getTaskSummary() {
        return "\tNow you have " + this.tasks.size() + " task"
                + (this.tasks.size() == 1 ? "" : "s") + " in the list";
    }


    /**
     * Marks a task in the task list as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws ChatBotException If the specified index is invalid (negative or out of bounds).
     */
    public void markTask(int index) throws ChatBotException {
        if (index < 0) {
            throw new ChatBotException("Oops! Number entered cannot be negative.");
        }
        if (index == 0 || index > this.tasks.size()) {
            throw new ChatBotException("Oops! Number entered does not exist in the list.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        currTask.toString();
       /* System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + currTask.toString()); */
    }


    /**
     * Marks a task in the task list as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws ChatBotException If the specified index is invalid (negative or out of bounds).
     */
    public void unmarkTask(int index) throws ChatBotException {
        if (index < 0) {
            throw new ChatBotException("Oops! Number entered cannot be negative.");
        }
        if (index == 0 || index > this.tasks.size()) {
            throw new ChatBotException("Oops! umber entered does not exist in the list.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsNotDone();
        currTask.toString();
       // System.out.println("OK, I've marked this task as not done yet:");
        // System.out.println("\t" + currTask.toString());
    }

    /**
     * Prints all tasks in the task list.
     * If the task list is empty, prints a message indicating that the list is empty.
     */
    public String listTasks() {
        if (this.tasks.size() == 0) {
            return "\tThe task list is empty.";
        } else {
            String result = "\tHere are the tasks in your list: \n";
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                result += "\t" + (i + 1) + "." + currTask.toString() + "\n";
            }
            return result;
        }
    }

    /**
     * Finds tasks containing a specified keyword.
     *
     * @param keyWord The keyword to search for in task descriptions.
     * @return A list of tasks containing the keyword.
     * @throws ChatBotException If no tasks match the keyword.
     */
    public TaskList findTasks(String keyWord) throws ChatBotException {
        List<Task> matchingTasks = new ArrayList<>();
        boolean isFound = false;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyWord)) {
                matchingTasks.add(task);
                isFound = true;
            }
        }
        if (!isFound) {
            throw new ChatBotException("Oops! No tasks matching the keyword \"" + keyWord + "\" were found." );
        } else {
            System.out.println("\tHere are the matching tasks in your list: ");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task currTask = matchingTasks.get(i);
                System.out.println("\t" + (i + 1) + "." + currTask.toString());
            }
        }
        return new TaskList(matchingTasks);
    }
}

