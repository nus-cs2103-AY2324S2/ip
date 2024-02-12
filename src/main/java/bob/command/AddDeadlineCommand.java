package bob.command;

import java.time.LocalDateTime;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.SavingException;
import bob.task.Task;

public class AddDeadlineCommand extends AddCommand {
    private final LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws SavingException {
        Task task = taskList.addDeadline(description, by);
        ui.showAdd(task, taskList.getSize());
        storage.saveTask(task);
    }
}
