package duke;

import java.io.Serializable;
import java.util.ArrayList;

import duke.item.Deadline;
import duke.item.Event;
import duke.item.Item;
import duke.item.ToDo;

/**
 * ItemList is the class whose instance represents the
 * arrangement of Item objects. It can be manipulated by commands
 * to add, delete, mark done or mark undone Items in the list. The
 * working instance of Itemlist is instantiated when the main method
 * of Duke is run.
 */
public class ItemList implements Serializable {
    private final ArrayList<Item> listOfItems = new ArrayList<>();
    private int itemCount = 0;

    public int getItemCount() {
        return itemCount;
    }

    public ArrayList<Item> getList() {
        return this.listOfItems;
    }

    /**
     * Adds a To-do object to the ItemList
     *
     * @param name is a string array containing information needed
     *             to construct the new To-do instance.
     * @return a string that confirms that a To-do has been added.
     */
    public String addToDo(String[] name) {
        listOfItems.add(itemCount, new ToDo(name));
        itemCount++;
        return listOfItems.get(itemCount - 1).addMessage(itemCount);
    }

    /**
     * Adds an Event object to the ItemList
     *
     * @param info is a string array containing information needed
     *             to construct the new event instance.
     * @return a string that confirms that an event has been added.
     * @throws CustomExceptions from nested method calls to be passed
     *                          to the AddCommand class for handling.
     */
    public String addEvent(String[] info) throws CustomExceptions {
        listOfItems.add(itemCount, new Event(info));
        itemCount++;
        return listOfItems.get(itemCount - 1).addMessage(itemCount);
    }

    /**
     * Adds a Deadline object to the ItemList
     *
     * @param info is a string array containing information needed
     *             to construct the new deadline instance.
     * @return a string that confirms that a deadline has been added.
     * @throws CustomExceptions from nested method calls to be passed
     *                          to the AddCommand class for handling.
     */
    public String addDeadline(String[] info) throws CustomExceptions {
        listOfItems.add(itemCount, new Deadline(info));
        itemCount++;
        return listOfItems.get(itemCount - 1).addMessage(itemCount);
    }

    /**
     * Removes an Item object at a given index from the ItemList.
     *
     * @param index is an integer greater than 0 that refers
     *              to the Item object to be removed.
     * @return a String containing the string representation of
     *     the item that has been removed.
     * @throws CustomExceptions.NoSuchIndexException when no such index
     *                                               exists in the ItemList
     */
    public String removeItem(int index) throws CustomExceptions {
        String s;
        int initial = itemCount;
        try {
            itemCount--;
            s = listOfItems.get(index - 1).removeMessage(itemCount);
            listOfItems.remove(index - 1);
            assert itemCount == initial - 1;
        } catch (IndexOutOfBoundsException e) {
            itemCount++; // reverse decrement in the event of error
            assert itemCount == initial;
            throw new CustomExceptions.NoSuchIndexException(
                    "Index out of bounds, there is no event with such an index");
        }
        return s;
    }

    /**
     * Finds all Item objects in the ItemList instance that contain
     * the search string in full.
     *
     * @param s is the search string used to search for relevant items.
     * @return a string of items separated by newlines that contain
     *     the given search string s.
     */
    public String find(String s) {
        StringBuilder out = new StringBuilder();
        int index = 1;
        for (Item i : listOfItems) {
            if (i.getName().contains(s)) {
                out.append(index).append(".").append(i.toString()).append("\n");
                index++;
            }
        }
        if (out.toString().equals("")) {
            return "No tasks contain the search string: " + s;
        } else {
            return out.toString();
        }
    }

    @Override
    public String toString() {
        int c = 0;
        StringBuilder out = new StringBuilder();
        while (c < listOfItems.size()) {
            out.append(c + 1).append(".").append(listOfItems.get(c).toString()).append("\n");
            c++;
        }
        return out.toString().trim();
    }
}
