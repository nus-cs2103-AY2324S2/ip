package objects;

public class Processor {
    public static void greet() {
        System.out.println(Utils.getFile(FilePath.LOGO_PATH));
        Utils.printLine();
        System.out.println(Utils.getFile(FilePath.GREETING_PATH));
    }

    public static void process (String input, TaskList tasks) {
        input = input.trim();
        String command = Utils.getCommandType(input);

        try {
            switch (command) {
                case "list":
                    tasks.listTasks();
                    break;

                case "mark":
                    tasks.markTask(Utils.getIndex(input));
                    break;

                case "unmark":
                    tasks.unmarkTask(Utils.getIndex(input));
                    break;

                case "todo":
                case "deadline":
                case "event":
                    tasks.addTask(input);
                    break;

                default:
                    throw new InvalidCommandException();
            }

        } catch (DukeException e) {
            Utils.encaseLines(e.getMessage());

        }
    }

    public static void exit() {
        Utils.encaseLines(Utils.getFile(FilePath.EXIT_PATH));
    }
}
