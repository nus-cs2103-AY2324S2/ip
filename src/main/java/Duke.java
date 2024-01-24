import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

  private List<String> list = new ArrayList<>();

  private void add(String item) {
    list.add(item);
    System.out.println("____________________________________________________________");
    System.out.println("item added: " + item);
    System.out.println("____________________________________________________________");
  }

  private void list() {
    System.out.println("____________________________________________________________");
    for (int i = 0; i < list.size(); i++) {
      System.out.println(i + 1 + ". " + list.get(i));
    }
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
      }
      if (isQuitCommand(input)) {
        chatbot.exit();
        break;
      } else if (input.equals("list")) {
        chatbot.list();
      } else {
        chatbot.add(input);
      }
    }
  }
}
