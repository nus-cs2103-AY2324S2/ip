package duchess;

import java.util.ArrayList;
import java.util.HashSet;

import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.ToDo;

import javafx.util.Pair;

/**
 * TaskList class represents a list of tasks in the Duchess program.
 * It provides methods to add, delete, and manipulate tasks in the list.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = this.tasks.size();
    }


    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     * @throws DuchessException if an error occurs while adding the task
     */
    private Task addTask(Task task) throws DuchessException {
        if (this.taskCount >= MAX_TASKS) {
            throw new DuchessException("The task list is full. I cannot add more tasks.");
        }
        this.tasks.add(task);
        this.taskCount++;
        return task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex the index of the task to be deleted
     * @throws DuchessException if the task index is invalid
     */
    public Task deleteTask(int taskIndex) throws DuchessException {
        if (isInvalidTaskIndex(taskIndex)) {
            throw new DuchessException("Invalid task index.");
        }
        Task deletedTask = this.tasks.remove(taskIndex);
        this.taskCount--;
        return deletedTask;
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex the index of the task to be marked as done
     * @throws DuchessException if the task index is invalid
     */
    public Task markTaskAsDone(int taskIndex) throws DuchessException {
        if (isInvalidTaskIndex(taskIndex)) {
            throw new DuchessException("Invalid task index.");
        }
        Task task = this.tasks.get(taskIndex);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskIndex the index of the task to be unmarked
     * @throws DuchessException if the task index is invalid
     */
    public Task unmarkTaskAsDone(int taskIndex) throws DuchessException {
        if (isInvalidTaskIndex(taskIndex)) {
            throw new DuchessException("Invalid task index.");
        }
        Task task = this.tasks.get(taskIndex);
        task.unmarkAsDone();
        return task;
    }

    /**
     * Finds tasks containing the specified keyword in their descriptions.
     *
     * @param keyword the keyword to search for in task descriptions
     * @return an ArrayList of Pair objects containing the index of the matching task in the original list and the
     * matching task itself
     */
    public ArrayList<Pair<Integer, Task>> findTasksByKeyword(String keyword) {
        ArrayList<Pair<Integer, Task>> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(keyword)) {
                matchingTasks.add(new Pair<>(i, task));
            }
        }
        return matchingTasks;
    }

    /**
     * Adds a ToDo task to the task list.
     *
     * @param description the task details
     * @throws DuchessException if an error occurs while adding the task
     */
    public Task addToDo(String description) throws DuchessException {
        if (description.isEmpty()) {
            throw new DuchessException("Oh dear! That is an invalid command. Try: todo <description>");
        }
        ToDo newToDo = new ToDo(description);
        return addTask(newToDo);
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param userInput the user input containing task details
     * @throws DuchessException if an error occurs while adding the task
     */
    public Task addDeadline(String userInput) throws DuchessException {
        String[] deadlineTokens = userInput.split("/by");
        if (deadlineTokens.length <= 1) {
            throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> "
                    + "/by <deadline>");
        }

        String description = deadlineTokens[0].trim();
        String by = deadlineTokens[1].trim();
        Deadline newDeadline = new Deadline(description, by);
        return addTask(newDeadline);
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param userInput the user input containing task details
     * @throws DuchessException if an error occurs while adding the task
     */
    public Task addEvent(String userInput) throws DuchessException {
        String[] eventTokens = userInput.split("/from | /to");
        if (eventTokens.length <= 1) {
            throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> "
                    + "/from <start> /to <end>");
        }
        String description = eventTokens[0].trim();
        String from = eventTokens[1].trim(); // from is everything after
        String to = eventTokens[2].trim(); // to is everything after
        Event newEvent = new Event(description, from, to);
        return addTask(newEvent);
    }

    /**
     * Finds and returns a list of duplicate tasks in the task list.
     * A duplicate task is defined as a task with the same content as another task in the list.
     *
     * @return an ArrayList of Pair objects containing the index of the duplicate task in the original list and the
     *         duplicate task itself
     */
    public ArrayList<Pair<Integer, Task>> listDuplicateTasks() {
        HashSet<Task> uniqueTasks = new HashSet<>();
        ArrayList<Pair<Integer, Task>> duplicateTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("Number" + i + " " + task);
            if (!uniqueTasks.add(task)) {
                // If task is already in the set, it's a duplicate
                duplicateTasks.add(new Pair<>(i, task));
            }
        }

        return duplicateTasks;
    }

    /**
     * Prints the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.taskCount == 0) {
            sb.append("No tasks have been added yet.");
        } else {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < this.taskCount; i++) {
                sb.append(" ").append(i + 1).append(". ").append(this.tasks.get(i).toString()).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Checks if the task index is valid.
     *
     * @param taskIndex the index of the task to be checked
     * @return true if the task index is valid, false otherwise
     */
    private boolean isInvalidTaskIndex(int taskIndex) {
        return taskIndex < 0 || taskIndex >= this.taskCount;
    }

    /**
     * Gets the list of tasks.
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTaskCount() {
        return this.taskCount;
    }
}

