package duke.ui;

import java.util.Arrays;
import java.util.Scanner;

import duke.storage.Storage;

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
    String goodbye = "Bye. Hope to see you again soon!";

    // Display greeting
    System.out.println(greeting);

    // Scanner for getting user input
    Scanner sc = new Scanner(System.in);

    String input;
    // Store and echo user input
    do {
      input = sc.nextLine();
      String[] splitInput = input.split(" ");

      System.out.println("------------------------------------------------------------");

      switch (splitInput[0].toLowerCase()) {
        case "bye": // Exit
          // Print exit message
          System.out.println(goodbye);
          break;

        case "list": // List items
          Storage.listItems();
          break;

        case "mark": // Mark item
          Storage.markItem(Integer.parseInt(splitInput[1]) - 1);
          break;

        case "unmark": // Unmark item
          Storage.unmarkItem(Integer.parseInt(splitInput[1]) - 1);
          break;

        default: // Store and echo items
          try {
            // Store item
            Storage.storeItem(splitInput[0], Arrays.copyOfRange(splitInput, 1, splitInput.length));

            // Echo item
            System.out.println("added: " + input);
            break;
          } catch (Exception exception) {
            System.out.println(String.format("ERROR: %s", exception.getMessage()));
          }
      }

      System.out.println("------------------------------------------------------------");
    } while (!input.equals("bye"));

    sc.close();
  }
}
