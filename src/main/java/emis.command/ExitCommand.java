package main.java.emis.command;
import main.java.emis.*;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        Ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}