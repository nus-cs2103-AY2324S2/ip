class DeleteCommand extends Command {
    private final int num;

    DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    void execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.remove(num);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        taskList.countSize();
        storage.deleteLine(num);
    }
}
