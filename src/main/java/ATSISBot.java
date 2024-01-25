import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The ATSISBot class represents a chatbot that can perform various tasks.
 * It allows users to interact with the bot by entering commands and receiving responses.
 * The bot can manage a list of tasks, including adding, marking, and deleting tasks.
 */
public class ATSISBot {

  private static final String line = "____________________________________________________________\n";

  private static final String welcomeMessage = line + "Hello! I'm ATSISBot\n" + "What can I do for you?\n" + line;
  private static final String endingMessage = line + "Bye. Hope to see you again soon!\n" + line;
  private static final String listMessage = "Here are the tasks in your list:\n";
  private static final String markMessage = "Nice! I've marked this task as done:\n";
  private static final String unmarkMessage = "OK, I've marked this task as not done yet:\n";
  private static final String addTaskMessage = "Got it. I've added this task:\n";
  private static final String deleteTaskMessage = "Noted. I've removed this task:\n";
  private static final String noDescriptionMessage = "The description of a %s cannot be empty.\n";
  private static final String unknownCommandMessage = "I'm sorry, but I don't understand that command.\n";

  private static ArrayList<Task> list = new ArrayList<>();

  /**
   * The main method is the entry point of the ATSISBot program.
   * It prompts the user for input and executes corresponding commands based on the input.
   * The program continues to accept input until the user enters "bye".
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    System.out.println(ATSISBot.welcomeMessage);

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    while (!input.equals("bye")) {
      System.out.print(line);
      if (input.equals("list")) {
        AtomicInteger index = new AtomicInteger(1);
        System.out.print(listMessage);
        list.forEach(element -> System.out
            .println(index.getAndIncrement() + ". " + element.toString()));
      } else if (input.startsWith("mark")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        if (elementIdx <= 0 || elementIdx > list.size()) {
          System.out.println("Invalid task number. Please enter a valid task number.");
        } else {
          list.get(elementIdx - 1).markAsDone();
          System.out.println(markMessage);
          System.out.println("  " + list.get(elementIdx - 1).toString());
        }
      } else if (input.startsWith("unmark")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        if (elementIdx <= 0 || elementIdx > list.size()) {
          System.out.println("Invalid task number. Please enter a valid task number.");
        } else {
          list.get(elementIdx - 1).markAsUndone();
          System.out.println(unmarkMessage);
          System.out.println("  " + list.get(elementIdx - 1).toString());
        }
      } else if (input.startsWith("todo")) {
        if (!input.contains(" ")) {
          System.out.print(String.format(noDescriptionMessage, "todo"));
        } else {
          String description = input.split(" ", 2)[1];
          list.add(new Todo(description));
          System.out.print(addTaskMessage);
          System.out.println("  " + list.get(list.size() - 1).toString());
          System.out.println(Task.getTaskNum());
        }
      } else if (input.startsWith("deadline")) {
        if (!input.contains(" ")) {
          System.out.print(String.format(noDescriptionMessage, "deadline"));
        } else {
          String[] descriptionAndBy = input.split(" ", 2)[1].split(" /by ");
          if (descriptionAndBy.length != 2) {
            System.out.println("Invalid deadline format. Please use: deadline <description> /by <date>");
          } else {
            list.add(new Deadline(descriptionAndBy[0], descriptionAndBy[1]));
            System.out.print(addTaskMessage);
            System.out.println("  " + list.get(list.size() - 1).toString());
            System.out.println(Task.getTaskNum());
          }
        }
      } else if (input.startsWith("event")) {
        if (!input.contains(" ")) {
          System.out.print(String.format(noDescriptionMessage, "event"));
        } else {
          String[] descriptionAndFromTo = input.split(" ", 2)[1].split(" /from ");
          if (descriptionAndFromTo.length != 2) {
            System.out.println("Invalid event format. Please use: event <description> /from <date>");
          } else {
            list.add(new Event(descriptionAndFromTo[0], descriptionAndFromTo[1]));
            System.out.print(addTaskMessage);
            System.out.println("  " + list.get(list.size() - 1).toString());
            System.out.println(Task.getTaskNum());
          }
        }
      } else if (input.startsWith("delete")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        if (elementIdx <= 0 || elementIdx > list.size()) {
          System.out.println("Invalid task number. Please enter a valid task number.");
        } else {
          Task removedTask = list.remove(elementIdx - 1);
          System.out.print(deleteTaskMessage);
          System.out.println("  " + removedTask.toString());
          System.out.println(Task.getTaskNum());
        }
      } else {
        System.out.print(unknownCommandMessage);
      }
      System.out.print(line);
      input = sc.nextLine();
    }

    System.out.println(ATSISBot.endingMessage);
  }
}
