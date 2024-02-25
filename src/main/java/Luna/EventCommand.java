package Luna;

import java.time.LocalDate;

public class EventCommand extends Command {

    String taskName;
    LocalDate startDate;
    LocalDate endDate;

    public EventCommand(String tn, LocalDate sd, LocalDate ed) {
        super(CommandType.EVENT);
        this.taskName = tn;
        this.startDate = sd;
        this.endDate = ed;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        tl.add(taskName, startDate, endDate);
    }

}
