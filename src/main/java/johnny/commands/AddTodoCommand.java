package johnny.commands;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.tasks.ToDo;
import johnny.ui.Ui;

/**
 * Controls what happens when a Todo is being added.
 */
public class AddTodoCommand extends Command {

    /** Name of the Deadline. */
    private String name;

    /**
     * Constructor for AddTodoCommand.
     *
     * @param name Name of the Event.
     */
    public AddTodoCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the process of a AddTodoCommand.
     * Instantiates a new Command, adds it to TaskList, writes it to Storage and Ui shows appropriate response.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException If data cannot be written to Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = new ToDo(name);
        tasks.addTask(task);
        storage.appendToFile(task);
        ui.showAddTask(task, tasks);
    }

    /**
     * Returns False so chatbot can continue running.
     *
     * @return False so the loop keeps running.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
