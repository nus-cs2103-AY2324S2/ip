import java.util.List;

public class DeleteCommand extends Command<List<Task>> {
    public DeleteCommand(List<String> arguments) {
        super("delete", arguments);
    }

    @Override
    List<Task> execute(List<Task> tasks) throws CoatException {
        try {
            if (arguments.isEmpty()) {
                throw new CoatException("Error: Task index not provided for delete command. Correct format: delete <index>");
            }

            int index = Integer.parseInt(arguments.get(0)) - 1;

            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.printf("\n(^-^)~~   Deleted task %d\n", index + 1);
            } else {
                throw new CoatException("Error: Task index out of range for delete command.");
            }
        } catch (CoatException e) {
            System.out.printf("Error: Invalid task index for delete command.\n");
            return tasks;
        } catch (NumberFormatException e) {
            System.out.printf("Error: Task index not provided for delete command. Correct format: delete <index>\n");
            return tasks;
        }

        return tasks;
    }
}
