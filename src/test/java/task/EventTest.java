package task;

import org.junit.jupiter.api.Test;

import exception.InvalidDateException;
import exception.InvalidTaskFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
  @Test
  public void eventToString_correctFormat() {
    String eventStartDate = "2024-01-01";
    String eventEndDate = "2024-01-02";
    String eventDescription = "Event Test";
    String inputString = "event " + eventDescription + " /from " + eventStartDate + " /to " + eventEndDate;
    try {
      Event newEvent = Event.createFromInput(inputString);
      String expected = "[E][ ] Event Test (from: Jan 01 2024 to: Jan 02 2024)";
      assertEquals(expected, newEvent.toString());
    } catch (Exception e) {
      fail("An unexpected exception occurred: " + e.getMessage());
    }
  }

  @Test
  public void eventToString_incorrectFormat() {
    String eventStartDate = "2024-01-01";
    String eventDescription = "Event Test";
    // Missing end date
    String inputStringMissingEndDate = "event " + eventDescription + " /from " + eventStartDate;
    // Missing description
    String inputStringMissingDescription = "event /from " + eventStartDate + " /to " + eventStartDate;
    // Missing start date
    String inputStringMissingStartDate = "event " + eventDescription + " /to " + eventStartDate;
    String inputStringMissingPrefix = eventDescription + " /from " + eventStartDate + " /to " + eventStartDate;
    assertThrows(InvalidTaskFormatException.class, () -> {
      Event.createFromInput(inputStringMissingEndDate);
    });
    assertThrows(InvalidTaskFormatException.class, () -> {
      Event.createFromInput(inputStringMissingDescription);
    });
    assertThrows(InvalidTaskFormatException.class, () -> {
      Event.createFromInput(inputStringMissingStartDate);
    });
    assertThrows(InvalidTaskFormatException.class, () -> {
      Event.createFromInput(inputStringMissingPrefix);
    });
  }

  @Test
  public void eventToString_incorrectDateFormat() {
    String eventStartDate = "20240101";
    String eventEndDate = "20240102";
    String eventDescription = "Event Test";
    String inputString = "event " + eventDescription + " /from " + eventStartDate + " /to " + eventEndDate;
    assertThrows(InvalidDateException.class, () -> {
      Event.createFromInput(inputString);
    });
  }
}
