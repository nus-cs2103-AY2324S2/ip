import java.util.Objects;

public class Processor {
    public static void process (String input, TaskList tasks) {
        if (Objects.equals(input, "list")) {
            tasks.printTasks();

        } else if (input.contains("mark")) {
            int i = Utils.getIndex(input);
            Task task = tasks.getTask(i);

            if (input.startsWith("mark")) {
                task.mark();

            } else if (input.startsWith("unmark")) {
                task.unmark();

            }

        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
            tasks.addTask(input);
        } else {
            Utils.encaseLines(input);
        }
    }
}
