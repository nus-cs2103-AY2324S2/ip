import java.util.ArrayList;

public class Todo {
    private ArrayList<Task> list = new ArrayList<>();
    public void returnList() {
        for (int i = 1; i < list.size()+1; i++) {
            System.out.println(i + "." + "[" + list.get(i-1).getStatus() + "]" + list.get(i-1));
        }
        return;
    }
    public void addTodo(Task item) {
        list.add(item);
    }
    public void mark(int i) {
        this.list.get(i-1).markDone();
        this.returnList();
    }
    public void unmark(int i) {
        this.list.get(i-1).markUndone();
        this.returnList();
    }
}