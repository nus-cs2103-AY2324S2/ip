package duchess;

import duchess.task.TaskType;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.ToDo;

import java.util.ArrayList;

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
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = this.tasks.size();
    }

    /**
     * Adds a ToDo task to the task list.
     * @param userInput the user input containing task details
     * @throws DuchessException if an error occurs while adding the task
     */
    public void addToDo(String userInput) throws DuchessException {
        String[] toDoTokens = userInput.split("todo"); //Split to find description
        if (toDoTokens.length > 1) {
            String description = toDoTokens[1].trim(); //Trim to only keep description
            ToDo newToDo = new ToDo(description);
            addTask(newToDo, TaskType.TODO);
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: todo <description>");
        }
    }

    /**
     * Adds a Deadline task to the task list.
     * @param userInput the user input containing task details
     * @throws DuchessException if an error occurs while adding the task
     */
    public void addDeadline(String userInput) throws DuchessException {
        String[] deadlineTokens = userInput.split("deadline");

        if (deadlineTokens.length > 1) {
            // Split further to extract description and deadline details
            String[] details = deadlineTokens[1].trim().split("/by");

            if (details.length > 1) {
                String description = details[0].trim();
                String by = details[1].trim(); // by is everything after
                Deadline newDeadline = new Deadline(description, by);
                addTask(newDeadline, TaskType.DEADLINE);
            } else {
                throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> /by <deadline>");
            }
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> /by <deadline>");
        }
    }

    /**
     * Adds an Event task to the task list.
     * @param userInput the user input containing task details
     * @throws DuchessException if an error occurs while adding the task
     */
    public void addEvent(String userInput) throws DuchessException {
        String[] eventTokens = userInput.split("event");

        if (eventTokens.length > 1) {
            // Split further to extract description and event details
            String[] details = eventTokens[1].trim().split("/from|/to"); // Means can use either /from or /to as delimiter

            if (details.length > 2) {
                String description = details[0].trim();
                String from = details[1].trim(); // from is everything after
                String to = details[2].trim();   // to is everything after

                Event newEvent = new Event(description, from, to);
                addTask(newEvent, TaskType.EVENT);
            } else {
                throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> /from <start> /to <end>");
            }
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> /from <start> /to <end>");
        }
    }

    /**
     * Prints the task list.
     */
    public void printTaskList() {
        //printHorizontalLine();
        if (this.taskCount == 0) {
            System.out.println(" No tasks have been added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < this.taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + this.tasks.get(i).toString());
            }
        }
        //printHorizontalLine();
    }

    /**
     * Adds a task to the task list.
     * @param task the task to be added
     * @param taskType the type of the task (ToDo, Deadline, or Event)
     * @throws DuchessException if an error occurs while adding the task
     */
    private void addTask(Task task, TaskType taskType) throws DuchessException {
        if (this.taskCount < MAX_TASKS) {
            this.tasks.add(task);
            this.taskCount++;

            //printHorizontalLine();
            System.out.println(" Understood. I've added this " + taskType + " task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + this.taskCount + " tasks in the list.");
            //printHorizontalLine();
        } else {
            throw new DuchessException("The task list is full. I cannot add more tasks.");
        }
    }

    /**
     * Deletes a task from the task list.
     * @param taskIndex the index of the task to be deleted
     * @throws DuchessException if the task index is invalid
     */
    public void deleteTask(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            Task deletedTask = this.tasks.remove(taskIndex);
            this.taskCount--;

            //printHorizontalLine();
            System.out.println(" Understood. I've deleted this task:");
            System.out.println(deletedTask.toString());
            System.out.println(" Now you have " + this.taskCount + " tasks in the list.");
            //printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    /**
     * Marks a task as done.
     * @param taskIndex the index of the task to be marked as done
     * @throws DuchessException if the task index is invalid
     */
    public void markTaskAsDone(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            this.tasks.get(taskIndex).markAsDone();
            //printHorizontalLine();
            System.out.println(" Perfect! I've marked this task as done:");
            System.out.println(this.tasks.get(taskIndex).toString());
            //printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    /**
     * Unmarks a task as done.
     * @param taskIndex the index of the task to be unmarked
     * @throws DuchessException if the task index is invalid
     */
    public void unmarkTaskAsDone(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            this.tasks.get(taskIndex).unmarkAsDone();
            //printHorizontalLine();
            System.out.println(" Understood, I've marked this task as not done yet:");
            System.out.println(this.tasks.get(taskIndex).toString());
            //printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    /**
     * Checks if the task index is valid.
     * @param taskIndex the index of the task to be checked
     * @return true if the task index is valid, false otherwise
     */
    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < this.taskCount;
    }

    /**
     * Gets the list of tasks.
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}

