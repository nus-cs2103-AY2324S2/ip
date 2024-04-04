package command;

import exception.TobiasException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {

    private String keyWord;

    /**
     * Constructor for Find Command.
     *
     * @param keyWord The keyword that is being searched for.
     * */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }


    /**
     * Finds the tasks with the keyword and prints them in the console.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";

        try {
            result = tasks.printListFind(keyWord);
        } catch (TobiasException e) {
            result = e.printMessage();
        }

        return result;
    }

    /**
     * Informs if this command is an Exit command.
     *
     * @return boolean value of true if this command is an exit command.
     * */
    @Override
    public boolean isExit() {
        return false;
    }
}
