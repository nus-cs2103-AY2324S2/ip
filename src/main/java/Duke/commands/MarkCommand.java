package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
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
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("mark");
        }
        String markIdx = words[1].trim();
        int currentIdx = tasks.getNumberOfTasks();
        if (!isNumeric(markIdx)) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx = Integer.parseInt(words[1]) - 1;
        boolean hasInvalidIndex = taskIdx >= currentIdx || taskIdx < 0;
        if (hasInvalidIndex) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        Task markedTask = tasks.markTask(taskIdx);
        storage.rewriteFile(tasks.getTasks());
        return ui.markMessage(markedTask);
    }
}
