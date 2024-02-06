/**
 * Represents an invalid command that has no effect
 */
public class InvalidCommand extends Command {
    /**
     * Creates the invalid command
     *
     * @param invalidMessage reason for invalidity
     */
    public InvalidCommand(String invalidMessage) {
        this.statusMsg = invalidMessage;
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage) {
        return; // Do nothing
    }
}
