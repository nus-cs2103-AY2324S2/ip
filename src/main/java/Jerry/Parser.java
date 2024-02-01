package Jerry;

public class Parser {
    public Command parse(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();;
        int taskIndex;

        switch (command) {
            case "bye":
                return new Command(Command.CommandType.BYE);

            case "list":
                return new Command(Command.CommandType.LIST);

            case "mark":
                taskIndex = Integer.parseInt(parts[1]) - 1;
                return new Command(Command.CommandType.MARK, taskIndex);

            case "unmark":
                taskIndex = Integer.parseInt(parts[1]) - 1;
                return new Command(Command.CommandType.UNMARK, taskIndex);

            case "delete":
                taskIndex = Integer.parseInt(parts[1]) - 1;
                return new Command(Command.CommandType.DELETE,taskIndex);

            case "todo":
                return new Command(Command.CommandType.ADD_TODO, parts[1]);

            case "deadline":

                return new Command(Command.CommandType.ADD_DEADLINE, parts[1]);

            case "event":
                return new Command(Command.CommandType.ADD_EVENT, parts[1]);

            default:
                return new Command(Command.CommandType.INVALID);
        }
    }
}
