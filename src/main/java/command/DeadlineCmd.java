package command;
import task.Deadline;
import java.time.LocalDateTime;

public class DeadlineCmd extends Command {
    public Deadline task;
    public void execute() {
        tasks.add(task);
        ui.addedResponse(task.toString());
    }

    public DeadlineCmd(String description, LocalDateTime by) {
        task = new Deadline(description, by);
    }
}
