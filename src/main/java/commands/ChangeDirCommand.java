package commands;

import java.io.IOException;
import exceptions.LoadCacheException;

public class ChangeDirCommand extends Command {
    public static final String COMMAND_WORD = "cd";
    private final String dirName;

    public ChangeDirCommand(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public String execute() throws Exceptions.InvalidInputException, IOException, LoadCacheException {
        this.storage.changeCache(this.dirName, this.taskList);
        return "Switched to directory " + dirName;
    }
}
