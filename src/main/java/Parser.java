public class Parser {
    public enum Commands {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        BYE,
        UNKNOWN;

        public static Commands valueOfOrElse(String command) {
            try {
                return Commands.valueOf(command);
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    public Command parseCommand(String userInput) {
        String inputs[] = userInput.split(" ", 2);
        String command = inputs[0];
        Commands cmd = Commands.valueOfOrElse(command.toUpperCase());

        switch (cmd) {

        case LIST:
            return new ListCommand();

        case MARK:
            return prepareMark(inputs);

        case UNMARK:
            return prepareUnmark(inputs);
            
        case TODO:
            return prepareTodo(inputs);
            
        case DEADLINE:
            return prepareDeadline(inputs);

        case EVENT:
            return prepareEvent(inputs);

        case DELETE:
            return prepareDelete(inputs);

        case BYE:
            return new ByeCommand();

        default:
            return new IncorrectCommand(
                new DukeException(
                    "ERROR!! The system did not recognize the command you entered."));
        }
    }

    private Command prepareMark(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new DukeException("Wrong mark command format!!");
            }

            int taskIndex = parseTaskIndex(inputs[1]);
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(new DukeException("Wrong mark command format!!"));
        } catch (DukeException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareUnmark(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new DukeException("Wrong unmark command format!!");
            }

            int taskIndex = parseTaskIndex(inputs[1]);
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(new DukeException("Wrong unmark command format!!"));
        } catch (DukeException e) {
            return new IncorrectCommand(e);
        }
    }

    private int parseTaskIndex(String input) throws DukeException {
        try {
            return Integer.parseInt(input.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task index format");
        }
    }

    private Command prepareTodo(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new DukeException("Oh no! Your todo command is "
                                        + "incomplete!!");
            }

            Todo todo = new Todo(inputs[1]);
            return new TodoCommand(todo);
        } catch (DukeException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareDeadline(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new DukeException("OOPS!!! The deadline task "
                                        + "cannot be empty.");
            }
            // separate to description: deadlineInfo[0]: return book 
            // and deadline: deadlineInfo[1]: by Sunday
            String[] deadlineInfo = inputs[1].split("/by", 2);

            if (deadlineInfo.length != 2) {
                throw new DukeException("OOPS!!! Please add "
                                        + "a valid deadline.");
            }

            Deadline deadline = new Deadline(deadlineInfo[0].strip(), 
                                            deadlineInfo[1].strip());

            return new DeadlineCommand(deadline);
        } catch (DukeException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareEvent(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new DukeException("OOPS!!! The event task "
                                        + "cannot be empty.");
            }

            String[] eventInfo = inputs[1].split("/", 3);
            if (eventInfo.length != 3) {
                throw new DukeException("OOPS!!! Please check "
                                        + "the event format again.");
            }

            Event event = new Event(eventInfo[0].strip(), 
                                    eventInfo[1].replaceFirst("from", "").strip(),
                                    eventInfo[2].replaceFirst("to", "").strip());
            return new EventCommand(event);
        } catch (DukeException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareDelete(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new DukeException("OOPS!!! The delete command "
                                        + "cannot be empty.");
            }

            int index = Integer.parseInt(inputs[1]) - 1;
            return new DeleteCommand(index);
        } catch (DukeException e) {
            return new IncorrectCommand(e);
        }
    }
}