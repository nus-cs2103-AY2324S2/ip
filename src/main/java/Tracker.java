import java.util.ArrayList;

public class Tracker {

    private ArrayList<String> stringList;

    public Tracker(){
        stringList = new ArrayList<>();
    }

    public void addElement(String element) {
        stringList.add(element);
        System.out.println("added: " + element);
    }

    public void listElements() {
        int index = 0;
        for (String element: stringList) {
            index++;
            System.out.println(index + ". " + element);
        }
        if (stringList.size() == 0) {
            System.out.println("No items in list!");
        }
    }



}
