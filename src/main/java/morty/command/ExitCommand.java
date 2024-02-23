package morty.command;

import morty.Storage;
import morty.TaskList;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import morty.Response;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

  /**
   * Constructs a ExitCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public ExitCommand(String[] tokens) {
    super(tokens);
    this.exit = true;
  }

  /**
   * Executes the ExitCommand.
   *
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the ExitCommand.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    // Delay for 2 seconds before exiting the program
    new Timer().schedule(
        new TimerTask() {
          @Override
          public void run() {
            Platform.exit();
          }
        },
        1000);
    return ui.showGoodbye();
  }
}
