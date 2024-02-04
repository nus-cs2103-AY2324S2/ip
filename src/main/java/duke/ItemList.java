package duke;

import java.io.Serializable;
import java.util.ArrayList;

import duke.item.Deadline;
import duke.item.Event;
import duke.item.Item;
import duke.item.ToDo;

public class ItemList implements Serializable {
    private ArrayList<Item> listOfItems = new ArrayList<Item>();
    private int itemCount = 0;

    public int getItemCount() {
        return itemCount;
    }

    public ArrayList<Item> getList() {
        return this.listOfItems;
    }

    public String addToDo(String[] name) {
        listOfItems.add(itemCount, new ToDo(name));
        itemCount++;
        return listOfItems.get(itemCount - 1).addMessage(itemCount);
    }

    public String addEvent(String[] info) throws CustomExceptions {
        listOfItems.add(itemCount, new Event(info));
        itemCount++;
        return listOfItems.get(itemCount - 1).addMessage(itemCount);
    }

    public String addDeadline(String[] info) throws CustomExceptions {
        listOfItems.add(itemCount, new Deadline(info));
        itemCount++;
        return listOfItems.get(itemCount - 1).addMessage(itemCount);
    }

    public String removeItem(int index) throws CustomExceptions {
        String s = "";
        try {
            itemCount--;
            s = listOfItems.get(index - 1).removeMessage(itemCount);
            listOfItems.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            itemCount++; // reverse decrement in the event of error
            throw new CustomExceptions.NoSuchIndexException(
                    "Index out of bounds, there is no event with such an index");
        }
        return s;
    }

    public String find(String s) {
        StringBuilder out = new StringBuilder();
        int index = 1;
        for (Item i : listOfItems) {
            if (i.getName().contains(s)) {
                out.append(index).append(".").append(i.toString()).append("\n     ");
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
            out.append(c + 1).append(".").append(listOfItems.get(c).toString()).append("\n     ");
            c++;
        }
        return out.toString().trim();
    }
}
