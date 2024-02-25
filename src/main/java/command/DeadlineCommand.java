package command;

import emis.TaskList;
import task.Deadline;
import emis.Storage;

/**
 * The DeadlineCommand class represents a command to add a deadline task in the EMIS application.
 * When executed, it adds a deadline task with the specified description and due date to the task list.
 */
public class DeadlineCommand extends Command {
    /** The description of the deadline task. */
    private String description;
    
    /** The due date of the deadline task. */
    private String by;

    /**
     * Constructs a new DeadlineCommand object with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command by adding a new Deadline task to the task list with the specified description and due date,
     * and updates the storage.
     *
     * @param tasklist The TaskList object representing the list of tasks.
     * @param storage The Storage object handling loading and saving of tasks.
     * @return A message indicating the result of executing the command.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        String response = tasklist.addTask(new Deadline(this.description, this.by));
        storage.updateStorage();
        return response;
    }
}
