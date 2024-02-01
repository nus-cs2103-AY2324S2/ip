package commands;

public class ExitCommand implements Command {
    public void execute() {
        System.exit(0);
    }
}
