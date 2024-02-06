public class InvalidCommand extends Command {
    public InvalidCommand(String invalidMessage) {
        this.statusMsg = invalidMessage;
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage) {
        return; // Do nothing
    }
}
