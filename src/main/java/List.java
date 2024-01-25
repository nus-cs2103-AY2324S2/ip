import java.util.ArrayList;

public class List {
    private ArrayList<String> list;
    public List() {
        list = new ArrayList<String>();
    }
    private String horizontalLine = "______________________________________________ \n";

    public void printList() {
        if (list.isEmpty()) {
            System.out.println(horizontalLine + "The task list is currently empty! Add tasks!\n" + horizontalLine);
        }

        System.out.println(horizontalLine);
        for (int i = 0; i < list.size(); i++) {
            String task = list.get(i);
            int index = i + 1;
            System.out.println(index + ". " + task);
        }
        System.out.println(horizontalLine);
    }

    public void addTask(String task) {
        list.add(task);
    }
}
