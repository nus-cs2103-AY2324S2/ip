package chatbot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

  private String description;
  private boolean isDone;

  public Task(String description) {
    assert description != null &&
    !description.trim().isEmpty() : "Task description cannot be null or empty";
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

  public String getDescription() {
    return description;
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
    assert dateString != null : "Deadline date cannot be null";
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
    assert from != null && to != null : "Event times cannot be null";
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return ("[E]" + super.toString() + " (from: " + from + "to: " + to + ")");
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
  private ScrollPane scrollPane;
  private VBox dialogContainer;
  private TextField userInput;
  private Button sendButton;
  private Scene scene;

  private Image user = new Image(
    this.getClass().getResourceAsStream("/images/DaUser.png")
  );
  private Image duke = new Image(
    this.getClass().getResourceAsStream("/images/DaDuke.png")
  );

  public Duke() {
    this.st = new Storage("./data/task.txt");
    ArrayList<Task> tasks = st.loadTasks();
    this.myList = new TaskList(tasks);
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

  /**
   * Iteration 1:
   * Creates a label with the specified text and adds it to the dialog container.
   * @param text String containing text to add
   * @return a label with the specified text that has word wrap enabled.
   */
  private Label getDialogLabel(String text) {
    // You will need to import `javafx.scene.control.Label`.
    Label textToAdd = new Label(text);
    textToAdd.setWrapText(true);

    return textToAdd;
  }

  public String getResponse(String input) {
    String[] words = input.split("\\s+", 2);
    String detail = words.length > 1 ? words[1] : "";
    return this.p.parseThrough(input, words, detail, this.myList, this.st);
  }
}
