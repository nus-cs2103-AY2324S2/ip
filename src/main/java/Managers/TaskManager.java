package Managers;
import java.util.ArrayList;
import java.util.List;

import Models.Deadline;
import Models.Event;
import Models.Task;
import Models.Todo;
import Utils.StringUtils;

// TODO: Stretch goal: add exceptions for marking and listing non-existent indexes
public class TaskManager {

  private List<Task> tasks = new ArrayList<>();

  private Task get(int i) {
    return this.tasks.get(i);
  }

  private void printSeparator() {
    System.out.println("--------------------");
  }

  protected void add(Task task) {
    this.printSeparator();
    System.out.println("Got it, I've added this task: \n  " + task);
    tasks.add(task);
    System.out.println("Now you have " + tasks.size() + " tasks in the list");
    this.printSeparator();
  }

  protected void delete(String input) {
    int i = Integer.parseInt(StringUtils.getValueOfCommand(input, Commands.DELETE.getCommand(), null)) - 1;

    this.printSeparator();
    Task task = this.tasks.remove(i);
    System.out.println("Noted, I've removed this task: \n  " + task);
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
      input = input.trim();
      String command = getCommand(input);
      // Decided to pass the entire input instead because otherwise we would have to parse the input into command and value
      // which would not be appropriate here since it includes a list() function too
      if (command.equals(TodoDao.name)) {
        addTodo(input);
      } else if (command.equals(DeadlineDao.name)) {
        addDeadline(input);
      } else if (command.equals(EventDao.name)) {
        addEvent(input);
      } else if (command.equals(Commands.LIST.getCommand())) {
        print();
      } else if (command.equals(Commands.MARK.getCommand())) {
        mark(input);
      } else if (command.equals(Commands.UNMARK.getCommand())) {
        unmark(input);
      }  else if (command.equals(Commands.DELETE.getCommand())) {
        delete(input);
      } else if (command.equals(Commands.BYE.getCommand())) {
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
    int taskIndex = Integer.parseInt(StringUtils.getValueOfCommand(input, Commands.MARK.getCommand(), null)) - 1;
    Task task = this.get(taskIndex);
    this.printSeparator();
    task.markAsDone();
    System.out.println("Nice! I've marked this task as done: \n" + task);
    this.printSeparator();
  }

  private void unmark(String input) {
    int taskIndex = Integer.parseInt(StringUtils.getValueOfCommand(input, Commands.UNMARK.getCommand(), null)) - 1;
    Task task = this.get(taskIndex);
    this.printSeparator();
    task.markAsUndone();
    System.out.println("Ok! I've marked this task as not yet done: \n" + task);
    this.printSeparator();
  }
}
