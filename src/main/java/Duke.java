import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    String input = "";
    String botName = "GeePeeTee";
    // Welcome Message
    System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?");
    Scanner scanner = new Scanner(System.in);
    while (!input.equals("bye")) {
      input = scanner.nextLine();
      if (input.equals("bye")) {
        scanner.close();
        break;
      }
      System.out.println(input);
    }
    // Exit Message
    System.out.println("Bye. Hope to see you again soon!");
  }
}
