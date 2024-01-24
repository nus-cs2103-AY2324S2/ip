import java.util.ArrayList;

public class List implements Action {
    private final ArrayList<Task> mylist;

    public List(ArrayList<Task> mylist) {
        this.mylist = mylist;
    }

    @Override
    public void response() {
        System.out.println("  Here are the tasks in your list:");
        int i = 0;
        for (Task task : mylist) {
            System.out.println("  " + (i + 1) + "." + task);
            i++;
        }
    }
}

