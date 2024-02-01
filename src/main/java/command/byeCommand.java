package command;
import duke.Ui;
import duke.TaskList;

public class byeCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    public byeCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws Exception {
        taskList.bye(ui);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
