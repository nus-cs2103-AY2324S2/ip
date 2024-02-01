package command;

import data.Deadline;
import data.Task;
import data.TaskList;
import data.exception.CoDriverException;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate date;

    public DeadlineCommand(String description, LocalDate date) {

        this.description = description;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task newTask = new Deadline(this.description, this.date);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks.size());
    }
}
