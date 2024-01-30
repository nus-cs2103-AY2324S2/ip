public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return ;
    }
    @Override
    public boolean isExit() {
        System.out.println("Bye! It is an honor to serve you!");
        return true;
    }
}
