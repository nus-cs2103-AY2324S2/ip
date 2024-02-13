package duke.commands;

import duke.fileUtils.FilePaths;
import duke.fileUtils.FileUtil;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

public class Help extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        FileUtil.displayFile(FilePaths.HELP_PATH);
    }


}
