package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to mark specified task as done.
 */
public class MarkCommand implements Command {

    private String input;
    private boolean toMark;

    public MarkCommand(String input, boolean toMark) {
        this.input = input;
        this.toMark = toMark;
    }

    /**
     * Sets task in TaskList as done or not done depending on boolean value of toMark, where index is indicated in input.
     *
     * @param list Holds the tasks added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @throws DukeException If index given is not within range.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String[] s = input.split("\\s");
        int num = Integer.parseInt(s[1]);
        if (num <= list.getSize() && num >= 1) {
            Task t = list.getTask(num - 1);
            if (this.toMark) {
                t.done();
                ui.showMarked(t);
            } else {
                t.undo();
                ui.showUnmarked(t);
            }
            storage.writeToFile(list);
        } else {
            throw new DukeException("Task (" + num + ") not found.\n" + list.print());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object a) {
        MarkCommand mc = (MarkCommand) a;
        return (this.toMark == mc.toMark) && (this.input.equals(mc.input));
    }
}
