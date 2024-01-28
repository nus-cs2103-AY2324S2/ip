package duke;

import java.time.LocalDateTime;
import java.util.Arrays;

import duke.exceptions.HistoryIndexException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidParametersException;
import duke.exceptions.InvalidTaskException;

public class Parser {

    public static UI.Command getCommand(String[] arr) throws InvalidTaskException {
        switch (arr[0]) {
        case "bye":
            return UI.Command.BYE;
        case "todo":
            return UI.Command.TODO;
        case "event":
            return UI.Command.EVENT;
        case "deadline":
            return UI.Command.DEADLINE;
        case "list":
            return UI.Command.LIST;
        case "unmark":
            return UI.Command.UNMARK;
        case "mark":
            return UI.Command.MARK;
        case "delete":
            return UI.Command.DELETE;
        default:
            throw new InvalidTaskException();
        }
    }

    public static Integer checkIndexGiven(String s, int bounds) throws HistoryIndexException {
        Integer parsed = Integer.parseInt(s);
        if (parsed <= 0 || parsed > bounds) {
            throw new HistoryIndexException();
        }
        return parsed - 1;
    }

    public static String[] extractDescriptionData(String[] descriptionArray) throws
            InvalidInputException {
        String[] ret = new String[3];
        String taskDesc;
        switch(descriptionArray[0]) {
        case "todo":
            taskDesc = String.join(" ",
              Arrays.copyOfRange(descriptionArray, 1, descriptionArray.length));
            ret[0] = taskDesc;
            break;
        case "event":
            Integer startIdx = -1;
            Integer endIdx = -1;
            for (int i = 0; i < descriptionArray.length; i++) {
                if (descriptionArray[i].equals("/from")) {
                    startIdx = i;
                }
                if (descriptionArray[i].equals("/to")) {
                    endIdx = i;
                }
            }
            if (startIdx.equals(-1) || endIdx.equals(-1)) { // we cannot find start or end.
                throw new InvalidParametersException();
            }
            taskDesc = String.join(" ",
                Arrays.copyOfRange(descriptionArray, 1, startIdx));
            String start = String.join(" ",
                Arrays.copyOfRange(descriptionArray, startIdx + 1, endIdx));
            String end = String.join(" ",
                Arrays.copyOfRange(descriptionArray, endIdx + 1, descriptionArray.length));
            ret[0] = taskDesc;
            ret[1] = start;
            ret[2] = end;
            break;
        case "deadline":
            startIdx = -1;
            for (int i = 0; i < descriptionArray.length; i++) {
                if (descriptionArray[i].equals("/by")) { // we cannot find by event.
                    startIdx = i;
                }
            }
            if (startIdx.equals(-1)) {
                throw new InvalidParametersException();
            }
            taskDesc = String.join(" ",
              Arrays.copyOfRange(descriptionArray, 1, startIdx));
            start = String.join(" ",
              Arrays.copyOfRange(descriptionArray, startIdx + 1, descriptionArray.length));
            ret[0] = taskDesc;
            ret[1] = start;
            break;
        default:
            return ret;
        }
        return ret;
    }

    public static LocalDateTime parseDate(String potentialDate) throws InvalidDateTimeException {
        String time;
        Integer year;
        Integer month;
        Integer day;
        try {
            String[] dd = potentialDate.split(" ");
            String[] dateArr = dd[0].split("-");
            time = dd[1];
            year = Integer.parseInt(dateArr[0]);
            month = Integer.parseInt(dateArr[1]);
            day = Integer.parseInt(dateArr[2]);
        } catch (Exception e) {
            throw new InvalidDateTimeException();
        }
        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            throw new InvalidDateTimeException();
        }
    }
}
