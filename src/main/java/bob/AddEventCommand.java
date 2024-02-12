package bob;

import java.time.LocalDateTime;

public class AddEventCommand extends AddCommand {
    private LocalDateTime from;
    private LocalDateTime to;

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
