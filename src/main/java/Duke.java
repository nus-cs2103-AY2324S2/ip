import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    enum Command {
      BYE,
      LIST,
      EVENT,
      TODO,
      DEADLINE,
      MARK,
      UNMARK,
      DELETE
    }
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      String name = "shu heng";
      String name_display = "_________________________\n" +
        "Hello! I'm " + name + "\n" +
        "What can I do for you?\n" +
        "_________________________\n";
      System.out.println(name_display);
      String current_input = "";

      FileManager manager = new FileManager("data");
      manager.createLog();
      ArrayList<Task> history = new ArrayList<>();
      try {
        manager.loadHistory(history);
      } catch (CorruptedLogException e) {
        System.out.println("Cannot load history!");
      }

      mainloop: while (true) {
        current_input = sc.nextLine();
        Command curr_command = null;
        String[] current_input_split = current_input.split(" ");
        try {
          curr_command = getCommand(current_input_split);
        } catch (InvalidTaskException e) {
          System.out.println("That's not a valid input :(");
          System.out.println(e.getMessage());
          continue;
        }

        switch (curr_command) {
          case LIST:
            System.out.println("_________________________\n" +
              "Get off your ass and starting doing work!" + "\n");
            for (int i = 0; i < history.size(); i++) {
              Task curr = history.get(i);
              System.out.println((i + 1) + "." +
                curr.getFullStatus());
            }
            System.out.println("_________________________\n");
            break;
          case BYE:
            break mainloop;
          case DELETE:
            int focus_index = -1;
            try {
              focus_index = checkIndexGiven(current_input_split[1], history.size());
            } catch (HistoryIndexException e) {
              System.out.println("Invalid index selected!");
              continue;
            }
            Task deleted = history.remove(focus_index);
            String print_out = "_________________________\n" +
              "Finished with this? Good Job!" + "\n" +
              deleted.getFullStatus() + "\n" +
              "_________________________\n" +
              "Now you have " + history.size() + " items in your list!\n";
            System.out.println(print_out);
            manager.writeLog(history);
            break;
          case MARK:
            focus_index = -1;
            try {
              focus_index = checkIndexGiven(current_input_split[1], history.size());
            } catch (HistoryIndexException e) {
              System.out.println("Invalid index selected!");
              continue;
            }
            Task focus_task = history.get(focus_index);
            focus_task.mark();
            print_out = "_________________________\n" +
              "Marking this done!" + "\n" +
              focus_task.getFullStatus() + "\n" +
              "_________________________\n";
            System.out.println(print_out);
            manager.writeLog(history);
            break;
          case UNMARK:
            focus_index = -1;
            try {
              focus_index = checkIndexGiven(current_input_split[1], history.size());
            } catch (HistoryIndexException e) {
              System.out.println("Invalid index selected!");
              continue;
            }
            focus_task = history.get(focus_index);
            focus_task.unmark();
            print_out = "_________________________\n" +
              "Ok this is not done..." + "\n" +
              focus_task.getFullStatus() + "\n" +
              "_________________________\n";
            System.out.println(print_out);
            manager.writeLog(history);
            break;
          case EVENT:
            Task event = null;
            String[] data;
            try {
              data = extractDescriptionData(current_input_split);
            } catch (InvalidInputException e) {
              System.out.println("That's not a valid input :(");
              System.out.println(e.getMessage());
              continue mainloop;
            }
            try {
              event = new Events(data[0], parseDate(data[1]), parseDate(data[2]));
              history.add(event);
              String to_print = "_________________________\n" +
                "added: " + event.getFullStatus() + "\n" +
                "_________________________\n" +
                "Now you have " + history.size() + " items in your list!\n";
              System.out.println(to_print);
              manager.writeLog(history);
            } catch (InvalidInputException e) {
              System.out.println("Invalid input: " + e.getMessage());
            }
            break;
          case TODO:
            event = null;
            try {
              data = extractDescriptionData(current_input_split);
            } catch (InvalidInputException e) {
              System.out.println("That's not a valid input :(");
              System.out.println(e.getMessage());
              continue mainloop;
            }
            event = new ToDos(data[0]);
            history.add(event);
            String to_print = "_________________________\n" +
              "added: " + event.getFullStatus() + "\n" +
              "_________________________\n" +
              "Now you have " + history.size() + " items in your list!\n";
            System.out.println(to_print);
            manager.writeLog(history);
            break;
          case DEADLINE:
            try {
              data = extractDescriptionData(current_input_split);
            } catch (InvalidInputException e) {
              System.out.println("That's not a valid input :(");
              System.out.println(e.getMessage());
              continue;
            }
            try {
              event = new Deadlines(data[0], parseDate(data[1]));
              history.add(event);
              to_print = "_________________________\n" +
                "added: " + event.getFullStatus() + "\n" +
                "_________________________\n" +
                "Now you have " + history.size() + " items in your list!\n";
              System.out.println(to_print);
              manager.writeLog(history);
            } catch (InvalidInputException e) {
              System.out.println("Invalid Input: " + e.getMessage());
            }
            break;
        }
      }
      String final_print = "_________________________\n" +
        "Bye. Hope to see you again soon!\n" +
        "_________________________\n";
      System.out.println(final_print);
      sc.close();
    }

    public static Command getCommand(String[] arr) throws InvalidTaskException {
      switch (arr[0]) {
        case "bye":
          return Command.BYE;
        case "todo":
          return Command.TODO;
        case "event":
          return Command.EVENT;
        case "deadline":
          return Command.DEADLINE;
        case "list":
          return Command.LIST;
        case "unmark":
          return Command.UNMARK;
        case "mark":
          return Command.MARK;
        case "delete":
          return Command.DELETE;
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
      switch(potential_date.charAt(4)) {
        case '-':
          try {
            String[] dd = potential_date.split(" ");
            String[] date_arr = dd[0].split("-");
            time = dd[1];
            year = Integer.parseInt(date_arr[0]); month = Integer.parseInt(date_arr[1]);
            day = Integer.parseInt(date_arr[2]);
            break;
          } catch (Exception e) {
            throw new InvalidDateTimeException();
          }
        case ' ':
          try {
            String[] date_arr = potential_date.split(" ");
            year = Integer.parseInt(date_arr[0]); month = Integer.parseInt(date_arr[1]);
            day = Integer.parseInt(date_arr[2]);
            time = date_arr[3];
            break;
          } catch (Exception e) {
            throw new InvalidDateTimeException();
          }
        default:
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
