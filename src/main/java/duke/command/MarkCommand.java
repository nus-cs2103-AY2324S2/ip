package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkCommand implements Command {

    private String input;
    private boolean toMark;

    public MarkCommand(String input, boolean toMark) {
        this.input = input;
        this.toMark = toMark;
    }

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
}
