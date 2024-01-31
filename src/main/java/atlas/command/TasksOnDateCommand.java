package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;

import java.time.LocalDate;

public class TasksOnDateCommand extends Command {
    private LocalDate date;

    public TasksOnDateCommand(TaskList tasks, Ui ui, Storage storage, LocalDate date) {
        super(tasks, ui, storage);
        this.date = date;
    }

    @Override
    public void execute() {
        ui.showTasksOnDate(tasks, date);
    }
}
