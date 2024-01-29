import java.io.IOException;

public class AddCommand extends Command {

    private String[] commandArr;

    public AddCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(commandArr[0], commandArr.length > 1 ? commandArr[1] : "");
            FileManaging.writeToFile(CommandType.FILEPATH.toString(), tasks);
        } catch (DukeException e) {
            ui.displayToScreen(e.getMessage());
        } catch (IOException e) {
            ui.displayToScreen(e.getMessage());
        }
    }
}
