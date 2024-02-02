package duke.command;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;


public class FindCommand extends Command{
    private String findWord;

    public FindCommand(String findWord) {
        this.findWord = findWord;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.findTasks(findWord, tasks.getTasks());
    }
}
