import java.util.List;

public class MyList implements Action {
    private final TaskList mylist;

    public MyList(TaskList mylist) {
        this.mylist = mylist;
    }

    @Override
    public String response() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Here are the tasks in your list:\n");

        for (int i = 0; i < mylist.size(); i++) {
            Task task = mylist.get(i);
            stringBuilder.append("  ").append(i + 1).append(". ").append(task).append("\n");
        }

        return stringBuilder.toString();
    }

}

