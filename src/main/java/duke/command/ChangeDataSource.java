package duke.command;

import duke.Storage;
import duke.TaskList;

import java.io.FileNotFoundException;

public class ChangeDataSource implements Command{
    private boolean status = true;
    private String filePath;

    public ChangeDataSource(String filePath, Storage storage, TaskList tasks) {
        this.filePath = filePath;
        storage.resetStorage(filePath);
        try {
            tasks.resetTaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.status = false;
        }
    }

    @Override
    public String reply() {
        if (status) {
            return String.format("Dear sir, your data source has been changed to %s\n.", this.filePath);
        }
        return "Sorry sir, File not found";
    }
}
