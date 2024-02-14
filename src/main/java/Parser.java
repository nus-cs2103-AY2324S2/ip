import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static LocalDateTime formatDateTime(String input) {
        UI ui = new UI();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            ui.printErrInvalidDate();
        }
        throw new IllegalArgumentException("Unrecognized date and time format: " + input);
    }
    public static Command parse(String input) throws IOException {
            String[] inputArr = input.split(" ");
            String keyword = inputArr[0];
            Command output;
            switch (keyword) {
                case "log":
                    output = new LogCommand();
                    break;
                case "done":
                    output = new DoneCommand(inputArr[1]);
                    break;
                case "redo":
                    output = new RedoCommand(inputArr[1]);
                    break;
                case "delete":
                    output = new DeleteCommand(inputArr[1]);
                    break;
                case "find":
                    output = new FindCommand(inputArr[1]);
                    break;
                case "todo":
                    String toDoFormat = "^todo\\s+(\\S+(\\s+\\w+)*)$";
                    Pattern toDoPattern = Pattern.compile(toDoFormat);
                    Matcher toDoMatcher = toDoPattern.matcher(input);

                    if (toDoMatcher.matches()) {
                        String desc = toDoMatcher.group(1);
                        output = new ToDoCommand(desc);
                    }
                    output = new DefaultCommand(1);
                    break;

                case "due":
                    String dueFormat = "^due\\s+(\\w+(\\s+\\w+)*)\\s+/by\\s+(\\S+(\\s+\\w+|/)*)$";
                    Pattern duePattern = Pattern.compile(dueFormat);
                    Matcher dueMatcher = duePattern.matcher(input);

                    if (dueMatcher.matches()) {
                        String desc = dueMatcher.group(1);
                        String by = dueMatcher.group(3);

                        LocalDateTime byDateTime = formatDateTime(by);

                        output = new DueCommand(desc, byDateTime);
                    }
                    output = new DefaultCommand(1);
                    break;

                case "event":
                    String eventFormat = "^event\\s+(\\w+(\\s+\\w+)*)\\s+/from\\s+(\\S+(\\s+\\w+|/)*)\\s+/to\\s+(\\S+(\\s+\\w+|/)*)$";
                    Pattern eventPattern = Pattern.compile(eventFormat);
                    Matcher eventMatcher = eventPattern.matcher(input);

                    if (eventMatcher.matches()) {
                        String desc = eventMatcher.group(1);
                        String start = eventMatcher.group(3);
                        String end = eventMatcher.group(5);

                        LocalDateTime startDateTime = formatDateTime(start);
                        LocalDateTime endDateTime = formatDateTime(end);

                        output = new EventCommand(desc, startDateTime, endDateTime);
                    }
                    output = new DefaultCommand(1);
                    break;

                default:
                    output = new DefaultCommand(0);
                    break;
            }
            return output;
    }

}
