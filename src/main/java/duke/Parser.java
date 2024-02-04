package duke;
public class Parser {

    public static int parseNum(String input) throws DukeException {
        String[] items = input.split(" ");
        if (items.length == 1) {
            throw new DukeException("Oops! Please state the task number.", true);
        } else {
            return Integer.parseInt(input.split(" ")[1]);
        }
    }

    public static String parseTodo(String input) throws DukeException{
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("todo");
        }
        return items[1];
    }

    public static String[] parseDeadline(String input) throws DukeException{
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("deadline");
        }
        String[] parts = input.split("/by ");
        parts[0] = parts[0].replaceFirst("deadline ", "");
        return parts;
    }

    public static String[] parseEvent(String input) throws DukeException{
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("event");
        }
        try {
            String[] helper = input.split("/from ");
            String[] parts = new String[3];
            parts[0] = helper[0].replaceFirst("event ", ""); //task
            parts[1] = helper[1].split("/to ")[0]; //from
            parts[2] = helper[1].split("/to ")[1]; //to
            return parts;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please state the details like this: event event_name /from timing /to timing.", true);
        }

    }


}
