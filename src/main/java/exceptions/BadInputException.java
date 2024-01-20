package exceptions;

public class BadInputException extends RuntimeException {
  protected final String expected;
  protected final String example;
  protected final String got;

  public BadInputException(String message, String expected, String example, String got) {
    super(message);
    this.expected = expected;
    this.example = example;
    this.got = got;
  }

  @Override
  public String getMessage() {
    String message = super.getMessage() + "\n";
    String badInputMessage = String.format("Bad input provided: %s\n", got == null || got.isEmpty() ? "<empty>" : got);
    String expectedMessage = expected == null ? "" : String.format("Expected: %s\n", expected);
    String exampleMessage = example == null ? "" : String.format("Example: %s\n", example);
    return String.format("%s%s%s%s",
      badInputMessage,
      message,
      expectedMessage,
      exampleMessage
    );
  }
}
