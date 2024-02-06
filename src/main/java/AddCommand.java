public class AddCommand extends Command {
    private Task t;
    public AddCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.t);
        setExit(false);
    }
}
