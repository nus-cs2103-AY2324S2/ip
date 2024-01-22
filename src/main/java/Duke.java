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
            "Get off your ass and starting doing work! " + "\n");
          for (int i = 0; i < history.size(); i++) {
            Task curr = history.get(i);
            System.out.println((i + 1) + "." +
              curr.getFullStatus());
          }
          System.out.println("_________________________\n");
        } else if (current_input_split[0].equals("mark")) {
          int focus_index = Integer.parseInt(current_input_split[1]) - 1;
          Task focus_task = history.get(focus_index);
          focus_task.mark();
          String print_out = "_________________________\n" +
            "Marking this done! " + "\n" +
            focus_task.getFullStatus() + "\n" +
            "_________________________\n";
          System.out.println(print_out);
        } else if (current_input_split[0].equals("unmark")) {
          int focus_index = Integer.parseInt(current_input_split[1]) - 1;
          Task focus_task = history.get(focus_index);
          focus_task.unmark();
          String print_out = "_________________________\n" +
            "Ok this is not done... " + "\n" +
            focus_task.getFullStatus() + "\n" +
            "_________________________\n";
          System.out.println(print_out);
        } else {
          boolean valid = true;
          Task event = null;
          String[] data = extractDescriptionData(current_input_split);
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
          if (valid) {
            String to_print = "_________________________\n" +
              "added: " + event.getFullStatus() + "\n" +
              "_________________________\n" +
              "Now you have " + history.size() + " items in your list!";
            System.out.println(to_print);
          }
          valid = true;
        }
      }

      String final_print = "_________________________\n" +
        "Bye. Hope to see you again soon!\n" +
        "_________________________\n";
      System.out.println(final_print);
      sc.close();
    }

    public static String[] extractDescriptionData(String[] descriptionArray) {
      String[] ret = new String[3];
      String task_desc;
      switch(descriptionArray[0]) {
        case "todo":
          task_desc = String.join(" ",
            Arrays.copyOfRange(descriptionArray, 1, descriptionArray.length));
          ret[0] = task_desc;
          break;
        case "event":
          Integer start_idx = null; Integer end_idx = null;
          for (int i = 0; i < descriptionArray.length; i++) {
            if (descriptionArray[i].equals("/from")) {
              start_idx = i;
            }
            if (descriptionArray[i].equals("/to")) {
              end_idx = i;
            }
          }
          if (start_idx.equals(null) || end_idx.equals(null)) {
            return ret;
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
          start_idx = null;
          for (int i = 0; i < descriptionArray.length; i++) {
            if (descriptionArray[i].equals("/by")) {
              start_idx = i;
            }
          }
          if (start_idx.equals(null)) {
            return ret;
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
}
