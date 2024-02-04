package ben.commands;

import ben.storage.Storage;
import ben.tasks.Event;
import ben.tasks.Task;
import ben.tasks.TaskList;
import ben.ui.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public EventCommand(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newEvent = new Event(false, description, startDate, endDate);
        tasks.addTask(newEvent);

        ui.showAddedTaskMessage();
        ui.show(newEvent.toString());
        ui.showCurrNoOfTasks(tasks);
    }
}