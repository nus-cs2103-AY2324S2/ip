public class InvalidCommand extends Command {
    public String message;
    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printMessage(this.message);
    }
}