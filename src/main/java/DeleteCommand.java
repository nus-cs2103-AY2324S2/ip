public class DeleteCommand extends Command {
    private final int positionToDelete;

    DeleteCommand(int positionToDelete) {
        this.positionToDelete = positionToDelete;
    }
    public void execute(TaskList tasks, Ui ui) throws DookException {
        Task toDelete;
        try {
            toDelete = tasks.get(positionToDelete - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to delete :(");
            } else {
                err = new DookException(String.format("Nooo! " +
                                "You have %d tasks!" +
                                " Valid inputs for delete is in the range [0 - %d]",
                        tasks.size(), tasks.size()));
            }
            throw err;
        }
        System.out.println("Oki! Bye Bye task!");
        tasks.remove(positionToDelete - 1);
        System.out.println("You deleted this task :(");
        System.out.println(toDelete);
    }
}
