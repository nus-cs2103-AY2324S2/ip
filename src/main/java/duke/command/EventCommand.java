package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String task;
    private LocalDate start;
    private LocalDate end;

    public EventCommand(String task, LocalDate start, LocalDate end) {
        this.task = task;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task newEvent = Task.createTask(TaskType.EVENT, task, start, end);
        list.add(newEvent);
        storage.save(list);
        ui.showMessage("added new event: " + newEvent);
        ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");
    }
}