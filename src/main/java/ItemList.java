public class ItemList {
    private Item[] listOfItems = new Item[100];
    private int itemCount = 0;

    public int getItemCount() {
        return itemCount;
    }

    public Item[] getList() {
        return this.listOfItems;
    }

    String addToDo(String[] name) {
        listOfItems[itemCount] = new ToDo(name);
        itemCount++;
        return listOfItems[itemCount-1].addMessage(itemCount);
    }

    String addEvent(String[] info) throws CustomExceptions.toBeforeFromException {
        listOfItems[itemCount] = new Event(info);
        itemCount++;
        return listOfItems[itemCount-1].addMessage(itemCount);
    }

    String addDeadline(String[] info) {
        listOfItems[itemCount] = new Deadline(info);
        itemCount++;
        return listOfItems[itemCount-1].addMessage(itemCount);
    }

    @Override
    public String toString() {
        int c = 0;
        String out = "";
        while (!(listOfItems[c] == null)) {
            out += (c+1) + "." + listOfItems[c].toString() + "\n     ";
            c++;
        }
        return out.trim();
    }
}
