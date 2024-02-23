package commands;

import exceptions.WeiException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * Searches for tasks using keywords.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Creates FIndCommand object.
     *
     * @param input User command.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Looks for matching tasks and show to the user.
     *
     * @param tasks TaskList of the user.
     * @param ui User interface.
     * @throws WeiException If the command is incomplete.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WeiException {
        if (input.length() < 6) {
            throw new WeiException("please tell me what you are looking for");
        }
        String keyword = this.input.substring(5);
        String searchResult = tasks.find(keyword);
        return ui.showSearchResult(searchResult);
    }

    /**
     * {@inheritDoc}
     *
     * @return false;
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
