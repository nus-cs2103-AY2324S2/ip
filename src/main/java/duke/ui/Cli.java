package duke.ui;

import java.util.Scanner;

/**
 * The UI CLI class handles the displaying of UI elements in
 * the application
 *
 * @author Ryan NgWH
 */
public class Cli {
  /**
   * Method to print the CLI UI
   */
  public static void printUI() {
    // UI Greeting
    String greeting = "------------------------------------------------------------\n" +
        "Hello! I'm Ciara\n" +
        "What can I do for you?\n" +
        "------------------------------------------------------------";

    // UI Goodbye message
    String goodbye = "------------------------------------------------------------\n" +
        "Bye. Hope to see you again soon!\n" +
        "------------------------------------------------------------";

    // Display greeting
    System.out.println(greeting);

    // Scanner for getting user input
    Scanner sc = new Scanner(System.in);

    // Echo user input
    while (true) {
      String input = sc.nextLine();

      if (input.equals("bye")) {
        break;
      }
      System.out.println("------------------------------------------------------------\n" +
          input +
          "\n------------------------------------------------------------");
    }

    // Print exit message
    System.out.println(goodbye);
    sc.close();
  }
}
