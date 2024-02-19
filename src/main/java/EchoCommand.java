public class EchoCommand extends Command {
    EchoCommand(String command) {
        super(command);
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEcho(this.command);
    }
}
