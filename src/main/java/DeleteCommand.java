import java.util.List;

public class DeleteCommand extends Command<List<Task>> {
    public DeleteCommand(List<String> arguments) {
        super("delete", arguments);
    }

    @Override
    List<Task> execute(List<Task> tasks) throws CoatException {
        try {
            if (arguments.isEmpty()) {
                throw new CoatException("Hey, you need to tell me which one to delete! Try 'delete <NUMBER>'. ~(>_<)\n");
            }

            int index = Integer.parseInt(arguments.get(0)) - 1;

            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.printf("\nDeleted task %d ~(^-^)\n ", index + 1);
            } else {
                throw new CoatException(String.format("I can't do that.. Task index %s is out of range! ~(T_T)\n", arguments.get(0)));
            }
        } catch (CoatException e) {
            System.out.printf("%s", e.getMessage());
            return tasks;
        } catch (NumberFormatException e) {
            System.out.printf("Sigh.. That's not a valid number! Try 'delete <NUMBER>'.\n");
            return tasks;
        }

        return tasks;
    }
}
