public class ListCommand extends Command {
    ListCommand() {
        super("" );
    }

    @Override
    void execute(TaskList taskList) throws MikeException {
        if (taskList.isEmpty()) {
            throw new MikeException("You have no more tasks Sulley...");
        }
        Ui.display("You and I are a team.\nHere is the task list:");
        Ui.display(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
