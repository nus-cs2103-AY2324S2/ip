package command;

public class ListCmd extends Command {
    public ListCmd() {}
    @Override
    public void execute() {
        ui.printTasks();
    }
}
