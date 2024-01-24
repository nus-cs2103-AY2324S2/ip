package duke.storage;

import duke.exceptions.StorageFullException;

/**
 * The UI CLI class handles storing of elements required for
 * the application
 *
 * @author Ryan NgWH
 */
public class Storage {
  /**
   * Current index of the array
   */
  private static int index = 0;

  /**
   * Limit of the storage array
   */
  private static final int LIMIT = 100;

  /**
   * Array used to store objects for the application
   */
  private static String[] storageArray = new String[LIMIT];

  /**
   * Method to store items for the application
   *
   * @param item String to store
   */
  public static void storeItem(String item) throws StorageFullException {
    // Throw exception if storage is full
    if (index >= LIMIT) {
      throw new StorageFullException("ERROR: Storage full, item will not be stored");
    }

    // Add item to storage
    storageArray[index] = item;
    index++;
  }

  /**
   * Method to print all items in storage to standard output
   */
  public static void listItems() {
    for (int i = 0; i < index; i++) {
      System.out.println(String.format("1. %s", storageArray[i]));
    }
  }
}
