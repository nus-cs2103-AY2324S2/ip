package johnny.commands;

import java.time.LocalDateTime;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Event;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when an Event is being added.
 */
public class AddEventCommand extends Command {

    /** Name of the Event. */
    private String name;
    /** When the Event starts from. */
    private LocalDateTime from;
    /** When the Event lasts to. */
    private LocalDateTime to;

    /**
     * Constructor for AddEventCommand.
     *
     * @param name Name of the Event.
     * @param from When the Event starts from.
     * @param to When the Event lasts to.
     */
    public AddEventCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the process of a AddEventCommand.
     * Instantiates a new Event, adds it to TaskList, writes it to Storage and Ui shows appropriate response.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException If data cannot be written to Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = new Event(name, from, to);
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
