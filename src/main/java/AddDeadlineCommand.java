import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {

    private String name;
    private LocalDateTime by;

    public AddDeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = new Deadline(name, by);
        tasks.addTask(task);
        storage.appendToFile(task);
        ui.showAddTask(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
