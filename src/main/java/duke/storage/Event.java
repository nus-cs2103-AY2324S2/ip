package duke.storage;

/**
 * The Event class defines a 'Event' task used for the application
 *
 * @author Ryan NgWH
 */
public class Event extends Task {
  /**
   * Start date/time of the Event task
   */
  private String startDate;

  /**
   * End date/time of the Event task
   */
  private String endDate;

  /**
   * Constructor for a Event object
   *
   * @param description Description of the event
   * @param startDate   Start date of the event
   * @param endDate     End date of the event
   */
  public Event(String description, String startDate, String endDate) {
    super(description);
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
