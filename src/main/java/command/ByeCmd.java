package command;

public class ByeCmd extends Command{
    @Override
    public void execute() {
        ui.bye();
    }

    public ByeCmd() {
        isBye = true;
    }
}
