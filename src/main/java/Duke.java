import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  private final static String indentation = " ".repeat(3);
  private final static String subIndentation = indentation + " ";
  private final static String divider = "_".repeat(60);
  private final static String logo = " _               _          \n" +
      "    | |   _   _  ___| | ___   _ \n" +
      "    | |  | | | |/ __| |/ / | | |      |\\__/,|   (`\\\n" +
      "    | |__| |_| | (__|   <| |_| |    _.|o o  |_   ) )\n" +
      "    |_____\\__,_|\\___|_|\\_\\\\__, |  -(((---(((--------\n" +
      "                          |___/ ";

  public static void main(String[] args) {
    printOutput(logo, "Hello! I'm Lucky the cat", "What can I do for you?");
    Scanner sc = new Scanner(System.in);
    ArrayList<String> tasks = new ArrayList<>();
    ArrayList<Boolean> taskStatus = new ArrayList<>();
    boolean isChatting = true;
    String tick = "[X]";
    String untick = "[ ]";
    String checkBox = untick;
    Command command;

    while (isChatting) {
      String[] input = sc.nextLine().split(" ");

      command = Command.parseCommand(input[0]);

      switch (command) {
        case VIEW_LIST:
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < tasks.size(); i++) {
            if (taskStatus.get(i)) {
              checkBox = tick;
            } else {
              checkBox = untick;
            }
            sb.append(checkBox + " " + (i + 1) + ". " + tasks.get(i) + "\n" + subIndentation);
          }

          printOutput("Here are the tasks in your list:", sb.toString());
          break;

        case BYE:
          printOutput("Goodbye my friend. See you soon!");
          isChatting = false;
          break;

        case UPDATE_MARK:
          int target = Integer.parseInt(input[1]) - 1;
          taskStatus.set(target, true);
          checkBox = tick;
          printOutput("Nice! I've marked this task as done:", checkBox + " " + tasks.get(target));
          break;
        case UPDATE_UNMARK:
          target = Integer.parseInt(input[1]) - 1;
          taskStatus.set(target, false);
          checkBox = untick;
          printOutput("OK, I've marked this task as not done yet:", checkBox + " " + tasks.get(target));
          break;
        default:
          tasks.add(String.join(" ", input));
          taskStatus.add(false);
          System.out.println(indentation + "added: " + String.join(" ", input) + "\n" + indentation +
              divider + "\n");
          break;
      }
    }
    sc.close();
  }

  public static void printOutput(String... msg) {
    System.out.println(indentation + divider);

    for (String string : msg) {
      System.out.println(subIndentation + string);
    }
    System.out.println(indentation + divider + "\n");
  }
}
