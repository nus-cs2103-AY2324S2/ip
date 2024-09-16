package duke;

class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        System.exit(0);
        return ""; // this is not reachable
    }
}
