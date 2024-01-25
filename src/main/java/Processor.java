import java.util.Objects;

public class Processor {
    public static void process (String input, TaskList tasks) {
        if (Objects.equals(input, "list")) {
            tasks.printTasks();

        } else if (input.contains("mark")) {
            int i = Integer.parseInt(input.split("\\s+")[1]) - 1;
            Task task = tasks.getTask(i);

            if (input.startsWith("mark")) {
                task.mark();

            } else if (input.startsWith("unmark")) {
                task.unmark();

            }

        } else {
            tasks.addTask(input);

        }
    }
}
