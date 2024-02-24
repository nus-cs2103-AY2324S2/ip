package seiki.commands;

import java.util.ArrayList;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'find' command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private ArrayList<String> args;

    public FindCommand(ArrayList<String> args) {
        this.args = args;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        if (taskList.getTaskCount() == 0) {
            throw new SeikiException("There are currently no tasks to search from.");
        } else {
            assert taskList.getTaskCount() > 0 : "Task list should contain tasks";
            String keyword = args.get(0).trim();
            assert !keyword.isEmpty() : "Keyword should not be empty";
            TaskList resultList = taskList.searchByKeyword(keyword);

            if (resultList.getTaskCount() == 0) {
                throw new SeikiException("There are no task that matches the keyword: " + keyword);
            } else {
                assert resultList.getTaskCount() > 0 : "Result list should contain tasks";
                return ui.showFindTask(keyword, resultList);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
