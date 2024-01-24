import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
  private static final String chatbotName = "Sylvia";

  private static final String[] quitCommands = { "bye", "quit", "exit" };

  private static boolean isQuitCommand(String input) {
    for (String command : quitCommands) {
      if (input.toLowerCase().equals(command)) {
        return true;
      }
    }
    return false;
  }

  public Duke() {
    this.list = new TaskList();
  }

  private TaskList list;

  private void add(String input) {
    boolean added = list.addTask(new Task(input));
    if (!added) {
      System.out.println("____________________________________________________________");
      System.out.println("Sorry, some error happened. Please try again.");
      System.out.println("____________________________________________________________");
      return;
    }
    System.out.println("____________________________________________________________");
    System.out.println("added: " + input);
    System.out.println("____________________________________________________________");
  }

  private void list() {
    System.out.println("____________________________________________________________");
    System.out.println(list);
    System.out.println("____________________________________________________________");
  }

  private void markTaskAsDone(int index) {
    list.markTaskAsDone(index);
    System.out.println("____________________________________________________________");
    System.out.print("Done: ");
    System.out.println(list.get(index - 1));
    System.out.println("____________________________________________________________");
  }

  private void unmarkTaskAsDone(int index) {
    list.unmarkTaskAsDone(index);
    System.out.println("____________________________________________________________");
    System.out.print("Undone: ");
    System.out.println(list.get(index - 1));
    System.out.println("____________________________________________________________");
  }

  private void greet() {
    System.out.println("____________________________________________________________");
    System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
    System.out.println("____________________________________________________________");
  }

  private void exit() {
    System.out.println("____________________________________________________________");
    System.out.println("Cya!");
    System.out.println("____________________________________________________________");
  }

  private void echo(String input) {
    System.out.println("____________________________________________________________");
    System.out.println(input);
    System.out.println("____________________________________________________________");
  }

  public static void main(String[] args) {
    Duke chatbot = new Duke();
    chatbot.greet();
    String input = "";
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      try {
        input = reader.readLine();
      } catch (IOException e) {
        System.out.println("Sorry, I don't understand that.");
        break;
      }
      // get the first word of the input
      String[] words = input.split(" ", 2);
      if (isQuitCommand(words[0])) {
        chatbot.exit();
        break;
      } else if (words[0].equals("list")) {
        chatbot.list();
      } else if (words[0].equals("mark")) {
        int index = Integer.parseInt(words[1]);
        chatbot.markTaskAsDone(index);
      } else if (words[0].equals("unmark")) {
        int index = Integer.parseInt(words[1]);
        chatbot.unmarkTaskAsDone(index);
      } else {
        chatbot.add(input);
      }
    }
  }
}
