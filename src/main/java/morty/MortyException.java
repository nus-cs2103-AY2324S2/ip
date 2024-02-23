package morty;

/**
 * MortyException is a custom exception class for Morty.
 * It is used to handle exceptions that occur during the execution of Morty.
 */
public class MortyException extends Exception {
  public MortyException(String message) {
    super(message);
  }
}
