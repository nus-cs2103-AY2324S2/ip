package johnny.commands;

import java.time.LocalDateTime;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Deadline;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when a Deadline is being added.
 */
public class AddDeadlineCommand extends Command {

    /** Name of the Deadline. */
    private String name;
    /** When the Deadline is due by. */
    private LocalDateTime by;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param name Name of the Deadline.
     * @param by When the Deadline is due by.
     */
    public AddDeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    /**
     * Executes the process of a AddDeadlineCommand.
     * Instantiates a new Deadline, adds it to TaskList, writes it to Storage and Ui shows appropriate response.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException If data cannot be written to Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = new Deadline(name, by);
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
