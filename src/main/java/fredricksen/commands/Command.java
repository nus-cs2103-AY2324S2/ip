package fredricksen.commands;

import fredricksen.tasks.Task;
import fredricksen.tasks.TaskList;

/**
 * Represents a Command class with an Array of all the words in the command
 * and a TaskList of all the current tasks.
 * The Command class serves as a base class for various commands such as
 * ToDoCommand, DeadlineCommand, HelpCommand and more.
 */
public class Command {

    private String[] fullCommand;
    private TaskList tasks;

    /**
     * Constructs a Command object with an array of words from the user input command
     * and the TaskList to store the current tasks in.
     *
     * @param fullCommand an Array consisting of all the individual words from the user input command.
     * @param tasks The TaskList to store the current tasks.
     */
    public Command(String[] fullCommand, TaskList tasks) {
        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }

    public Command() {}

    public String execute() {
        return "hello I am command's main class";
    }

    /**
     * Display the task that have been added into the tasks list
     *
     * @param task The Task to display.
     * @param tasks The TaskList to add the task into.
     * @return A formatted String which shows the task added and number of tasks currently.
     */
    public String displayTask(Task task, TaskList tasks) {
        String singular = tasks.size() == 1 ? "task" : "tasks";

        assert tasks.size() == 1 : "task";

        int num = tasks.size();
        return "Got it. I've added this task: \n"
                + "    " + task
                + "\nNow you have " + num + " " + singular + " in the list.";
    }

    /**
     * A getter method to get the array of words in the user input command
     *
     * @return an Array of Strings based on the user input command.
     */
    public String[] getFullCommand() {
        return this.fullCommand;
    }

    /**
     * A getter method to get the TaskList of tasks
     *
     * @return A TaskList of Task Type tasks.
     */
    public TaskList getTasks() {
        return this.tasks;
    }
}
