import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "shu heng";
        String name_display = "_________________________\n" +
          "Hello! I'm " + name + "\n" +
          "What can I do for you?\n" +
          "_________________________\n";
      System.out.println(name_display);
      String current_input = "";

      ArrayList<Task> history = new ArrayList<>();

      while (true) {
        current_input = sc.nextLine();
        String[] current_input_split = current_input.split(" ");
        if (current_input.equals("bye")) {break;}
        if (current_input.equals("list")) {
          System.out.println("_________________________\n" +
            "Get off your ass and starting doing work!" + "\n");
          for (int i = 0; i < history.size(); i++) {
            Task curr = history.get(i);
            System.out.println((i + 1) + "." +
              curr.getFullStatus());
          }
          System.out.println("_________________________\n");
        } else if (current_input_split[0].equals("delete")) {
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
        } else if (current_input_split[0].equals("mark")) {
          int focus_index = -1;
          try {
            focus_index = checkIndexGiven(current_input_split[1], history.size());
          } catch (HistoryIndexException e) {
            System.out.println("Invalid index selected!");
            continue;
          }
          Task focus_task = history.get(focus_index);
          focus_task.mark();
          String print_out = "_________________________\n" +
            "Marking this done!" + "\n" +
            focus_task.getFullStatus() + "\n" +
            "_________________________\n";
          System.out.println(print_out);
        } else if (current_input_split[0].equals("unmark")) {
          int focus_index = -1;
          try {
            focus_index = checkIndexGiven(current_input_split[1], history.size());
          } catch (HistoryIndexException e) {
            System.out.println("Invalid index selected!");
            continue;
          }
          Task focus_task = history.get(focus_index);
          focus_task.unmark();
          String print_out = "_________________________\n" +
            "Ok this is not done..." + "\n" +
            focus_task.getFullStatus() + "\n" +
            "_________________________\n";
          System.out.println(print_out);
        } else {
          Task event = null;
          String[] data;
          try {
            data = extractDescriptionData(current_input_split);
          } catch (InvalidInputException e) {
            System.out.println("That's not a valid input :(");
            System.out.println(e.getMessage());
            continue;
          }
          if (data.length == 0) {
            System.out.println("invalid data");
          }
          switch (current_input_split[0]) {
            case "event":
              event = new Events(data[0], data[1], data[2]);
              history.add(event);
              break;
            case "todo":
              event = new ToDos(data[0]);
              history.add(event);
              break;
            case "deadline":
              event = new Deadlines(data[0], data[1]);
              history.add(event);
              break;
          }
          String to_print = "_________________________\n" +
            "added: " + event.getFullStatus() + "\n" +
            "_________________________\n" +
            "Now you have " + history.size() + " items in your list!\n";
          System.out.println(to_print);
        }
      }

      String final_print = "_________________________\n" +
        "Bye. Hope to see you again soon!\n" +
        "_________________________\n";
      System.out.println(final_print);
      sc.close();
    }

    public static Integer checkIndexGiven(String s, int bounds) throws HistoryIndexException {
      Integer parsed = Integer.parseInt(s);
      if (parsed < 0 || parsed >= bounds) {
        throw new HistoryIndexException();
      }
      return parsed;
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
        default:
          throw new InvalidTaskException();
      }
      return ret;
    }
}
