import java.io.IOException;

public class MarkCommand extends Command{
    private String[] commandArr;

    public MarkCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(commandArr.length > 1 ? commandArr[1] : "");
            FileManaging.writeToFile(CommandType.FILEPATH.toString(), tasks);
        } catch (DukeException e) {
            ui.displayToScreen(e.getMessage());
        } catch (IOException e) {
            ui.displayToScreen(e.getMessage());
        }
    }
}
