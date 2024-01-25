import java.util.ArrayList;

public class List {
    private ArrayList<Task> list;
    public List() {
        list = new ArrayList<Task>();
    }
    private String horizontalLine = "______________________________________________\n";

    public void printList() {
        if (list.isEmpty()) {
            System.out.println("The task list is currently empty! Add tasks!\n");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            int index = i + 1;
            System.out.println(index + ". " + task.printTask());
        }
        System.out.println("");
    }

    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it I've added this task:\n" +
                task.printTask() + "\n" +
                "You now have " + list.size() + " tasks in the list\n");
    }

    public void markTask(int index) throws InvalidArgsException {
        if (index >= list.size()) {
            throw new InvalidArgsException("Sorry that item does not exist in your list!\n");
        }

        else if (index < 0) {
            throw new InvalidArgsException("Please input a positive task number!\n");
        }

        Task currTask = list.get(index);
        currTask.markTask();
        System.out.println("Nice! I've marked this task as done");
        System.out.println(currTask.printTask()+ "\n");
    }

    public void unmarkTask(int index) throws InvalidArgsException {
        if (index >= list.size()) {
            throw new InvalidArgsException("Sorry that item does not exist in your list!\n");
        }

        else if (index < 0) {
            throw new InvalidArgsException("Please input a positive task number!\n");
        }

        Task currTask = list.get(index);
        currTask.unmarkTask();
        System.out.println("Alright, I've marked this task as not done yet");
        System.out.println(currTask.printTask() + "\n");
    }
}
