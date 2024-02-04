package duke;
public class Parser {
    public String[] parse(String input) {
        return input.split(" ", 2);
    }

    public String parseCommand(String input) {
        return input.split(" ", 2)[0];
    }

    public int parseIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }

    public String parseDescription(String input) {
        return input.split(" ", 2)[1];
    }

    public String[] parseDeadline(String input) {
        // Remove the deadline in the beginning, then split by /by
        return input.split("deadline ")[1].split(" /by ");
    }

    public String[] parseEvent(String input) {
        String[] split = input.split(" /");
        String description = split[0].split("event ")[1];
        String from = split[1].split("from ")[1];
        String to = split[2].split("to ")[1];
        return new String[] { description, from, to };
    }
}
