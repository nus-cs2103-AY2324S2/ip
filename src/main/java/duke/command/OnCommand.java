package duke.command;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.time.LocalDate;

public class OnCommand extends Command {

    private LocalDate targetDate;

    public OnCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTasksOn(targetDate, tasks.getTasks());
    }
}
