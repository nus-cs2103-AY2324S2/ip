import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {

    }

    public static Command parse(String fullCommand) throws RolandException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
            return new MarkCommand(index, true);
        } else if (fullCommand.startsWith("unmark")) {
            int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
            return new MarkCommand(index, false);
        } else if (fullCommand.startsWith("delete")) {

            int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
            return new DeleteCommand(index);
        } else if (fullCommand.startsWith("todo")) {
            if (fullCommand.length() <= 5) {
                throw new RolandException("Please provide description for todo");
            }
            String description = fullCommand.substring(5,fullCommand.length());
            return new AddCommand(new ToDos(description));

        } else if (fullCommand.startsWith("deadline")) {
            try {
                if (fullCommand.length() <= 9) {
                    throw new RolandException("Please provide description for deadline");
                }
                if (fullCommand.split("/").length < 2) {
                    throw new RolandException("Please include when is the deadline by with /by <YYYY-mm-dd>");
                }
                String split[] = fullCommand.split(" /");
                String description = split[0].substring(9, split[0].length());
                String by = split[1].substring(3, split[1].length());
                LocalDate date = LocalDate.parse(by);
                return new AddCommand(new Deadlines(description, date));
            } catch (DateTimeParseException e) {
                throw new RolandException("Please include when is the deadline by with /by <YYYY-mm-dd>");
            }
        } else if (fullCommand.startsWith("event")) {
            if (fullCommand.length() <= 6) {
                throw new RolandException("Please provide description for event");
            }
            if (fullCommand.split("/").length != 3) {
                throw new RolandException("Please include when is the start and end of the event with /from <start> /to <end>");
            }
            String split[] = fullCommand.split(" /");
            String description = split[0].substring(6, split[0].length());
            String from = split[1].substring(5, split[1].length());
            String to = split[2].substring(4, split[2].length());
            return new AddCommand(new Events(description, from, to));

        } else {
                throw new RolandException("I do not understand you :(.");
        }
    }


}
