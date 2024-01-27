package objects;

public class Processor {
    public static void greet() {
        System.out.println(Utils.getFile(FilePath.LOGO_PATH));
        Utils.encaseLines(Utils.getFile(FilePath.GREETING_PATH));
    }

    public static void process (String input, TaskList tasks) {
        input = input.trim();
        String command = Utils.getCommandType(input);

        try {
            switch (command) {
                case Commands.LIST:
                    Commands.listTasks(tasks);
                    break;

                case Commands.MARK:
                case Commands.UNMARK:
                case Commands.DELETE:
                    Commands.processTask(tasks, input);
                    break;

                case Commands.TODO:
                case Commands.DEADLINE:
                case Commands.EVENT:
                    Commands.createTask(tasks, input);
                    break;

                case Commands.HELP:
                    Commands.printHelp();
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
