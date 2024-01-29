package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String SUCCESS_MESSAGE = "Congrats, you have no more tasks! Uncle is proud of you!";

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tasks.isEmpty()) {
            ui.showToUser(SUCCESS_MESSAGE);
        } else {
            ui.showList(tasks);
        }

    }
}
