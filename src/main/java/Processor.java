import java.util.Objects;

public class Processor {
    private static final String LOGO_PATH = "./src/main/Snorlax.txt";
    private static final String GREETING_PATH = "./src/main/Greeting.txt";
    private static final String EXIT_PATH = "./src/main/Exit.txt";

    public static void greet() {
        String logo = Utils.getFile(LOGO_PATH);
        String greeting = Utils.getFile(GREETING_PATH);

        System.out.println(logo);
        Utils.printLine();
        System.out.println(greeting);
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
        String exit = Utils.getFile(EXIT_PATH);
        Utils.encaseLines(exit);
    }
}
