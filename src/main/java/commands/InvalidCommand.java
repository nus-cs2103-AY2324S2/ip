public class InvalidCommand extends Command {
    public String message;
    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printMessage(this.message);
    }
}
