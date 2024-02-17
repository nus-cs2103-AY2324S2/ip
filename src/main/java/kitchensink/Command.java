package kitchensink;

import java.io.IOException;

/**
 * Currently unused. May refactor code to use this class in the future.
 */
public abstract class Command {
    public void execute(List taskList, Ui ui, Storage storage) throws IOException {
        storage.saveTasks(taskList);
    }
}
