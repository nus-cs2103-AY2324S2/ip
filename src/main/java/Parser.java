public class Parser {
    static Command parse(String fullCommand) throws ThamesException{
        String[] split = fullCommand.trim().split(" ", 2);
        String input = split[0];
        switch(input.toLowerCase()) {
        case "todo":
            return new AddCommand(new ToDo(split[1].trim()));
        case "deadline":
            String[] split1 = split[1].split("/by");
            return new AddCommand(new Deadline(split1[0].trim(), split1[1].trim()));
        case "event":
            String[] split2 = split[1].split("/from");
            String[] split3 = split2[1].split("/to");
            return new AddCommand(new Event(split2[0].trim(), split3[0].trim(), split3[1].trim()));
        case "mark":
            return new EditCommand(true, Integer.parseInt(split[1].trim()) - 1);
        case "unmark":
            return new EditCommand(false, Integer.parseInt(split[1].trim()) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(split[1].trim()) - 1);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();

        }
        throw new ThamesException("Message cannot be deciphered");
    }
}
