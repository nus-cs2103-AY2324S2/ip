package command;

public class ByeCmd extends Command{
    public void execute() {
        ui.bye();
    }

    public ByeCmd() {
        isBye = true;
    }
}
