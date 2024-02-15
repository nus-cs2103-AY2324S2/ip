package command;
import task.Deadline;
import java.time.LocalDateTime;

public class DeadlineCmd extends Command {
    private Deadline task;
    @Override
    public String execute() {
        tasks.add(task);
        response = ui.addedResponse(task.toString());
        return response;
    }

    public Deadline getTask() {
        return task;
    }

    public DeadlineCmd(String description, LocalDateTime by) {
        task = new Deadline(description, by);
    }
}
