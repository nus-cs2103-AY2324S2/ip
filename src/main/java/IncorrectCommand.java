public class IncorrectCommand extends Command {

    public IncorrectCommand() {
    }

    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Invalid command");
    }
}
