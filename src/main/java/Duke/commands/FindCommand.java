package Duke.commands;

import Duke.util.Storage;
import Duke.util.TaskList;
import Duke.util.UI;

public class FindCommand extends Commands {
    private String[] words;
    public FindCommand(String[] s) {
        super();
        this.words = s;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage s) {
        ui.displayFoundTask(tasks.findTasksWithString(words[1].trim()));
        return false;
    }
}
