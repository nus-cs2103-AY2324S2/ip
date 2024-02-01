import java.time.LocalDate;

public class OnCommand extends Command{

    private LocalDate targetDate;

    OnCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTasksOn(targetDate, tasks.getTasks());
    }
}
