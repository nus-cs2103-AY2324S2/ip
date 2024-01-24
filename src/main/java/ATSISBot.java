import java.util.Scanner;

public class ATSISBot {

  public static void main(String[] args) {
    String welcomeMessage =
      "____________________________________________________________\n" +
      "Hello! I'm ATSISBot\n" +
      "What can I do for you?\n" +
      "____________________________________________________________\n";
    String endingMessage =
      "____________________________________________________________\n" +
      "Bye. Hope to see you again soon!\n" +
      "____________________________________________________________\n";

    System.out.println(welcomeMessage);

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    while (!input.equals("bye")) {
      String output =
        "____________________________________________________________\n" +
        input +
        "\n" +
        "____________________________________________________________\n";
      System.out.println(output);
      input = sc.nextLine();
    }

    System.out.println(endingMessage);
  }
}
