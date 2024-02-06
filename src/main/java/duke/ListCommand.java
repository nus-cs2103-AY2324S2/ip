package duke;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
        setExit(false);
    }
}
