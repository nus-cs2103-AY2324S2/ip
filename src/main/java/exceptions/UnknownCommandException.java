package exceptions;

public class UnknownCommandException extends RuntimeException {
  protected final String got;
  protected final String[] knownCommands;
  public UnknownCommandException(String message, String got, String... knownCommands) {
    super(message);
    this.got = got;
    this.knownCommands = knownCommands;
  }

  @Override
  public String getMessage() {
    String message = super.getMessage() + "\n";
    String badCommandMessage = String.format("Unknown command provided: %s\n", got);
    String knownCommandsMessage = knownCommands == null ? "" : String.format("Known commands: %s\n", String.join(", ", knownCommands));
    return String.format("%s%s%s",
      badCommandMessage,
      message,
      knownCommandsMessage
    );
  }
}
