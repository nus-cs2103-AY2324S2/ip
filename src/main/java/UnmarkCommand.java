import java.util.List;

class UnmarkCommand extends Command<List<Task>> {
    public UnmarkCommand(List<String> arguments) {
        super("unmark", arguments);
    }

    @Override
    List<Task> execute(List<Task> tasks) throws CoatException {
        // Assuming arguments is a list of one string
        if (!arguments.isEmpty()) {
            try {
                int index = Integer.parseInt(arguments.get(0)) - 1;
                if (index >= 0 && index < tasks.size()) {
                    Task task = tasks.get(index);
                    if (task.isDone()) {
                        task.markUndone();
                        System.out.printf("Oh no.. Marked task %d as undone: %s\n", index + 1, task.getDescription());
                    } else {
                        throw new CoatException(String.format("I can't do that.. Task %d is already undone! ~(T_T)\n", index + 1));
                    }
                } else {
                    throw new CoatException(String.format("I can't do that.. Task index %s is out of range! ~(T_T)\n", arguments.get(0)));
                }
            } catch (CoatException e) {
                System.out.printf("%s", e.getMessage());
                return tasks;
            } catch (NumberFormatException e) {
                System.out.printf("Sigh.. That's not a valid number! Try 'unmark <NUMBER>'.\n");
                return tasks;
            }
        } else {
            System.out.printf("I can't do that.. You need to specify a task index! Try \"mark <index>\".. \n");
        }

        return tasks;
    }
}
