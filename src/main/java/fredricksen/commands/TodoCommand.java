package fredricksen.commands;

import fredricksen.tasks.TaskList;
import fredricksen.tasks.ToDo;

/**
 * Represents an "ToDo" Command, which extends the Command class.
 * An "ToDo" Command is a command that creates a ToDoCommand object with
 * the user input command and the TaskList tasks to store the task.
 */
public class TodoCommand extends Command {
    private TaskList tasks;
    private String fullCommand;

    /**
     * Constructs an ToDoCommand instance with the specified user input command
     * and the TaskList to store the ToDo task in.
     *
     * @param fullCommand The user input command to be executed.
     * @param tasks the TaskList to store the various tasks.
     */
    public TodoCommand(String fullCommand, TaskList tasks) {
        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }

    /**
     * It creates a new ToDo instance.
     * Then it triggers the displayTask method in the base class to display the add task message
     * and then adds the ToDo instance into the TaskList containing all current Task type object.
     *
     * @return A String that is the message to be displayed after successfully adding an ToDo task.
     */
    @Override
    public String execute() {
        ToDo newTdTask = new ToDo(this.fullCommand, "T", false);
        this.tasks.addTask(newTdTask);
        return displayTask(newTdTask, this.tasks);
    }
}
