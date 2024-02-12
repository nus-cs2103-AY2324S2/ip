package bob;

import java.time.LocalDateTime;

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
