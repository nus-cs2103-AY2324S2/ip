public class ListCommand extends Command {
    ListCommand() {
        super("" );
    }

    @Override
    void execute(TaskList taskList, Ui ui) throws MikeException {
        if (taskList.isEmpty()) {
            throw new MikeException("You have no more tasks Sulley...");
        }
        ui.display("You and I are a team.\nHere is the task list:");
        ui.display(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
