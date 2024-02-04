package duke.command;

import duke.task.Event;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/*
* The EventCommand class is a subclass of Command and represents a command to add an Event task to the task list.
* It takes in a description String, from and to as LocalDate objects.
 */
public class EventCommand extends Command {
    protected String description;
    protected LocalDate from;
    protected LocalDate to;

    /*
     * Constructs EventCommand object with description, from and to as LocalDate objects.
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        ui.showTaskAdded(event, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}