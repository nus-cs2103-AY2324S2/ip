package duke;

class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, boolean silent) {
        if (!silent){
            System.out.println("Bye. Hope to see you again soon!");
        }
        System.exit(0);
    }
}
