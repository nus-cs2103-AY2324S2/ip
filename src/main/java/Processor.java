import java.util.Objects;

public class Processor {
    public static void process (String input, TaskList tasks) {
        if (Objects.equals(input, "list")) {
            tasks.printTasks();

        } else {
            tasks.addTask(input);

        }
    }
}
