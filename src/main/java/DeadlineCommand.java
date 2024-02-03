import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String name;
    private LocalDateTime by;

    public DeadlineCommand(String[] commands) {
        String[] deadlineSplit = commands[1].split(" /by ");
        this.name = deadlineSplit[0];
        this.by = Command.parseDateTime(deadlineSplit[1]);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newDeadline = new Deadline(this.name, this.by);
        tasks.addTask(newDeadline);
        ui.printTaskAdded(newDeadline, tasks.getSize());
        storage.save(tasks);
    }

}
