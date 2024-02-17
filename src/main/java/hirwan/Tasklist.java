package hirwan;

import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    static List<String> tasks = new ArrayList<>();
    public Tasklist(List<String> List) {
        this.tasks = List;
    }
    public String get(int index) {
        return tasks.get(index);
    }

    public void add(String input) {
        tasks.add(input);
    }

    public void printList() {
        for (String element : tasks) {
            Ui.output(tasks.indexOf(element) + 1 + element);
        }
    }

    public void delete(int index) {
            tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<String> getList() {
        return tasks;
    }

    public void set(int Index, String input) {
        tasks.set(Index, input);
    }

    public void deleteList() {
        tasks.removeAll(tasks);
    }

    public Tasklist copyList() {
        List<String> copiedlist = new ArrayList<>();
        copiedlist.addAll(this.tasks);
        Tasklist tasklist = new Tasklist(copiedlist);
        return tasklist;
    }
}
