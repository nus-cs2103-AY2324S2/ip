package main.java.emis.command;
import main.java.emis.*;
import main.java.emis.task.ToDo;

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
     * Executes the to-do command by adding a new to-do task with the specified description to the task lsit and updating the storage.
     * 
     * @param t The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param s The Storage object handling loading and saving of tasks.
     */
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        t.addTask(new ToDo(this.description));
        s.updateStorage();
    }

    /**
     * Indicates whether the command is an exit command.
     * 
     * @return false, as the to-do command does not represent an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}