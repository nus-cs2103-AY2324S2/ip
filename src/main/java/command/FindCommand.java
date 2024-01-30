package command;

import exception.TobiasException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {

    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.printListFind(keyWord);
        } catch (TobiasException e) {
            e.printMessage();
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
