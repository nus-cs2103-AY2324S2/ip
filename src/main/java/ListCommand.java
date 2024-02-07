class ListCommand extends Command {

    @Override
    void execute(Storage storage, TaskList taskList) {
        taskList.print();
    }
}
