package duke.ui;

public class Cli {
  public static void printUI() {
    String greeting = "------------------------------------------------------------\n" +
        "Hello! I'm Ciara\n" +
        "What can I do for you?\n";

    String goodbye = "------------------------------------------------------------\n" +
        "Bye. Hope to see you again soon!\n" +
        "------------------------------------------------------------";

    System.out.println(greeting);
    System.out.println(goodbye);
  }
}
