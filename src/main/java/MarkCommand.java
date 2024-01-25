import java.util.List;

class MarkCommand extends Command<List<Task>> {
    public MarkCommand(List<String> arguments) {
        super("mark", arguments);
    }

    @Override
    List<Task> execute(List<Task> tasks) {
        for (String arg : arguments) {
            int index = Integer.parseInt(arg) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsDone();
            }
        }
        System.out.printf("\n(^-^)~~   Marked tasks as done!\n");
        return tasks;
    }
}
