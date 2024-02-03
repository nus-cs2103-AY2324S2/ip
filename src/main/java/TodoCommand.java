public class TodoCommand extends Command {
    private Todo todo;

    public TodoCommand (Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(Ui ui) {
        super.taskList.addTask(this.todo);
        ui.printToUser("\tGot it. I've added this task:",
                        "\t" + this.todo,
                        super.taskList.getListCounter());
    }
}