package dibo.command;

import java.time.LocalDate;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;
import dibo.task.Event;

/**
 * The AddEventCommand class represents a command to add an event task to TaskList.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructs a new AddEventCommand object with the specified parameters.
     *
     * @param description The String description of event task.
     * @param start The LocalDate object of the start of the event.
     * @param end The LocalDate object of the end of the event.
     */
    public AddEventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        Event event = new Event(this.description, this.start, this.end);
        taskList.addTask(event);
        ui.storeAddedMessage(event.toString(), taskList.getSize());
        storage.saveData(taskList);
    }
}

