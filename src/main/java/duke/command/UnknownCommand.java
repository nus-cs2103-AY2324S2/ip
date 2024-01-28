package duke.command;

public class UnknownCommand extends Command {
  private final String command;

  public UnknownCommand(String command) {
    this.command = command;
  }

  @Override
  public String execute() {
    return String.format("I'm sorry, but I don't know what \"%s\" means.\n", this.command);
  }
}
