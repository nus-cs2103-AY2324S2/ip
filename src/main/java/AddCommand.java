import java.util.List;

public class AddCommand extends Command<List<Task>> {
    private List<String> arguments;

    public AddCommand(List<String> arguments) {
        super("add", arguments);
        this.arguments = arguments;
    }

    @Override
    public List<Task> execute(List<Task> tasks) {
        String taskDescription = String.join(" ", arguments);
        tasks.add(new Task(taskDescription));
        System.out.printf("\n(^-^)~~   Added: %s\n", taskDescription);
        return tasks;
    }
}
