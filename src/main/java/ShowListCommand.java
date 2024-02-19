public class ShowListCommand extends Command {
    ShowListCommand(String command) {
        super(command);
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
