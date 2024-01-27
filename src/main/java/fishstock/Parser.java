package fishstock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import fishstock.FishStock.Keyword;

class Parser {
    protected static DateTimeFormatter inDateFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
    protected static DateTimeFormatter outDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    protected static boolean startsWith(String keyword, String input) {
        return input.length() >= keyword.length() &&
                keyword.equals(input.substring(0, keyword.length()));
    }

    protected static Keyword parse(String input) {
        if ("bye".equals(input)) {
            return Keyword.BYE;
        } else if ("list".equals(input)) {
            return Keyword.LIST;
        } else if (startsWith("mark", input)) {
            return Keyword.MARK;
        } else if (startsWith("unmark", input)) {
            return Keyword.UNMARK;
        } else if (startsWith("delete", input)) {
            return Keyword.DELETE;
        } else if (startsWith(Todo.keyword, input)) {
            return Keyword.TODO;
        } else if (startsWith(Deadline.keyword, input)) {
            return Keyword.DEADLINE;
        } else if (startsWith(Event.keyword, input)) {
            return Keyword.EVENT;
        }
        return Keyword.INVALID;
    }

    protected static Integer getTaskFromIndex(String input) {
        try {
            int num = Integer.parseInt(input.split(" ", 2)[1]);
            return num - 1;

        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printError("OH NOSE! Task number cannot be empty..");
        } catch (NumberFormatException e) {
            Ui.printError("OH NOSE! Task number has to be an integer..");
        }
        return null;
    }

    protected static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, inDateFormat);
        } catch (DateTimeParseException e) {
            Ui.printError("OH NOSE! Dates should be of the format <dd/mm/yyyy hh:mm>");
        }
        return null;
    }

    protected static String inDate(LocalDateTime date) {
        return date.format(inDateFormat);
    }

    protected static String outDate(LocalDateTime date) {
        return date.format(outDateFormat);
    }
}
