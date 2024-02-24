import java.time.LocalDate;

public class TodoCommand extends Command {

    String taskName;

    private TodoCommand() {
        super(CommandType.TODO);
    }


    public TodoCommand(String tn) {
        super(CommandType.TODO);
        taskName = tn;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        tl.add(taskName);
    }

}
