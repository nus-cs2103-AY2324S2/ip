public class QuitApplication extends Command {
    public QuitApplication(String fullCommand) {
        super(fullCommand);
        this.exit = true;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.quitApplication();
    }
}