package command;

public class ByeCmd extends Command{
    @Override
    public String execute() {
        response = ui.bye();
        return response;
    }

    public ByeCmd() {
        isBye = true;
    }
}
