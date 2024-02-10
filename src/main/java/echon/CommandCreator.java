package echon;

/**
 * Creates a command object from a command string.
 */
public class CommandCreator {
    private TaskList taskList;

    public CommandCreator(TaskList taskList) {
        this.taskList = taskList;
    }

    private int parseIndex(String command) throws EchonException {
        String[] tokens = command.split(" ");
        if (tokens.length == 1) {
            throw new EchonException("OOPS!!! The index of a " + tokens[0]
                    + " cannot be empty.");
        }
        try {
            return Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new EchonException("OOPS!!! The index of a " + tokens[0]
                    + " must be a number.");
        }
    }

    private String parseStringArgument(String command) throws EchonException {
        String[] tokens = command.split(" ");
        if (tokens.length == 1) {
            throw new EchonException("OOPS!!! The description of a " + tokens[0]
                    + " cannot be empty.");
        }
        String argument = tokens[1];
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].startsWith("/")) {
                break;
            }
            argument += " " + tokens[i];
        }
        return argument;
    }

    private int parseIndexArgument(String command, String name) throws EchonException {
        String[] tokens = command.split(" ");
        for (int i = 0; i < tokens.length - 1; i++) {
            if (tokens[i].equals("/" + name)) {
                return Integer.parseInt(tokens[i + 1]) - 1;
            }
        }
        throw new EchonException("OOPS!!! The argument /" + name + " is missing.");
    }

    private String parseNamedArgument(String command, String name) throws EchonException {
        String[] tokens = command.split(" ");
        for (int i = 0; i < tokens.length - 1; i++) {
            if (!tokens[i].equals("/" + name)) {
                continue;
            }
            String argument = tokens[i + 1];
            for (int j = i + 2; j < tokens.length; j++) {
                if (tokens[j].startsWith("/")) {
                    break;
                }
                argument += " " + tokens[j];
            }
            return argument;
        }
        throw new EchonException("OOPS!!! The argument /" + name + " is missing.");
    }

    private Boolean parseBooleanArgument(String command, String name) {
        String[] tokens = command.split(" ");
        for (int i = 0; i < tokens.length - 1; i++) {
            if (tokens[i].equals("/" + name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a command object from a command string.
     *
     * @param command The command string.
     * @return The command object.
     * @throws EchonException If the command string is invalid.
     */
    public Command createCommand(String command) throws EchonException {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand(this.taskList);
        } else if (command.startsWith("mark")) {
            int index = parseIndex(command);
            return new MarkAsDoneCommand(index, this.taskList);
        } else if (command.startsWith("unmark")) {
            int index = parseIndex(command);
            return new UnmarkAsDoneCommand(index, this.taskList);
        } else if (command.startsWith("todo")) {
            String description = parseStringArgument(command);
            return new AddTodoCommand(description, this.taskList);
        } else if (command.startsWith("deadline")) {
            String description = parseStringArgument(command);
            String dueDate = parseNamedArgument(command, "by");
            return new AddDeadlineCommand(description, dueDate, this.taskList);
        } else if (command.startsWith("event")) {
            String description = parseStringArgument(command);
            String fromDate = parseNamedArgument(command, "from");
            String toDate = parseNamedArgument(command, "to");
            Boolean isTentative = parseBooleanArgument(command, "tentative");
            return new AddEventCommand(description, fromDate, toDate, isTentative,
                    this.taskList);
        } else if (command.startsWith("delete")) {
            int index = parseIndex(command);
            return new DeleteTaskCommand(index, this.taskList);
        } else if (command.startsWith("find")) {
            String keyword = parseStringArgument(command);
            return new FindTaskCommand(keyword, this.taskList);
        } else if (command.startsWith("add-slot")) {
            int index = parseIndex(command);
            String fromDate = parseNamedArgument(command, "from");
            String toDate = parseNamedArgument(command, "to");
            return new AddTentativeSlotCommand(index, fromDate, toDate, this.taskList);
        } else if (command.startsWith("confirm-slot")) {
            int index = parseIndex(command);
            int slotIndex = parseIndexArgument(command, "slot");
            return new ConfirmTentativeSlotCommand(index, slotIndex, this.taskList);
        } else {
            throw new EchonException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
