package hirwan;

import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    static List<String> List = new ArrayList<>();
    public Tasklist(List<String> List) {
        this.List = List;
    }
    public String get(int index) {
        return List.get(index);
    }

    public void add(String input) {
        List.add(input);
    }

    public void printList() {
        for (String element : List) {
            Ui.output(List.indexOf(element) + 1 + element);
        }
    }

    public void delete(int index) {
            List.remove(index);
    }

    public int size() {
        return List.size();
    }

    public List<String> getList() {
        return List;
    }

    public void set(int Index, String input) {
        List.set(Index, input);
    }
}