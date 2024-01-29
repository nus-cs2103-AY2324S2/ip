public class UnknownCommand implements Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Sorry I don't know what that means.\n"
                + "\t Try keywords: todo, deadline, event, list, mark, unmark, delete.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
