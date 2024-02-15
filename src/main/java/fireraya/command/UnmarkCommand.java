package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;

public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        if (tasks.size() <= index || index < 0) {
            throw new FirerayaException("That task does not exist!");
        }


        tasks.unmark(index);
        String a = ui.print("Unmarking task:\n");
        String b = ui.listTaskAt(tasks.getTasks(), index);
        storage.saveToFile(tasks.getTasks());
        return a + b;
    }

}
