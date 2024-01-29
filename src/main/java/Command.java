public interface Command {
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    public boolean isExit();
}
