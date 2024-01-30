package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {

    }

    public String parseCmd(String cmd) {
        if (cmd.equals("bye") || cmd.equals("list")){
            return cmd;
        }

        String[] split = cmd.split(" ");
        return split[0];
    }

    public String[] parseDelete(String cmd) throws DukeException {
        String[] str = cmd.split(" ");

        if (str.length < 2) {
            throw new DukeException("Missing params for delete!");
        } else if (str.length > 2) {
            throw new DukeException("Too many params for delete!");
        }

        try {
            Integer.parseInt(str[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number given.");
        }

        return str;
    }

    public String[] parseMark(String cmd) throws DukeException {
        String[] str = cmd.split(" ");

        if (str.length != 2) {
            throw new DukeException("Incorrect number of params for mark/unmark");
        }

        try {
            Integer.parseInt(str[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number given.");
        }

        return str;
    }

    public String[] parseAdd(String cmd) throws DukeException {
        String[] str = cmd.split(" ", 2);
        String[] res = new String[0];
        String desc, from, to;
        LocalDate by;

        if (str[0].equals("todo")) {
            if (str.length != 2) {
                throw new DukeException("Missing params for todo!");
            }
            desc = str[1].replaceAll("\\s", "");
            res = new String[] {str[0], desc};
        } else if (str[0].equals("deadline")) {
            if (str.length != 2) {
                throw new DukeException("Missing params for deadline!");
            }

            String[] temp = str[1].split("/by");

            if (temp.length != 2) {
                throw new DukeException("Missing deadline for deadline!");
            }

            desc = temp[0].replaceAll("\\s", "");
            by = validDate(temp[1].replaceAll("\\s", ""));
            res = new String[] {str[0], desc, by.toString()};
        } else {
            if (str.length != 2) {
                throw new DukeException("Missing params for event!");
            }

            String[] temp = str[1].split("/from");

            if (temp.length != 2) {
                throw new DukeException("Missing [from] and [to] for event!");
            }

            desc = str[1].split("/from")[0].replaceAll("\\s", "");

            String[] temp2 = str[1].split("/from")[1].split("/to");

            if (temp2.length != 2) {
                throw new DukeException("Missing [to] for event!");
            }

            from = temp2[0].replaceAll("\\s", "");
            to = temp2[1].replaceAll("\\s", "");

            res = new String[]{str[0], desc, from, to};
        }

        return res;
    }

    public LocalDate validDate(String str) throws DukeException {
        LocalDate ld;

        try {
            ld = LocalDate.parse(str);
        } catch (DateTimeParseException dt) {
            throw new DukeException("Invalid date format. Use yyyy-MM-dd format.");
        }

        return ld;
    }

}
