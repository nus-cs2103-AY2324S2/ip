package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void showWelcomeMessage_correctOutput() {
    Ui ui = new Ui();
    ui.showWelcomeMessage();
    assertEquals("Hello! I'm GeePeeTee\nWhat can I do for you?\n", outContent.toString());
  }

  @Test
  public void showErrorMessage_correctOutput() {
    Ui ui = new Ui();
    ui.showErrorMessage("Test error message");
    assertEquals("Oops! An error occurred:\nTest error message\n", outContent.toString());
  }

  @Test
  public void showTaskMessage_correctOutput() {
    Ui ui = new Ui();
    Task task = new Task("Test task");
    ui.showTaskMessage(task);
    assertEquals("[ ] Test task\n", outContent.toString());
  }
}
