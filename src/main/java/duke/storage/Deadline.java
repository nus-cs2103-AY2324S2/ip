package duke.storage;

/**
 * The Deadline class defines a 'Deadline' task used for the application
 *
 * @author Ryan NgWH
 */
public class Deadline extends Task {
  /**
   * Due date/time of the deadline task
   */
  private String dueDate;

  /**
   * Constructor for a Todo object
   *
   * @param description Description of the todo task
   */
  public Deadline(String description, String dueDate) {
    super(description);
    this.dueDate = dueDate;
  }
}
