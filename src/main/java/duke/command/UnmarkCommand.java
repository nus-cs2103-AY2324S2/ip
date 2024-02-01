package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IllegalParamException;
import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private int id;

    public UnmarkCommand(int id) {
        this.id = id;
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws IllegalParamException {
        try {
            Task notDone = list.getTask(this.id);
            notDone.setNotDone();
            storage.save(list);
            ui.showMessage("Awh why uncheck me :( Its ok, it is what it is!\n" + notDone);
        } catch (IllegalParamException e) {
            throw new IllegalParamException(e.getMessage() + " Unable to unmark duke.command.task!");
        }
    }
}