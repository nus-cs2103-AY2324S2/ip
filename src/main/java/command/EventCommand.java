package command;

import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    protected String description;
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected boolean isDone;

    /**
     * Constructor for Event Command.
     *
     * @param description Description of Event.
     * @param from LocalDateTime object of from of Event.
     * @param to LocalDateTime object of to of Event.
     * */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.isDone = false;
    }

    /**
     * Constructor for Event Command.
     *
     * @param description Description of Event.
     * @param from LocalDateTime object of from of Event.
     * @param to LocalDateTime object of to of Event.
     * @param isDone Boolean value if Event is done.
     * */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }


    /**
     * Creates a new Event and adds it to the current TaskList.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, isDone, from, to);
        tasks.addToList(task);
        storage.storeToLocal(tasks);

        return ui.addedTaskPrinter(task, tasks.taskNum());
    }

    /**
     * Informs if this command is an Exit command.
     *
     * @return Boolean value of true if this command is an exit command.
     * */
    @Override
    public boolean isExit() {
        return false;
    }
}
