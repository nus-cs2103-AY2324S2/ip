import java.util.ArrayList;
import java.util.List;

public class Todo {

    private List<String> list;

    public Todo() {
        this.list = new ArrayList<>(100);
    }

    public String add(String item) {
        this.list.add(item);
        return "added: " + item;
    }

    public String[] getList() {
        List<String> list = this.list;
        for (int i = 0; i < list.size(); i++) {
            list.set(i, i+1 + ". " + list.get(i));
        }
        return list.toArray(new String[0]);
    }
}
