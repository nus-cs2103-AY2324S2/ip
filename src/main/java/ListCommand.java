public class ListCommand extends Command {

    @Override
    public void execute (Ui ui) {
        ui.printToUser(super.taskList.toString());
    }
}
