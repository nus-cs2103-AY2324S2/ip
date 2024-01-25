import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Task {
  protected String description;
  protected boolean isDone;

  private static Integer taskNum = 0;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
    Task.taskNum += 1;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " "); // mark done task with X
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }

  public String getDescription() {
    return this.description;
  }

  public static String getTaskNum() {
    return "Now you have " + Task.taskNum + " tasks in the list.";
  }

  public abstract String toString();
}

class Todo extends Task {
  public Todo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
  }
}

class Deadline extends Task {
  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.by + ")";
  }
}

class Event extends Task {
  protected String fromTo;

  public Event(String description, String fromTo) {
    super(description);
    this.fromTo = fromTo;
  }

  @Override
  public String toString() {
    return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.fromTo + ")";
  }
}

public class ATSISBot {

  private static final String line = "____________________________________________________________\n";

  private static final String welcomeMessage = line + "Hello! I'm ATSISBot\n" + "What can I do for you?\n" + line;
  private static final String endingMessage = line + "Bye. Hope to see you again soon!\n" + line;
  private static final String listMessage = "Here are the tasks in your list:\n";
  private static final String markMessage = "Nice! I've marked this task as done:\n";
  private static final String unmarkMessage = "OK, I've marked this task as not done yet:\n";
  private static final String addTaskMessage = "Got it. I've added this task:\n";

  private static ArrayList<Task> list = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println(ATSISBot.welcomeMessage);

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    while (!input.equals("bye")) {
      System.out.print(line);
      if (input.equals("list")) {
        AtomicInteger index = new AtomicInteger(1);
        System.out.print(listMessage);
        list.forEach(element -> System.out
            .println(index.getAndIncrement() + ". " + element.toString()));
      } else if (input.startsWith("mark")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        list.get(elementIdx - 1).markAsDone();
        System.out.println(markMessage);
        System.out.println("  " + list.get(elementIdx - 1).toString());
      } else if (input.startsWith("unmark")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        list.get(elementIdx - 1).markAsUndone();
        System.out.println(unmarkMessage);
        System.out.println("  " + list.get(elementIdx - 1).toString());
      } else if (input.startsWith("todo")) {
        String description = input.split(" ", 2)[1];
        list.add(new Todo(description));
        System.out.print(addTaskMessage);
        System.out.println("  " + list.get(list.size() - 1).toString());
        System.out.println(Task.getTaskNum());
      } else if (input.startsWith("deadline")) {
        String[] descriptionAndBy = input.split(" ", 2)[1].split(" /by ");
        list.add(new Deadline(descriptionAndBy[0], descriptionAndBy[1]));
        System.out.print(addTaskMessage);
        System.out.println("  " + list.get(list.size() - 1).toString());
        System.out.println(Task.getTaskNum());
      } else if (input.startsWith("event")) {
        String[] descriptionAndFromTo = input.split(" ", 2)[1].split(" /from ");
        list.add(new Event(descriptionAndFromTo[0], descriptionAndFromTo[1]));
        System.out.print(addTaskMessage);
        System.out.println("  " + list.get(list.size() - 1).toString());
        System.out.println(Task.getTaskNum());
      }
      System.out.print(line);
      input = sc.nextLine();
    }

    System.out.println(ATSISBot.endingMessage);
  }
}
