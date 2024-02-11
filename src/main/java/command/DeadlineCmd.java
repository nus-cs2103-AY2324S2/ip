package command;
import task.Deadline;
import java.time.LocalDateTime;

public class DeadlineCmd extends Command {
    private Deadline task;
    @Override
    public void execute() {
        tasks.add(task);
        ui.addedResponse(task.toString());
    }

    public Deadline getTask() {
        return task;
    }

    public DeadlineCmd(String description, LocalDateTime by) {
        task = new Deadline(description, by);
    }
}
