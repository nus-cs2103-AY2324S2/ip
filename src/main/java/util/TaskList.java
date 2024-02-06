package util;

import exceptions.ChatBotException;
import tasks.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks and provides methods to manipulate the tasks.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private static final String FILE_PATH = "./data/duke/.txt";
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (this.tasks.size() < MAX_TASKS) {
            this.tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
            System.out.println("Now you have " + this.tasks.size() + " task" +
                    (this.tasks.size() == 1 ? "" : "s") + " in the list");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param number The index of the task to be deleted.
     * @throws ChatBotException If an error occurs while deleting the task.
     */
    public void deleteTask(int number) throws ChatBotException {
        if (this.tasks.size() == 0) {
            throw new ChatBotException("Oops! There are no tasks in the list.");
        }
        if (number > this.tasks.size()) {
            throw new ChatBotException("Oops! Number entered does not exist in the list.");
        }
        Task remainingTasks = this.tasks.remove(number - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + remainingTasks.toString());
        System.out.println("Now you have " + this.tasks.size() + " task" +
                (this.tasks.size() == 1 ? "" : "s") + " in the list");
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws ChatBotException If an error occurs while marking the task as done.
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
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + currTask.toString());
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws ChatBotException If an error occurs while marking the task as not done.
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
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + currTask.toString());
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        if (this.tasks.size() == 0) {
            //throw new exceptions.ChatBotException("Oops! The task list is currently empty.");
            System.out.println("Oops! The task list is currently empty.");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + currTask.toString());
            }
        }
    }
}

