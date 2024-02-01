package command;

import data.Event;
import data.Task;
import data.TaskList;
import data.exception.CoDriverException;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final String description;
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public EventCommand(String description, LocalDate fromDate, LocalDate toDate) {
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task newTask = new Event(this.description, this.fromDate, this.toDate);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks.size());
    }
}
