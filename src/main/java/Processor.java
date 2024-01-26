import java.util.Objects;

public class Processor {
    public static void greet() {
        System.out.println(Utils.getFile(FilePath.LOGO_PATH));
        Utils.printLine();
        System.out.println(Utils.getFile(FilePath.GREETING_PATH));
    }
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

    public static void exit() {
        Utils.encaseLines(Utils.getFile(FilePath.EXIT_PATH));
    }
}
