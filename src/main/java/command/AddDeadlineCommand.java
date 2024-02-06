package command;
import duke.Ui;
import task.Deadline;
import task.TaskList;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.showAddTask(deadline, tasks.size());

    }
    
}
