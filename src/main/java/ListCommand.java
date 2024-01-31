public class ListCommand extends Command {
    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) {
        ui.print("Here is your todo list:\n" + todoList);
    }
}
