package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Command of marking/unmarking a task in a list.
 */
public class ToggleMarkTaskCommand extends Command {
    private int index;

    /**
     * Initializes a Command to mark/unmkark a task on the given index depending on the given type.
     *
     * @param type the type of the Command which can be mark or unmark.
     * @param index the index of the task to be marked
     */
    public ToggleMarkTaskCommand(Parser.Cmd type, int index) {
        super(type);
        this.index = index;
    }

    /**
     * Marks/Unmarks the task on the index field in the given list.
     *
     * @param taskList the given taskList to mark/unmark the task.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        if (this.index > taskList.getSize() || this.index <= 0) {
            ui.informIndexOutofBound();
            return;
        }
        if (this.type == Parser.Cmd.mark) {
            ui.informListMarked(taskList.markList(this.index));
        } else {
            taskList.unmarkList(this.index);
            ui.informListUnmarked(taskList.unmarkList(this.index));
        }
    }
}
