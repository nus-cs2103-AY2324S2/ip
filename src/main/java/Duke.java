import java.util.Scanner;

public class Duke {
  private static String logo = " ____        _        \n"
      + "|  _ \\ _   _| | _____ \n"
      + "| | | | | | | |/ / _ \\\n"
      + "| |_| | |_| |   <  __/\n"
      + "|____/ \\__,_|_|\\_\\___|\n";

  private static String chatbotName = "Aiken Dueet";

  private static void greet() {
    String greeting = "Hello! I'm " + chatbotName + "\n"
        + "What can I do for you?";
    System.out.println(greeting);
  }

  private static void exitMessage() {
    String exitMessage = "Bye. Hope to see you again soon!";
    System.out.println(exitMessage);
  }

  private static void println() {
    System.out.println("____________________________________________________________");
  }

  private static void echo() {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    while (!input.equals("bye")) {
      println();
      System.out.println(input);
      println();
      input = sc.nextLine();
    }

    sc.close();
  }

  public static void main(String[] args) {
    println();
    System.out.println(logo);
    println();
    greet();
    println();

    echo();

    println();
    exitMessage();
    println();
  }
}
