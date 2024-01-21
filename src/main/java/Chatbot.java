import java.util.Scanner;

public class Chatbot {
  private String name;
  private String hello = "\t____________________________________________________________\n"
      + "\tHello! I'm %s\n" + "\tWhat can I do for you?\n"
      + "\t____________________________________________________________\n";

  private String goodbye = "\tBye. Hope to see you again soon!\n"
      + "\t____________________________________________________________";

  public Chatbot(String name) {
    this.name = name;
    this.hello = String.format(this.hello, name);
  }

  public void greet() {
    String message = this.hello + this.goodbye;
    System.out.println(message);
  }

  public void echo() {
    System.out.println(this.hello);
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      System.out.println("\t____________________________________________________________");

      if (input.equals("bye")) {
        System.out.println(this.goodbye);
        break;
      } else {
        System.out.println("\t" + input);
        System.out.println("\t____________________________________________________________");
      }
    }

    scanner.close();
  }
}