class MarkCommand extends Command {
    private final int num;

    MarkCommand(int num) {
        this.num = num;
    }

    @Override
    void execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.retrieve(num);
        if (task.isDone()) {
            System.out.println("Already done. No need to mark again.");
        } else {
            task.mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task + '\n');
            storage.editLine(num, task);
        }
    }
}
