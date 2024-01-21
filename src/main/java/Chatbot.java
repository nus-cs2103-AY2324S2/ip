import java.util.Scanner;

public class Chatbot {
  private String name;
  private String line = "\t____________________________________________________________\n";
  private String hello = this.line
      + "\tHello! I'm %s\n" + "\tWhat can I do for you?\n"
      + this.line;

  private String goodbye = "\tBye. Hope to see you again soon!\n"
      + this.line;

  private String[] storage;
  private int storageFill;

  public Chatbot(String name) {
    this.name = name;
    this.storage = new String[100];
    this.storageFill = 0;
    this.hello = String.format(this.hello, name);
  }

  public void greet() {
    String message = this.hello + this.goodbye;
    System.out.print(message);
  }

  public void echo() {
    System.out.println(this.hello);
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      System.out.println(this.line);

      if (input.equals("bye")) {
        System.out.println(this.goodbye);
        break;
      } else {
        System.out.println("\t" + input);
        System.out.println(this.line);
      }
    }

    scanner.close();
  }

  public void store() {
    System.out.println(this.hello);
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      System.out.print(this.line);

      if (input.equals("bye")) {
        System.out.print(this.goodbye);
        break;
      } else if (input.equals("")) {

      } else if (input.equals("list")) {
        for (int i = 0; i < this.storageFill; i++) {
          String formattedOutput = String.format("\t%d. %s", (i + 1), this.storage[i]);
          System.out.println(formattedOutput);
        }
        System.out.println(this.line);

      } else {
        System.out.println("\tadded: " + input);
        this.storage[this.storageFill++] = input;
        System.out.println(this.line);
      }
    }

    scanner.close();
  }
}