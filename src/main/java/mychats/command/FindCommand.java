package duke.command;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command to find tasks containing a given word.
 */
public class FindCommand extends Command{

    private String findWord;

    /**
     * Constructs a FindCommand instance with the given word to search for.
     *
     * @param findWord Word to search for in tasks.
     */
    public FindCommand(String findWord) {
        this.findWord = findWord;
    }

    /**
     * Executes FindCommand to search for tasks containing the given word.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTasks(findWord, tasks.getTasks());
    }
}
