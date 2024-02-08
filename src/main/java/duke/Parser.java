package duke;

import exceptions.DukeException;
import exceptions.DukeUnknownTaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the commands entered by the user.
     * @param sc Scanner
     * @param ui ui
     * @param tl TaskList
     * @param st storage
     * @throws DukeException if the tasks are invalid
     */
    public static void parse(Scanner sc, Ui ui, TaskList tl, Storage st) throws DukeException {
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                ui.printList(tl);
            } else if (cmd.startsWith("mark")) {
                ui.printMark(tl, cmd);
            } else if (cmd.startsWith("unmark")) {
               ui.printUnmark(tl, cmd);
            } else if (cmd.startsWith("delete")) {
                ui.printDelete(tl, cmd);
            } else if (cmd.startsWith("todo")) {
                ui.printToDo(tl, cmd);
            } else if (cmd.startsWith("deadline")) {
                ui.printDeadline(tl, cmd);
            } else if (cmd.startsWith("event")) {
                ui.printEvent(tl, cmd);
            } else if (cmd.startsWith("find")) {
                ui.printFind(tl, cmd);
            } else {
                throw new DukeUnknownTaskException();
            }
            String store_str = st.storeList(tl);
            st.save(store_str);
            ui.breakLines();
            cmd = sc.nextLine();
        }
    }

    /**
     * Returns the index of the taskList to mark as done.
     * @param cmd
     * @return int index
     */
    public static int parseMark(String cmd) {
        return Integer.parseInt(cmd.substring(5)) - 1;
    }

    /**
     * Returns the index of the taskList to mark as undone.
     * @param cmd
     * @return int index
     */
    public static int parseUnmark(String cmd) {
        return Integer.parseInt(cmd.substring(7)) - 1;
    }

    /**
     * Returns the index of the taskList to delete.
     * @param cmd
     * @return int index
     */
    public static int parseDelete(String cmd) {
        return Integer.parseInt(cmd.substring(7)) - 1;
    }

    /**
     * Returns the String of the description after "todo" or "find".
     * @param cmd
     * @return String desc
     */
    public static String parseToDoOrFind(String cmd) {
        return cmd.substring(4).strip();
    }

    /**
     * Returns the String of the description and date-time after "deadline".
     * @param cmd
     * @return String desc and date-time
     */
    public static String parseDeadline(String cmd) {
        return cmd.substring(8).strip();
    }

    /**
     * Returns the String of the description after "deadline".
     * @param cmd
     * @return String desc
     */
    public static String parseDeadlineDesc(String cmd) {
        String[] splitStr = cmd.split(" /by ");
        return splitStr[0].strip();
    }

    /**
     * Returns the String of the description and duration after "event".
     * @param cmd
     * @return String desc and duration
     */
    public static String parseEventTest(String cmd) {
        return cmd.substring(5).strip();
    }

    /**
     * Returns the String[] of the description and duration after "event" with no beginning empty space.
     * @param cmd
     * @return String[]
     */
    public static String[] parseEvent(String cmd) {
        String s = cmd.substring(6);
        String[] ans = new String[3];
        String[] splitStr = s.split(" /from ");
        String[] splitStr2 = splitStr[1].split(" /to ");
        ans[0] = splitStr[0].strip();
        ans[1] = splitStr2[0].strip();
        ans[2] = splitStr2[1].strip();
        return ans;
    }

    /**
     * Returns a LocalDateTime of the date-time after "deadline" with no beginning empty space.
     * @param cmd
     * @return LocalDateTime
     */
    public static LocalDateTime parseDeadlineBy(String cmd) {
        String[] splitStr = cmd.split(" /by ");
        String unformattedBy = splitStr[1].strip();
        String reformattedBy = unformattedBy.replace("/", "-");
        String[] splitDateTime = reformattedBy.split(" ");
        String formattedTime = splitDateTime[1].strip().replaceAll("(\\d{2})(\\d{2})", "$1:$2");
        String formattedDeadline = splitDateTime[0].strip() + " " + formattedTime;
        return LocalDateTime.parse(formattedDeadline, DateTimeFormatter.ofPattern("d-M-uuuu HH:mm"));
    }
}
