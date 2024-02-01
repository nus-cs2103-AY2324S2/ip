public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.println(tasks);
    }
}
