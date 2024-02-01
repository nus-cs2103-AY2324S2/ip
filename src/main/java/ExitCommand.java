public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        ui.say("Bye!");
    }
}
