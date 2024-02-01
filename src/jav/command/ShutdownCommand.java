package jav.command;

/**
 * ShutdownCommand handles the shutdown of the program.
 */
public class ShutdownCommand extends Command {

    @Override
    public void Execute() {
        Jav.Exit();
    }

}
