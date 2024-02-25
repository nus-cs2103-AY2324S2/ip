package command;

import emis.TaskList;
import task.ToDo;
import emis.Storage;

/**
 * The ToDoCommand class represents a command to add a to-do task in the EMIS application.
 * When executed, it adds a new to-do task with the specified description to the task list and updates the storage.
 */
public class ToDoCommand extends Command {
    /** The description of the to-do task to be added. */
    private String description;

    /**
     * Constructs a new ToDoCommand object with the specified description.
     * 
     * @param description The description of the to-do task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the to-do command by adding a new to-do task with the specified description to the task list and updating the storage.
     * 
     * @param tasklist The TaskList object representing the list of tasks.
     * @param storage The Storage object handling loading and saving of tasks.
     * @return A message indicating the result of executing the command.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        String response = tasklist.addTask(new ToDo(this.description));
        storage.updateStorage();
        return response;
    }
}
