package commands;

import utils.FilePath;
import view.EncaseLines;

import static utils.FileUtil.getFile;

/**
 * The Help class represents a command to display help information.
 * It implements the Command interface and specifies the execution behavior for displaying help content.
 */
public class Help implements Command {

    /**
     * Executes the Help command by displaying help information retrieved from the specified file path.
     */
    @Override
    public void execute() {
        EncaseLines.display(getFile(FilePath.HELP_PATH));
    }
}

