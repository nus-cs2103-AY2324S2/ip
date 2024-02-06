public class ListAllCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        new ListCommand(tasks).execute(tasks, ui, storage);
    }
}
