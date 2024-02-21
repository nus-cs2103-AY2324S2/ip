package jojo;

import exceptions.JojoException;
import exceptions.JojoUnknownTaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the commands entered by the user.
     * @param cmd String
     * @param ui ui
     * @param tl TaskList
     * @throws JojoException if the tasks are invalid
     */
    public static String parse(String cmd, Ui ui, TaskList tl) throws JojoException {
        String response;
        if (cmd.strip().equals("list")) {
            response = ui.ListToString(tl);
        } else if (cmd.startsWith("mark")) {
            response = ui.MarkToString(tl, cmd);
        } else if (cmd.startsWith("unmark")) {
            response = ui.UnmarkToString(tl, cmd);
        } else if (cmd.startsWith("delete")) {
            response = ui.DeleteToString(tl, cmd);
        } else if (cmd.startsWith("todo")) {
            response = ui.ToDoToString(tl, cmd);
        } else if (cmd.startsWith("deadline")) {
            response = ui.DeadlineToString(tl, cmd);
        } else if (cmd.startsWith("event")) {
            response = ui.EventToString(tl, cmd);
        } else if (cmd.startsWith("find")) {
            response = ui.FindToString(tl, cmd);
        } else {
            throw new JojoUnknownTaskException();
        }
        return response;
    }

    /**
     * Returns the index of the taskList to mark as done.
     * @param cmd String
     * @return int index
     */
    public static int parseMark(String cmd) {
        return Integer.parseInt(cmd.substring(5).strip()) - 1;
    }

    /**
     * Returns the index of the taskList to mark as undone.
     * @param cmd String
     * @return int index
     */
    public static int parseUnmark(String cmd) {
        return Integer.parseInt(cmd.substring(7).strip()) - 1;
    }

    /**
     * Returns the index of the taskList to delete.
     * @param cmd String
     * @return int index
     */
    public static int parseDelete(String cmd) {
        return Integer.parseInt(cmd.substring(7).strip()) - 1;
    }

    /**
     * Returns the String of the description after "todo" or "find".
     * @param cmd String
     * @return String desc
     */
    public static String parseToDoOrFind(String cmd) {
        return cmd.substring(4).strip();
    }

    /**
     * Returns the String of the description and date-time after "deadline".
     * @param cmd String
     * @return String desc and date-time
     */
    public static String parseDeadline(String cmd) {
        return cmd.substring(8).strip();
    }

    /**
     * Returns the String of the description after "deadline".
     * @param cmd String
     * @return String desc
     */
    public static String parseDeadlineDesc(String cmd) {
        String[] splitStr = cmd.split(" /by ");
        assert splitStr.length == 2: "length of array splitStr should be 2";
        return splitStr[0].strip();
    }

    /**
     * Returns the String of the description and duration after "event".
     * @param cmd String
     * @return String desc and duration
     */
    public static String parseEventTest(String cmd) {
        return cmd.substring(5).strip();
    }

    /**
     * Returns the String[] of the description and duration after "event" with no beginning empty space.
     * @param cmd String
     * @return String[]
     */
    public static String[] parseEvent(String cmd) {
        String s = cmd.substring(6);
        String[] ans = new String[3];
        String[] splitStr = s.split(" /from ");
        assert splitStr.length == 2: "length of array splitStr should be 2";
        String[] splitStr2 = splitStr[1].split(" /to ");
        assert splitStr2.length == 2: "length of array splitStr2 should be 2";
        ans[0] = splitStr[0].strip();
        ans[1] = splitStr2[0].strip();
        ans[2] = splitStr2[1].strip();
        return ans;
    }

    /**
     * Returns a LocalDateTime of the date-time after "deadline" with no beginning empty space.
     * @param cmd String
     * @return LocalDateTime
     */
    public static LocalDateTime parseDeadlineBy(String cmd) {
        String[] splitStr = cmd.split(" /by ");
        assert splitStr.length == 2: "length of array splitStr should be 2";
        String unformattedBy = splitStr[1].strip();
        String reformattedBy = unformattedBy.replace("/", "-");
        String[] splitDateTime = reformattedBy.split(" ");
        assert splitDateTime.length == 2: "length of array splitDateTime should be 2";
        String formattedTime = splitDateTime[1].strip().replaceAll("(\\d{2})(\\d{2})", "$1:$2");
        String formattedDeadline = splitDateTime[0].strip() + " " + formattedTime;
        return LocalDateTime.parse(formattedDeadline, DateTimeFormatter.ofPattern("d-M-uuuu HH:mm"));
    }
}
