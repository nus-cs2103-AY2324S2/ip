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
  private static Task[] storageArray = new Task[LIMIT];

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
    storageArray[index] = new Task(item);
    index++;
  }

  /**
   * Method to print all items in storage to standard output
   */
  public static void listItems() {
    for (int i = 0; i < index; i++) {
      System.out.println(String.format("%d.%s", i + 1, storageArray[i].toString()));
    }
  }

  /**
   * Method to mark an item in storage
   *
   * @param markIndex Index of the item to mark
   */
  public static void markItem(int markIndex) {
    try {
      storageArray[markIndex].mark();
      System.out.println("Nice! I've marked this task as done:");
      System.out.println(String.format("  %s", storageArray[markIndex].toString()));
    } catch (IndexOutOfBoundsException e) {
      System.out.println("ERROR: Item cannot be marked - Index out of bounds");
    } catch (NullPointerException e) {
      System.out.println("ERROR: Item cannot be marked - Index does not contain an item");
    }
  }

  /**
   * Method to unmark an item in storage
   *
   * @param unmarkIndex Index of the item to mark
   */
  public static void unmarkItem(int unmarkIndex) {
    try {
      storageArray[unmarkIndex].unmark();
      System.out.println("OK, I've marked this task as not done yet:");
      System.out.println(String.format("  %s", storageArray[unmarkIndex].toString()));
    } catch (IndexOutOfBoundsException e) {
      System.out.println("ERROR: Item cannot be unmarked - Index out of bounds");
    } catch (NullPointerException e) {
      System.out.println("ERROR: Item cannot be unmarked - Index does not contain an item");
    }
  }
}
