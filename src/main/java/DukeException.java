public class DukeException extends Exception {
  public DukeException(String message) {
    super(message);
  }

  public void printErrorMessage() {
    System.out.println(getMessage());
  }
}
