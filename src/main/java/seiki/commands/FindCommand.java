package seiki.commands;

import static seiki.common.Messages.MESSAGE_EMPTY_TASKLIST;
import static seiki.common.Messages.MESSAGE_FIND_FAIL;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'find' command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_HELPER = "Please follow the format: find [keyword]";
    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        taskList.checkIfListEmpty(MESSAGE_EMPTY_TASKLIST);
        TaskList resultList = taskList.searchByKeyword(keyword);
        resultList.checkIfListEmpty(String.format(MESSAGE_FIND_FAIL, keyword));
        return ui.showFindTask(keyword, resultList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
