package main.java.emis.command;
import main.java.emis.*;

public class PrintCommand extends Command {
    public PrintCommand() {
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        t.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}