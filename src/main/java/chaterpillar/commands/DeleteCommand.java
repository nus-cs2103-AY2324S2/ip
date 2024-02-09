package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException, IOException {
        try {
            tasks.deleteTaskAtIndex(index);
        } catch (NumberFormatException e) {
            throw new ChaterpillarException(
                    "Sorry, there is no number detected.\n"
                    + "The correct way to use the command is: delete number");
        } catch (IndexOutOfBoundsException e) {
            throw new ChaterpillarException(
                    "Sorry, the item does not exist in the list.\n"
                    + "The correct way to use the command is: delete number");
        }
        storage.saveAllToFile(tasks);
    }
}
