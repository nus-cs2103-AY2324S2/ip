package lamball.command;

/**
 * Command task that will be told to run.
 */
public abstract class Command {

    /**
     * Runs the command.
     *
     * @return Boolean determining if the chatbot should be terminated.
     */
    public abstract boolean run();
}
