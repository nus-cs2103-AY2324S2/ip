package bob.command;

import java.time.LocalDateTime;

import bob.exception.InvalidEventException;
import bob.Storage;
import bob.exception.SavingException;
import bob.task.Task;
import bob.TaskList;
import bob.Ui;

public class AddEventCommand extends AddCommand {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws SavingException, InvalidEventException {
        Task task = taskList.addEvent(description, from, to);
        ui.showAdd(task, taskList.getSize());
        storage.saveTask(task);
    }
}
