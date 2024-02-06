import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            tasks.deleteTaskAtIndex(index);
        } catch (NumberFormatException e) {
            ui.echo("Sorry, there is no number detected.\n" +
                    "The correct way to use the command is: delete number");
        } catch (IndexOutOfBoundsException e) {
            ui.echo("Sorry, the item does not exist in the list.\n" +
                    "The correct way to use the command is: delete number");
        }
        storage.saveAllToFile(tasks);
    }
}
