public class ByeCommand extends Command {

    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) {
        ui.showGoodbye();
    }
}
