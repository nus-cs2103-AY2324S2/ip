public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeText();
    }


}
