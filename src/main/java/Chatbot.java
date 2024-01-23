import java.util.Scanner;

public class Chatbot {
  private String name;
  private String line = "\t____________________________________________________________\n";
  private String hello = this.line
      + "\tHello! I'm %s\n" + "\tWhat can I do for you?\n"
      + this.line;

  private String goodbye = "\tBye. Hope to see you again soon!\n"
      + this.line;

  private Task[] storage;
  private int storageFill;

  public Chatbot(String name) {
    this.name = name;
    this.storage = new Task[100];
    this.storageFill = 0;
    this.hello = String.format(this.hello, name);
  }

  // Level 0
  public void greet() {
    String message = this.hello + this.goodbye;
    System.out.print(message);
  }

  // Level 1
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

  public void list() {
    System.out.println("\tHere are the tasks in your list:");
    for (int i = 0; i < this.storageFill; i++) {
      String formattedOutput = String.format("\t%d. %s", (i + 1), this.storage[i]);
      System.out.println(formattedOutput);
    }
    System.out.println(this.line);
  }

  // Level 2 onwards
  public void store() {
    System.out.println(this.hello);
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      System.out.print(this.line);
      Task newTask = new Task(input);
      String[] parts = input.split(" "); // split the input into parts

      if (input.equals("bye")) {
        System.out.print(this.goodbye);
        break;
      } else if (input.equals("")) {
        continue;
      } else if (input.equals("list")) {
        this.list();
      } else {

        String firstWord = parts[0]; // check for commands
        if (parts.length == 2 && firstWord.equals("mark")
            || firstWord.equals("unmark")) { // is a command
          try {
            int taskNumber = Integer.parseInt(parts[1]); // convert 2nd part to an int
          } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid number after command");
            continue;
          }
          int taskNumber = Integer.parseInt(parts[1]);
          if (taskNumber > this.storageFill || taskNumber <= 0) { // check if int within range
            System.out.println("\tOut of bounds!");
          } else { // valid operation
            if (parts[0].equals("mark")) {
              this.storage[taskNumber - 1].mark();
            }
            if (parts[0].equals("unmark")) {
              this.storage[taskNumber - 1].unmark();
            }
          }
        } else { // 1 word
          System.out.println("\tadded: " + input);
          this.storage[this.storageFill++] = newTask;
        }
        System.out.println(this.line);
      }
    }

    scanner.close();
  }

  private void addTask(String input) {
    String[] parts = input.split(" ", 2);
    String firstWord = parts[0];
    Task newTask = null;

    if (firstWord.equals("todo")) {
      newTask = new Todo(parts[1]);
    } else if (firstWord.equals("deadline")) {
      String[] deadlineParts = parts[1].split(" /by ", 2);
      newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
    } else if (firstWord.equals("event")) {
      String[] eventParts = parts[1].split(" /from ", 2);
      newTask = new Event(eventParts[0], eventParts[1]);
    }

    if (newTask != null) {
      this.storage[this.storageFill++] = newTask;
      System.out.println("Got it. I've added this task:\n  " + newTask);
      System.out.println("Now you have " + this.storageFill + " tasks in the list.");
    }
  }

}