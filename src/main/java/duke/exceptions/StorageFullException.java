package duke.exceptions;

/**
 * The StorageFullException class provices an exception.
 *
 * @author Ryan NgWH
 */
public class StorageFullException extends Exception {
  /**
   * Constructor for a StorageFullException
   * 
   * @param errorMessage Error message
   */
  public StorageFullException(String errorMessage) {
    super(errorMessage);
  }
}
