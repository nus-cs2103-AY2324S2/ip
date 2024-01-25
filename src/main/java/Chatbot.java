import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
  public enum CommandType {
    TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, LIST, INVALID;

    public static CommandType fromString(String command) {
      try {
        return CommandType.valueOf(command.toUpperCase());
      } catch (IllegalArgumentException e) {
        return INVALID;
      }
    }
  }

  private String name;
  private String line = "\t____________________________________________________________\n";
  private String hello = this.line
      + "\tHello! I'm %s\n" + "\tWhat can I do for you?\n"
      + this.line;

  private String goodbye = "\tBye. Hope to see you again soon!\n"
      + this.line;

  private ArrayList<Task> storage;
  private int storageFill;

  public Chatbot(String name) {
    this.name = name;
    this.storage = new ArrayList<>();
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
      String formattedOutput = String.format("\t%d. %s", (i + 1), this.storage.get(i));
      System.out.println(formattedOutput);
    }
  }

  // Level 2 onwards
  public void store() {
    System.out.println(this.hello);
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      System.out.print(this.line);
      String[] parts = input.split(" "); // split input
      if (input.replaceAll("\\s", "").equals("")) { // check empty input
        System.out.println("\tEnter a non-empty command!");
      } else {
        String firstWord = parts[0]; // check for mark/unmark
        CommandType command = CommandType.fromString(firstWord);
        switch (command) {
          case BYE:
            System.out.print(this.goodbye);
            scanner.close();
            return;
          case LIST:
            this.list();
            break;
          case MARK:
          case UNMARK:
          case DELETE:
            if (parts.length == 2) {
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
                  this.storage.get(taskNumber - 1).mark();
                }
                if (parts[0].equals("unmark")) {
                  this.storage.get(taskNumber - 1).unmark();
                }
                if (parts[0].equals("delete")) {
                  Task task = this.storage.get(taskNumber - 1);
                  this.storage.remove(taskNumber - 1);
                  System.out.println("\tNoted. I've removed this task:\n\t" + task);
                  System.out.println("\tNow you have " + --this.storageFill + " tasks in the list.");
                }
              }
            } else {
              System.out.println("\tOOPS!!! That is not a valid command! "
                  + "Try the following: \n"
                  + "\ttodo xxx\n"
                  + "\tdeadline xxx /by xxx\n"
                  + "\tevent xxx /from xxx /to xxx");
            }
            break;

          default:
            this.addTask(input, command);
            break;
        }
      }
      System.out.println(this.line);
    }

  }

  private void addTask(String input, CommandType command) {
    String[] parts = input.split(" ", 2);
    Task newTask = null;
    switch (command) {
      case TODO:
        if (parts[1].replaceAll("\\s", "").equals("")) {
          System.out.println("\tTask should not be empty!");
        } else {
          newTask = new Todo(parts[1]);
        }
        break;
      case DEADLINE:
        String[] deadlineParts = parts[1].split(" /by ", 2);
        if (deadlineParts.length < 2) {
          System.out.println("\tSpecify /by xxx!");
        } else if (deadlineParts[0].replaceAll("\\s", "").equals("")) {
          System.out.println("\tTask should not be empty!");
        } else if (deadlineParts[1].replaceAll("\\s", "").equals("")) {
          System.out.println("\tDue date should not be empty!");
        } else {
          newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
        }
        break;
      case EVENT:
        String[] eventParts = parts[1].split(" /from ", 2);
        if (eventParts[0].replaceAll("\\s", "").equals("")) {
          System.out.println("\tTask should not be empty!");
        } else if (eventParts.length < 2) {
          // not enough parts for an event
          System.out.println("\tSpecify /from xxx and /to xxx!");
        } else {
          String[] timeParts = eventParts[1].split(" /to ", 2);
          if (timeParts[0].replaceAll("\\s", "").equals("")) {
            System.out.println("\tStart time should not be empty!");
          } else if (timeParts.length < 2) {
            System.out.println("\tSpecify xxx /to xxx!");
          } else if (timeParts[1].replaceAll("\\s", "").equals("")) {
            System.out.println("\tEnd time should not be empty!");
          } else {
            // Construct the event string
            String eventTime = timeParts[0] + " to: " + timeParts[1];
            newTask = new Event(eventParts[0], eventTime);
          }
        }
        break;
      default:
        System.out.println("\tOOPS!!! That is not a valid command! "
            + "Try the following: \n"
            + "\ttodo xxx\n"
            + "\tdeadline xxx /by xxx\n"
            + "\tevent xxx /from xxx /to xxx");
        break;
    }

    if (newTask != null) {
      this.storage.add(newTask);
      this.storageFill++;
      System.out.println("\tGot it. I've added this task:\n\t" + newTask);
      System.out.println("\tNow you have " + this.storageFill + " tasks in the list.");
    }
  }

}