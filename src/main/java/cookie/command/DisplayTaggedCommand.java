package cookie.command;

import cookie.CookieException;
import cookie.task.Task;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to display tasks tagged with a specific tag.
 * Inherits from the Command class and overrides the executeAndReply method
 * to execute the command and generate an appropriate response.
 */
public class DisplayTaggedCommand extends Command {

    private String tag;

    /**
     * Constructs a DisplayTaggedCommand object with the specified input and tag.
     *
     * @param input The input string representing the user command.
     * @param tag   The tag for which tasks are to be displayed.
     */
    public DisplayTaggedCommand(String input, String tag) {
        super(input);
        this.tag = tag;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws CookieException {
        Task[] taggedTasks = tasks.displayTagged(tag);
        if (taggedTasks[0] == null) {
            throw new CookieException("UH OH! No tasks of this tag!");
        }
        return ui.displayTaggedTasks(taggedTasks, "#" + tag);
    }
}
