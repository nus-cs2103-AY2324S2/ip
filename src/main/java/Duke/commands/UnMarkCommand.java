package Duke.commands;

import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;
import Duke.exceptions.DukeException;
import Duke.exceptions.InvalidTaskIndexException;
public class UnMarkCommand extends Commands {
    private String[] words;
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    public UnMarkCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int currentIdx = tasks.list().size();
        if (words.length == 1 || !isNumeric(words[1])) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx = Integer.parseInt(words[1]) - 1;
        if (taskIdx >= currentIdx || taskIdx < 0) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        ui.displayUnMark(tasks.unMarkTask(taskIdx));
        storage.rewriteFile(tasks.list());
        return false;
    }
}
