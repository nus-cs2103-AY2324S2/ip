public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] split = input.split(" ", 2);
        String method = split[0];

        switch (method) {
            case "list": {
                return new ListCommand();
            }
            case "mark": {
                String params = split[1];
                if (params.equals("")) {
                    throw new DukeException("The id of a mark cannot be empty.");
                }
                return new MarkCommand(Integer.parseInt(split[1]));
            }
            case "unmark": {
                String params = split[1];
                if (params.equals("")) {
                    throw new DukeException("The id of a unmark cannot be empty.");
                }
                return new UnmarkCommand(Integer.parseInt(split[1]));
            }

            case "todo": {
                String params = split[1];
                if (params.equals("")) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                return new TodoCommand(params);
            }

            case "deadline": {
                String params = split[1];
                if (params.equals("")) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                String[] split1 = params.split(" /by ", 2);
                if (split1.length == 1) {
                    throw new DukeException("The deadline of a deadline cannot be empty.");
                }
                System.out.println(split1[1].strip());
                return new DeadlineCommand(split1[0], Utils.parseDate(split1[1].strip()));
            }

            case "event": {
                String params = split[1];
                if (params.equals("")) {
                    throw new DukeException("The description of a event cannot be empty.");
                }
                String[] split1 = params.split(" /from ", 2);
                if (split1.length == 1) {
                    throw new DukeException("The from of a event cannot be empty.");
                }
                String[] split2 = split1[1].split(" /to ", 2);
                if (split2.length == 1) {
                    throw new DukeException("The to of a event cannot be empty.");
                }
                return new EventCommand(split1[0], Utils.parseDate(split2[0]), Utils.parseDate(split2[1]));

            }

            case "delete": {
                String params = split[1];
                if (params.equals("")) {
                    throw new DukeException("The id of a delete cannot be empty.");
                }
                return new DeleteCommand(Integer.parseInt(params) - 1);
            }

            case "bye": {
                return new ByeCommand();
            }
            case "help":
            default: {
                return new HelpCommand();
            }
        }
    }
}
