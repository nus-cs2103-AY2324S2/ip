package Managers;
import java.util.ArrayList;
import java.util.List;

import Models.Deadline;
import Models.Event;
import Models.Task;
import Models.Todo;

public class TaskManager {
  private List<Task> tasks = new ArrayList<>();
  
  private Task get(int i) {
    return this.tasks.get(i);
  }

  private void printSeparator() {
    System.out.println("--------------------");
  }

  protected void add(String input) {
    Task task = new Task(input);
    this.printSeparator();
    System.out.println("Got it, I've added this task: \n  " + task);
    tasks.add(task);
    System.out.println("Now you have " + tasks.size() + " tasks in the list");
    this.printSeparator();
  }

  protected void add(Task task) {
    this.printSeparator();
    System.out.println("Got it, I've added this task: \n  " + task);
    tasks.add(task);
    System.out.println("Now you have " + tasks.size() + " tasks in the list");
    this.printSeparator();
  }

  private void print() {
    this.printSeparator();
    for (int i = 0; i < this.tasks.size(); i++) {
        System.out.println((i + 1) + ". " + this.get(i));
    }
    this.printSeparator();
  }

  protected static String getCommand(String input) {
    return input.split(" ")[0];
  }

  protected static String getValue(String input) {
    if (input.split(" ").length <= 1) throw new IllegalArgumentException("Value expected but not found");
    return input.split(" ")[1];
  }

  public void handleInput(String input) {
    try {
      String command = getCommand(input);
      // Decided to pass the entire input instead because otherwise we would have to parse the input into command and value
      // which would not be appropriate here since it includes a list() function too
      if (command.equals("todo")) {
        addTodo(input);
      } else if (command.equals("deadline")) {
        addDeadline(input);
      } else if (command.equals("event")) {
        addEvent(input);
      } else if (command.equals("list")) {
        print();
      } else if (command.equals("mark")) {
        mark(input);
      } else if (command.equals("unmark")) {
        unmark(input);
      } else if (command.equals("bye")) {
        return;
      } else {
        throw new IllegalArgumentException("Command not recognized");
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }

  private void addTodo(String input) {
    Todo todo = TodoDao.getFrom(input);
    this.add(todo);
  }

  private void addDeadline(String input) {
    Deadline deadline = DeadlineDao.getFrom(input);
    this.add(deadline);
  }

  private void addEvent(String input) {
    Event event = EventDao.getFrom(input);
    this.add(event);
  }


  private void mark(String input) {
    String value = getValue(input);

    int taskIndex = Integer.parseInt(value) - 1;
    Task task = this.get(taskIndex);
    this.printSeparator();
    task.markAsDone();
    System.out.println("Nice! I've marked this task as done: \n" + task);
    this.printSeparator();
  }

  private void unmark(String input) {
    String value = getValue(input);

    int taskIndex = Integer.parseInt(value) - 1;
    Task task = this.get(taskIndex);
    this.printSeparator();
    task.markAsUndone();
    System.out.println("Ok! I've marked this task as not yet done: \n" + task);
    this.printSeparator();
  }

}
