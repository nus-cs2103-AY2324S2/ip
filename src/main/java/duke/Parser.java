package duke;
public class Parser {

    public static int parseNum(String input) throws DukeException {
        String[] items = input.split(" ");
            if (items.length == 1) {
                throw new DukeException("Oops! Please state which task number you want to mark done!");
            }
        return Integer.parseInt(input.split(" ")[1]);
    }

    public static String parseTodo(String input) throws DukeException{
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("deadline");
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
        String[] parts = input.split("/from ");
        parts[0] = parts[0].replaceFirst("event ", ""); //task
        parts[1] = parts[1].split("/to")[0]; //from
        parts[2] = parts[1].split("/to")[1]; //to
        return parts;
    }


}
