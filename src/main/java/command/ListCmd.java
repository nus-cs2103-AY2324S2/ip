package command;

public class ListCmd extends Command {
    public ListCmd() {}
    @Override
    public String execute() {
        response = ui.printTasks();
        return response;
    }
}
