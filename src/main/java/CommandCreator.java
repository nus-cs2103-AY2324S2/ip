public class CommandCreator {
    private TaskList taskList;

    public CommandCreator(TaskList taskList) {
        this.taskList = taskList;
    }

    private int parseIndex(String command) {
        String[] tokens = command.split(" ");
        return Integer.parseInt(tokens[1]) - 1;
    }

    private String parseStringArgument(String command) {
        String[] tokens = command.split(" ");
        String argument = tokens[1];
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].startsWith("/")) {
                break;
            }
            argument += " " + tokens[i];
        }
        return argument;
    }

    private String parseNamedArgument(String command, String name) {
        String[] tokens = command.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/" + name)) {
                String argument = tokens[i + 1];
                for (int j = i + 2; j < tokens.length; j++) {
                    if (tokens[j].startsWith("/")) {
                        break;
                    }
                    argument += " " + tokens[j];
                }
                return argument;
            }
        }
        throw new IllegalArgumentException();
    }

    public Command createCommand(String command) {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand(this.taskList);
        } else if (command.startsWith("mark ")) {
            int index = parseIndex(command);
            return new MarkAsDoneCommand(index, this.taskList);
        } else if (command.startsWith("unmark")) {
            int index = parseIndex(command);
            return new UnmarkAsDoneCommand(index, this.taskList);
        } else if (command.startsWith("todo ")) {
            String description = parseStringArgument(command);
            return new AddTodoCommand(description, this.taskList);
        } else if (command.startsWith("deadline ")) {
            String description = parseStringArgument(command);
            String byDate = parseNamedArgument(command, "by");
            return new AddDeadlineCommand(description, byDate, this.taskList);
        } else if (command.startsWith("event ")) {
            String description = parseStringArgument(command);
            String fromDate = parseNamedArgument(command, "from");
            String toDate = parseNamedArgument(command, "to");
            return new AddEventCommand(description, fromDate, toDate,
                    this.taskList);
        } else {
            throw new IllegalArgumentException();
        }
    }
}