package commands;

import fileUtils.FilePaths;
import fileUtils.FileUtil;
import mainUtils.Storage;
import mainUtils.Ui;
import tasks.TaskList;

public class Help extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        FileUtil.displayFile(FilePaths.HELP_PATH);
    }


}
