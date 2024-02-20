package mike.command;

import mike.MikeException;
import mike.Storage;
import mike.TaskList;

/**
 * Archives current data into another file and loads a new file to store data.
 * @author ningc
 */
public class ArchiveCommand extends Command {
    private final String fileName;

    /**
     * Constructor.
     * @param fileName Name of the archive file.
     */
    public ArchiveCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MikeException {
        storage.archive(fileName);
        taskList.clear();
        return response();
    }

    private String response() {
        return "Archive successfully created. Data moved to archive...";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "ARCHIVE " + fileName;
    }
}
