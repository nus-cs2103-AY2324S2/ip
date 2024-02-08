package main.java.emis.command;
import main.java.emis.*;
import main.java.emis.exceptions.EmisException;

public class UnmarkCommand extends Command {
    private int taskNo;

    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        try {
            t.markAsUndone(this.taskNo);
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