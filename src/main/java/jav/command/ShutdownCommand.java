package jav.command;

/**
 * ShutdownCommand handles the shutdown of the program.
 */
public class ShutdownCommand extends Command {

    @Override
    public String execute() {
        return "SHUTDOWN";
    }
}
