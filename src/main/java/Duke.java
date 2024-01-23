import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    String divider = "____________________________________________________________";
    String indentation = "   ";
    String logo = "     _               _          \n" +
        "    | |   _   _  ___| | ___   _ \n" +
        "    | |  | | | |/ __| |/ / | | |      |\\__/,|   (`\\\n" +
        "    | |__| |_| | (__|   <| |_| |    _.|o o  |_   ) )\n" +
        "    |_____\\__,_|\\___|_|\\_\\\\__, |  -(((---(((--------\n" +
        "                          |___/ ";

    String greeting = indentation + "Hello! I'm Lucky the cat.\n" + indentation + "What can I do for you?";
    String bye = "Goodbye my friend. See you soon!";

    System.out.println(indentation + divider + "\n" + logo + "\n\n" + greeting + "\n" + indentation + divider + "\n");

    Scanner sc = new Scanner(System.in);
    ArrayList<String> tasks = new ArrayList<>();
    ArrayList<Boolean> taskStatus = new ArrayList<>();
    boolean isChatting = true;
    String tick = "[X]";
    String untick = "[ ]";
    String checkBox = untick;

    while (isChatting) {
      String in = sc.nextLine();
      int target = 0;

      if (in.contains("mark") || in.contains("unmark")) {
        target = Integer.parseInt(in.split(" ")[1]) - 1;
        in = in.split(" ")[0];
      }

      switch (in.toLowerCase()) {
        case "list":
          System.out.println(indentation + divider);
          System.out.println(indentation + "Here are the tasks in your list:");

          for (int i = 0; i < tasks.size(); i++) {
            if (taskStatus.get(i)) {
              checkBox = tick;
            } else {
              checkBox = untick;
            }

            System.out.println(indentation + checkBox + " " + (i + 1) + ". " + tasks.get(i));
          }
          System.out.println(indentation + divider + "\n");
          break;

        case "blah":
          System.out.println(indentation + divider);
          System.out.println(indentation + "blah");
          System.out.println(indentation + divider + "\n");
          break;

        case "bye":
          System.out.println(indentation + divider);
          System.out.println(indentation + bye + "\n" + indentation + divider + "\n");
          isChatting = false;
          break;

        case "mark":
          taskStatus.set(target, true);
          checkBox = tick;
          System.out.println(indentation + divider);
          System.out.println(indentation + "Nice! I've marked this task as done:");
          System.out.println(indentation + "  " + checkBox + " " + tasks.get(target));
          System.out.println(indentation + divider + "\n");
          break;
        case "unmark":
          taskStatus.set(target, false);
          checkBox = untick;
          System.out.println(indentation + divider);
          System.out.println(indentation + "OK, I've marked this task as not done yet:");
          System.out.println(indentation + "  " + checkBox + " " + tasks.get(target));
          System.out.println(indentation + divider + "\n");
          break;
        default:
          System.out.println(indentation + divider);
          tasks.add(in);
          taskStatus.add(false);
          System.out.println(indentation + "added: " + in + "\n" + indentation + divider + "\n");
          break;
      }
    }
    sc.close();
  }
}
