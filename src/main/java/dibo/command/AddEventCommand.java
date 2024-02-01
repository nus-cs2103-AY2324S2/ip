package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;
import dibo.task.Event;

import java.time.LocalDate;

/**
 * Class to handle the command which adds a to-do task.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructor for the AddEventCommand class.
     *
     * @param description The description of event task.
     * @param start The LocalDate object of the start of the event.
     * @param end The LocalDate object of the end of the event.
     */
    public AddEventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Run the add event task command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the added message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     * @throws DiboException when an error occurs when trying to save the changes into a text file.
     */
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        Event event = new Event(this.description, this.start, this.end);
        taskList.addTask(event);
        ui.showAdded(event.toString(), taskList.getSize());
        storage.save(taskList);
    }
}

