import java.time.LocalDateTime;
import java.util.Arrays;

public class Parser {

  public static Duke.Command getCommand(String[] arr) throws InvalidTaskException {
    switch (arr[0]) {
      case "bye":
        return Duke.Command.BYE;
      case "todo":
        return Duke.Command.TODO;
      case "event":
        return Duke.Command.EVENT;
      case "deadline":
        return Duke.Command.DEADLINE;
      case "list":
        return Duke.Command.LIST;
      case "unmark":
        return Duke.Command.UNMARK;
      case "mark":
        return Duke.Command.MARK;
      case "delete":
        return Duke.Command.DELETE;
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
    String task_desc;
    switch(descriptionArray[0]) {
      case "todo":
        task_desc = String.join(" ",
          Arrays.copyOfRange(descriptionArray, 1, descriptionArray.length));
        ret[0] = task_desc;
        break;
      case "event":
        Integer start_idx = -1; Integer end_idx = -1;
        for (int i = 0; i < descriptionArray.length; i++) {
          if (descriptionArray[i].equals("/from")) {
            start_idx = i;
          }
          if (descriptionArray[i].equals("/to")) {
            end_idx = i;
          }
        }
        if (start_idx.equals(-1) || end_idx.equals(-1)) { // we cannot find start or end.
          throw new InvalidParametersException();
        }
        task_desc = String.join(" ",
          Arrays.copyOfRange(descriptionArray, 1, start_idx));
        String start = String.join(" ",
          Arrays.copyOfRange(descriptionArray, start_idx + 1, end_idx));
        String end = String.join(" ",
          Arrays.copyOfRange(descriptionArray, end_idx + 1, descriptionArray.length));
        ret[0] = task_desc; ret[1] = start; ret[2] = end;
        break;
      case "deadline":
        start_idx = -1;
        for (int i = 0; i < descriptionArray.length; i++) {
          if (descriptionArray[i].equals("/by")) { // we cannot find by event.
            start_idx = i;
          }
        }
        if (start_idx.equals(-1)) {
          throw new InvalidParametersException();
        }
        task_desc = String.join(" ",
          Arrays.copyOfRange(descriptionArray, 1, start_idx));
        start = String.join(" ",
          Arrays.copyOfRange(descriptionArray, start_idx + 1, descriptionArray.length));
        ret[0] = task_desc; ret[1] = start;
        break;
    }
    return ret;
  }

  public static LocalDateTime parseDate(String potential_date) throws InvalidDateTimeException {
    String time; Integer year; Integer month; Integer day;

    try {
      String[] dd = potential_date.split(" ");
      String[] date_arr = dd[0].split("-");
      time = dd[1];
      year = Integer.parseInt(date_arr[0]); month = Integer.parseInt(date_arr[1]);
      day = Integer.parseInt(date_arr[2]);
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
