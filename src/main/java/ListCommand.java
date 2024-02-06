public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws LivException {
        Ui.displayListCommand();
    }
}
