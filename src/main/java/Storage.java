import java.util.ArrayList;

public class Storage {
    private ArrayList<String> list;
    public Storage() { //constructor
        this.list = new ArrayList<>();
    }

    public void addItem(String item) { //to append items to list
        list.add(item);
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) { //not last element
                UI.printResponse(i + 1, list.get(i), false);
            } else {
                UI.printResponse(i + 1, list.get(i), true);
            }
        }
    }
}
