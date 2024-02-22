package command;
import sky.Storage;
import sky.Ui;
import task.TaskList;

/**
 * Represents a command to archive the tasks.
 */
public class ArchiveCommand extends Command {
    /**
     * Executes the command to archive the tasks.
     * @param tasks Task list to archive the tasks from.
     * @param ui Ui to show the archive message to.
     * @return The archive message to be shown to the user.
     * @throws Exception If an error occurs during the execution of the command.
     */
    public String execute(TaskList tasks, Ui ui) throws Exception {
        Storage storage = new Storage();
        String archiveName = "SkyArchive_" + java.time.LocalDate.now() + ".txt";
        storage.saveData(tasks, archiveName);
        return ui.showArchiveMessage(archiveName);
    }
}
