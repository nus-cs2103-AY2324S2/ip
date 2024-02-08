package gulie;

/**
 * A command that can be run.
 */
public abstract class Command {
    /**
     * Runs the command.
     * @param ui
     * @param storage
     * @param tasklist
     * @throws GulieException
     */
    public abstract void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException;
}
