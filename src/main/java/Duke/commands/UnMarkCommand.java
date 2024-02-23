package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidTaskIndexException;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class to execute UnMarkCommand
 */
public class UnMarkCommand extends Command {
    private String[] words;

    /**
     * Constructor for UnMarkCommand
     * @param words words from userInput containing information to execute
     */
    public UnMarkCommand(String[] words) {
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
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("unMark");
        }
        int currentIdx = tasks.getNumberOfTasks();
        String unMarkIdx = words[1].trim();
        if (!isNumeric(unMarkIdx)) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx = Integer.parseInt(unMarkIdx) - 1;
        boolean isInvalidIndex = taskIdx >= currentIdx || taskIdx < 0;
        if (isInvalidIndex) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        Task unMarkedTask = tasks.unMarkTask(taskIdx);
        storage.rewriteFile(tasks.getTasks());
        return ui.unMarkMessage(unMarkedTask);
    }
}
