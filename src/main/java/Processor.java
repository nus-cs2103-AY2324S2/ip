import java.util.Objects;

public class Processor {
    public static void process (String input, TaskList tasks) {
        input = input.trim();
        String command = Utils.firstWord(input);

        switch (command) {
            case "list":
                tasks.listTasks();
                break;

            case "mark":
                tasks.mark(Utils.getIndex(input));
                break;

            case "unmark":
                tasks.unmark(Utils.getIndex(input));
                break;

            case "todo":
            case "deadline":
            case "event":
                tasks.addTask(input);
                break;

            default:
                Utils.encaseLines("Invalid command");
        }
    }
}
