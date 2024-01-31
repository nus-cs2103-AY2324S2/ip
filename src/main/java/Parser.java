import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Command parse(String text) throws DukeException {
        List<String> parts = Arrays.asList(text.split(" "));
        if (parts.isEmpty()) {
            throw new DukeException("Invalid Commands");
        }
        String header = parts.getFirst();
        Command command;
        switch(header) {
            case "list" -> {
                command = new ListCommand();
            }
            case "mark" -> {
                command = new MarkCommand(Integer.parseInt(parts.get(1)));
            }
            case "todo" -> {
                command = new AddTodoCommand(String.join("",parts));
            }
            case "deadline" -> {
                int byIndex = parts.indexOf("/by");
                if (byIndex == -1) {
                    throw new DukeException("No /by???");
                }
                if (byIndex == parts.size() - 1) {
                    throw new DukeException("Add something after your /by...");
                }
                String deadline = String.join(" ", parts.subList(byIndex + 1, parts.size()));
                String description = String.join(" ", parts.subList(0, byIndex));

                command = new AddDeadlineCommand(description,deadline);
            }
            case "event" -> {
                int fromIndex = parts.indexOf("/from");
                int toIndex = parts.indexOf("/to");
                if (fromIndex == -1) {
                    throw new DukeException("No /from???");
                }
                if (toIndex == -1) {
                    throw new DukeException("No /to???");
                }
                if (fromIndex == parts.size() - 1) {
                    throw new DukeException("Add something after your /from...");
                }
                if (toIndex == parts.size() - 1) {
                    throw new DukeException("Add something after your /to...");
                }
                if (fromIndex > toIndex) {
                    throw new DukeException("Don't throw funny funny... Mamma-Mia!");
                }
                String start = String.join(" ", parts.subList(fromIndex + 1, toIndex));
                String deadline = String.join(" ", parts.subList(toIndex + 1, parts.size()));
                String description = String.join(" ", parts.subList(0, fromIndex));

                command = new AddEventCommand(description,start,deadline);
            }
            default -> throw new DukeException("Mamma Mia! Me-no understand!");
        }
        return command;
    }
}
