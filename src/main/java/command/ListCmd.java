package command;

public class ListCmd extends Command {
    public ListCmd() {}
    public void execute() {
        ui.printTasks();
    }
}
