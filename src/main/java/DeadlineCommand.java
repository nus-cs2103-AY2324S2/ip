import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    protected String description;
    protected LocalDateTime deadline;
    protected boolean isDone;


    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
        this.isDone = false;
    }

    public DeadlineCommand(String description, LocalDateTime deadline, boolean isDone) {
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, isDone, deadline);
        tasks.addToList(task);
        ui.addedTaskPrinter(task, tasks.taskNum());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
