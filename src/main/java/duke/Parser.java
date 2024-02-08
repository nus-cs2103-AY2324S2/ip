package duke;

import exceptions.DukeException;
import exceptions.DukeUnknownTaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {

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

    public static int parseMark(String cmd) {
        return Integer.parseInt(cmd.substring(5)) - 1;
    }

    public static int parseUnmark(String cmd) {
        return Integer.parseInt(cmd.substring(7)) - 1;
    }

    public static int parseDelete(String cmd) {
        return Integer.parseInt(cmd.substring(7)) - 1;
    }

    public static String parseToDoOrFind(String cmd) {
        return cmd.substring(4).strip();
    }

    public static String parseDeadline(String cmd) {
        return cmd.substring(8).strip();
    }

    public static String parseDeadlineDesc(String cmd) {
        String[] splitStr = cmd.split(" /by ");
        return splitStr[0].strip();
    }

    public static String parseEventTest(String cmd) {
        return cmd.substring(5).strip();
    }

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
