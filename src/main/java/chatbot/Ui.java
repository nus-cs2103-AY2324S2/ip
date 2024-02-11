package chatbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Ui class manages the user interface for the chatbot. It handles reading user input,
 * printing responses to the console, and interacting with other components like Parser and TaskList
 * to carry out user commands. This class is responsible for starting the chatbot and handling
 * the loop of user interactions.
 */
public class Ui {

  protected BufferedReader br = new BufferedReader(
    new InputStreamReader(System.in)
  );

  protected PrintWriter pw = new PrintWriter(System.out, true);

  protected Parser p = new Parser();

  protected TaskList tl;

  /**
   * Constructs a Ui object with a reference to a TaskList.
   *
   * @param tl The TaskList that the Ui will interact with.
   */

  public Ui(TaskList tl) {
    this.tl = tl;
  }

  String end = "____________________________________________________________\n";

  /**
   * Starts the chatbot by displaying a welcome message to the user. This method should be called
   * to initiate the interaction with the user.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void start() throws IOException {
    String prompt =
      "Hello! I'm TFamilyBot\n" + "What can I do for you? \n" + end;
    assert prompt != null : "Prompt should not be null";
    pw.println(prompt);
  }

  /**
   * Handles a single round of user interaction. It reads the user's input, processes it, and
   * prints the response. The method returns a boolean indicating whether the interaction should
   * continue (true) or if it should end (false, when the user inputs 'bye').
   *
   * @param st The Storage object used for saving and retrieving task data.
   * @return false if the user inputs 'bye' to end the interaction, true otherwise.
   * @throws IOException If an I/O error occurs.
   */
  public boolean reply(Storage st) throws IOException {
    String io = br.readLine().trim();
    assert io != null : "Input read from BufferedReader should not be null";
    String[] words = io.split("\\s+", 2);
    String detail = words.length > 1 ? words[1] : "";

    pw.println(end);

    boolean done = p.isItDone(words);

    String answer = p.parseThrough(io, words, detail, this.tl, st);

    pw.println(answer);
    pw.println(end);

    if (done) {
      return false;
    }

    return true;
  }
}
