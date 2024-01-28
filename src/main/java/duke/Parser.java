package duke;

import duke.exceptions.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Parser {

  /**
   * Parses command from array.
   *
   * @param arr The array to parse.
   * @return A correct command enum assigned to the array.
   * @throws InvalidTaskException When the command does not match any of the enums.
   */
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

  /**
   * Generates the index with respect to a zero-indexed task array.
   *
   * @param s The index in question.
   * @param bounds The bounds of the task array.
   * @return The correct index with respect to the task array.
   * @throws HistoryIndexException The index is invalid.
   */
  public static Integer checkIndexGiven(String s, int bounds) throws HistoryIndexException {
    Integer parsed = Integer.parseInt(s);
    if (parsed <= 0 || parsed > bounds) {
      throw new HistoryIndexException();
    }
    return parsed - 1;
  }

  /**
   * Extracts description data from an array.
   *
   * @param descriptionArray The array that holds description data to parse.
   * @return An array representing the extracted data from the description array.
   * @throws InvalidInputException The given input has something wrong (Parameter wise or command wise).
   */
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

  /**
   * Takes in a potential date and parses it into a LocalDateTime format.
   *
   * @param potential_date The String version of the input date.
   * @return A LocalDateTime version of the input.
   * @throws InvalidDateTimeException Thrown when there is something wrong with converting the
   *  user's input into a LocalDateTime.
   */
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
