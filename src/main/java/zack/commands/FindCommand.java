package zack.commands;

import zack.ZackException;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZackException {
        TaskList foundTasks = tasks.findTasksByKeyword(keyword);
        ui.showFoundTasks(foundTasks);
    }
}
