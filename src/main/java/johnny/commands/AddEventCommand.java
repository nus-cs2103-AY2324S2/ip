package johnny.commands;

import java.time.LocalDateTime;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Event;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

public class AddEventCommand extends Command {

    private String name;
    private LocalDateTime from;
    private LocalDateTime to;

    public AddEventCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = new Event(name, from, to);
        tasks.addTask(task);
        storage.appendToFile(task);
        ui.showAddTask(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
