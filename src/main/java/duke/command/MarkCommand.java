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

    /**
     * Constructs a MarkCommand object.
     *
     * @param input User input.
     * @param toMark Indicates mark command if true, unmark if false.
     */
    public MarkCommand(String input, boolean toMark) {
        this.input = input;
        this.toMark = toMark;
    }

    /**
     * Sets task in TaskList as done or not done depending on boolean value of toMark,
     * where index is indicated in input.
     *
     * @param list Holds the tasks added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException If index given is not within range.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String[] s = input.split("\\s");
        assert (s.length == 2) : "Mark command cannot be split at space";
        int num = Integer.parseInt(s[1]);
        if (num > list.getSize() || num < 1) {
            throw new DukeException("Task (" + num + ") not found.\n" + list.print());
        }
        Task t = list.getTask(num - 1);
        String message = markTask(ui, t);
        storage.writeToFile(list);
        return message;
    }

    private String markTask(Ui ui, Task t) {
        if (this.toMark) {
            t.markAsDone();
            return ui.showMarked(t);
        } else {
            t.markAsNotDone();
            return ui.showUnmarked(t);
        }
    }

    @Override
    public boolean equals(Object a) {
        MarkCommand mc = (MarkCommand) a;
        return (this.toMark == mc.toMark) && (this.input.equals(mc.input));
    }
}
