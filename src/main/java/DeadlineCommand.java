import java.time.LocalDate;

public class DeadlineCommand extends Command {

    String taskName;
    LocalDate endDate;

    private DeadlineCommand() {
        super(CommandType.DEADLINE);
    }


    public DeadlineCommand(String tn, LocalDate ed) {
        super(CommandType.DEADLINE);
        taskName = tn;
        this.endDate = ed;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        tl.add(taskName, endDate);
    }

}
