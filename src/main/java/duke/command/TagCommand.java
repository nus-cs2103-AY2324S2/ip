package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Class representing Tag commands.
 */
public class TagCommand extends Command {
    private int index;
    private String tag;

    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag.replaceAll("\\s+","");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.tag(index, tag);
        return "Ok! I've tagged this task:\n" + tasks.getTaskByIndex(index).toString();
    }
}
