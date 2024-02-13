public class WrongCommand extends Command {
    public WrongCommand(String message) {
        super();
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showWrongCommand();
    }
    public  boolean isExit() {
        return false;
    }
}
