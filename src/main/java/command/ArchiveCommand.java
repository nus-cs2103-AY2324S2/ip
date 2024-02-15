package command;
import duke.Ui;
import duke.Storage;
import task.TaskList;

public class ArchiveCommand extends Command {
    public String execute(TaskList tasks, Ui ui) throws Exception {
        Storage storage = new Storage();
        String archiveName = "DukeArchive_" + java.time.LocalDate.now() + ".txt";
        storage.saveData(tasks, archiveName);
        return ui.showArchiveMessage(archiveName);
    }
}
