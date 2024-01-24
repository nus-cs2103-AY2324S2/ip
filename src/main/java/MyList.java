import java.util.List;

public class MyList implements Action {
    private final TaskList mylist;

    public MyList(TaskList mylist) {
        this.mylist = mylist;
    }

    @Override
    public void response() {
        System.out.println("  Here are the tasks in your list:");
        //List<Task> tasks = mylist.getTasks();
        for (int i = 0; i < mylist.size(); i++) {
            Task task = mylist.get(i);
            System.out.println("  " + (i + 1) + ". " + task);
        }
    }
}

