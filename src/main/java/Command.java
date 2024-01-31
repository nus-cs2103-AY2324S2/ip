public abstract class Command {
    public boolean isBye() {
        return false;
    }

    public abstract void execute(TodoList todoList, Storage storage, Ui ui) throws CheckbotException;
}
