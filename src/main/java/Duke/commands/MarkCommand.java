package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskIndexException;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class to execute MarkCommand
 */
public class MarkCommand extends Command {
    private String[] words;

    /**
     * Constructor for MarkCommand
     * @param words words of user input containing the information to execute command
     */
    public MarkCommand(String[] words) {
        super();
        this.words = words;
    }
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    /**
     * Executes the find command, searching for tasks containing the specified keyword.
     *
     * @param tasks The TaskList containing the list of tasks.
     * @param ui The UI object for displaying messages.
     * @param storage The Storage object for saving data to a file.
     * @return False indicating that the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int currentIdx = tasks.getItems().size();
        if (words.length == 1 || !isNumeric(words[1])) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx = Integer.parseInt(words[1]) - 1;
        if (taskIdx >= currentIdx || taskIdx < 0) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        ui.displayMark(tasks.markTask(taskIdx));
        storage.rewriteFile(tasks.getItems());
        return false;
    }
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int currentIdx = tasks.getItems().size();
        if (words.length == 1 || !isNumeric(words[1])) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx = Integer.parseInt(words[1]) - 1;
        if (taskIdx >= currentIdx || taskIdx < 0) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        Task markedTask = tasks.markTask(taskIdx);
        storage.rewriteFile(tasks.getItems());
        return ui.markMessage(markedTask);
    }
}
