package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DisplayTaggedCommand extends Command {

    private String tag;

    public DisplayTaggedCommand(String input, String tag) {
        super(input);
        this.tag = tag;
    }
    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        Task[] taggedTasks = tasks.displayTagged(tag);
        if (taggedTasks[0] != null) {
            return ui.displayTaggedTasks(taggedTasks);
        } else {
            throw new DukeException("UH OH! No tasks of this tag!");
        }
    }
}
