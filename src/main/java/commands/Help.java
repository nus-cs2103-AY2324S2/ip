package commands;

import utils.FilePath;
import view.EncaseLines;

import static utils.FileUtil.getFile;

public class Help implements Command {
    @Override
    public void execute() {
        EncaseLines.display(getFile(FilePath.HELP_PATH));
    }
}
