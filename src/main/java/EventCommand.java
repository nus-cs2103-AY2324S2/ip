import java.time.LocalDateTime;

public class EventCommand extends Command {
    protected String description;
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected boolean isDone;


    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.isDone = false;
    }

    public EventCommand(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, isDone, from, to);
        tasks.addToList(task);
        ui.addedTaskPrinter(task, tasks.taskNum());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
