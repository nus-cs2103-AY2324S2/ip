import java.util.ArrayList;

public class Todo {
    private ArrayList<String> list = new ArrayList<>();
    public void returnList() {
        for (int i = 1; i < list.size()+1; i++) {
            System.out.println(i + "." + list.get(i-1));
        }
        return;
    }
    public void addTodo(String item) {
        list.add(item);
    }
}
