public class GreetCommand extends Command {

    public GreetCommand() {
        super();
    }
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.greet();
    }
}
