package command;

public class ExitCommand extends Command {

    @Override
    public void execute() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
