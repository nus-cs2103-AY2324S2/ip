public class ByeCommand extends Command {

    ByeCommand() {
        this.isExit = true;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        System.out.println("Bye :(. Hope to see you again soon! ≽^- ˕ -^≼");
    }
}
