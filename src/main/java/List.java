import java.util.ArrayList;

public class List {
    private ArrayList<Task> list;
    public List() {
        list = new ArrayList<Task>();
    }
    private String horizontalLine = "______________________________________________\n";

    public void printList() {
        if (list.isEmpty()) {
            System.out.println(horizontalLine + "The task list is currently empty! Add tasks!\n" + horizontalLine);
        }

        System.out.println(horizontalLine + "Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            int index = i + 1;
            System.out.println(index + ". " + task.printTask());
        }
        System.out.println(horizontalLine);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void markTask(int index) {
        System.out.println(horizontalLine);
        if (index >= list.size()) {
            System.out.println("Sorry that item does not exist in your list!\n");
            System.out.println(horizontalLine);
            return;
        }

        else if (index < 0) {
            System.out.println("Please input a positive task number!\n");
            System.out.println(horizontalLine);
            return;
        }

        Task currTask = list.get(index);
        currTask.markTask();
        System.out.println("Nice! I've marked this task as done\n");
        System.out.println(currTask.printTask());
        System.out.println(horizontalLine);
    }

    public void unmarkTask(int index){
        System.out.println(horizontalLine);
        if (index >= list.size()) {
            System.out.println("Sorry that item does not exist in your list!");
            System.out.println(horizontalLine);
            return;
        }

        else if (index < 0) {
            System.out.println("Please input a positive task number!\n");
            System.out.println(horizontalLine);
            return;
        }

        Task currTask = list.get(index);
        currTask.unmarkTask();
        System.out.println("Alright, I've marked this task as not done yet\n");
        System.out.println(currTask.printTask());
        System.out.println(horizontalLine);
    }
}
