package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IllegalParamException;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command{
    private int id;
    public MarkCommand(int id) {
        this.id = id;
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IllegalParamException {
        try {
            Task done = list.getTask(id);
            done.setDone();
            storage.save(list);
            ui.showMessage("Thats sick! Great work, marked as done!\n" + done);
        } catch (IllegalParamException e) {
            throw new IllegalParamException(e.getMessage() + " Unable to mark duke.command.task!");
        }
    }
}
