class ExitCommand extends Command {
    @Override
    void execute(Storage storage, TaskList taskList) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    boolean isExit() {
        return true;
    }
}
