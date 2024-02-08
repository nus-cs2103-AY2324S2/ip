package main.java.emis.command;
import main.java.emis.exceptions.EmisException;
import main.java.emis.*;

public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        try {
            t.deleteTask(this.taskNo);
            s.updateStorage();
        } catch (EmisException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}