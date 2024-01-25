import java.util.ArrayList;

public class Mylist {
    private ArrayList<Task> mylist = new ArrayList<>();
    public void returnList() {
        for (int i = 1; i < mylist.size()+1; i++) {
            System.out.println(i + ". " + mylist.get(i-1).toString());
        }
        return;
    }
    public void addTodo(Task item) {
        mylist.add(item);
        System.out.println("Sorry but you got more work now: " + item.toString());
    }
    public void mark(int i) {
        this.mylist.get(i-1).markDone();
        this.returnList();
    }
    public void unmark(int i) {
        this.mylist.get(i-1).markUndone();
        this.returnList();
    }
    public int len() {
        return mylist.size();
    }
}