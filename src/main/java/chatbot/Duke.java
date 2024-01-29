package chatbot;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Thrown to indicate that the description for a Todo task cannot be empty.
 */
class EmptyTodoException extends Exception {

  public EmptyTodoException() {
    super("Todo cannot have empty description .");
  }
}

/**
 * Thrown to indicate that the description for a Deadline task cannot be empty.
 */
class EmptyDeadlineException extends Exception {

  public EmptyDeadlineException() {
    super("Deadline cannot have empty description.");
  }
}

/**
 * Thrown to indicate that the description for an Event task cannot be empty.
 */
class EmptyEventException extends Exception {

  public EmptyEventException() {
    super("Event cannot have empty description.");
  }
}

/**
 * Thrown to indicate that the user input is invalid.
 */
class InvalidException extends Exception {

  public InvalidException() {
    super("Sorry, invalid input.");
  }
}

/**
 * Represents a generic task with a description and a completion status.
 */
class Task {

  public String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "[X] " : "[ ] "); // mark done task with X
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }

  @Override
  public String toString() {
    return (this.getStatusIcon() + this.description);
  }

  public String getDesc() {
    return this.description;
  }
}

/**
 * Represents a Todo task.
 */
class Todo extends Task {

  public Todo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return ("[T]" + super.toString());
  }
}

/**
 * Represents a Deadline task with a due date.
 */
class Deadline extends Task {

  protected String by;

  public Deadline(String description, String dateString) {
    super(description);
    LocalDate date = LocalDate.parse(dateString);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    this.by = date.format(formatter);
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }
}

/**
 * Represents an Event task with a start and end time.
 */
class Event extends Task {

  protected String from;
  protected String to;

  public Event(String description, String from, String to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return (
      "[E]" + super.toString() + " (from: " + from + ") " + "to: " + to + ")"
    );
  }
}

/**
 * The main class for the chatbot application. It initializes and runs the chatbot,
 * handling the primary control flow of the application.
 */
public class Duke {

  private TaskList myList;
  private Storage st;
  private Ui ui;
  private Parser p;

  public Duke(String filePath) {
    this.st = new Storage(filePath);
    this.myList = new TaskList(new ArrayList<Task>());
    this.p = new Parser();
    this.ui = new Ui(this.myList);
  }

  /**
   * Starts and runs the chatbot. This method keeps the chatbot running
   * until the user decides to exit.
   * @throws IOException If an I/O error occurs.
   */
  public void run() throws IOException {
    ui.start();
    boolean continueChat = true;
    while (continueChat) {
      continueChat = ui.reply(this.st);
    }
  }

  public static void main(String[] args) throws IOException {
    new Duke("./data/tasks.txt").run();
  }
}
