package command;
import duke.Ui;
import duke.TaskList;

public class showListCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    public showListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws Exception {
        taskList.showList();
    }

    @Override
    public boolean isExit() {
       return false;
    }
}
