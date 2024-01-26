import java.util.ArrayList;
import java.util.HashMap;
public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public void addTask(String taskString) {
        String[] temp = taskString.split(" ", 2);
        String taskType = temp[0];
        String taskInfo = temp.length > 1 ? temp[1] : "";
        switch (taskType) {
            case "todo":
                addTask(new ToDo(taskInfo));
                break;
            case "deadline":
                String[] temp2 = taskInfo.split(" /", 2);
                addTask(new Deadline(temp2[0], temp2[1].substring(3)));
                break;
            case "event":
                String[] temp3 = taskInfo.split(" /", 3);
                addTask(new Event(temp3[0], temp3[1].substring(5), temp3[2].substring(3)));
                break;
            // can account for errors here
        }
    }
    private Task getTask(int i) {
        return this.list.get(i);
    }
    private void addTask(Task task) {
        System.out.printf("I have added the following task:\n%s\n", task.getRep());
        this.list.add(task);
        System.out.printf("You now have %d task(s) in your list!%n", list.size());
    }
    public void printList() {
        for (int i = 1; i < this.list.size() + 1; i++) {
            System.out.println(i + ". " + getTask(i - 1).getRep());
        }
    }
    public void mark(int i) {
        Task task = getTask(i - 1);
        task.mark();
        System.out.println(task.getRep());
    } // Handle index exception here maybe
    public void unmark(int i) {
        Task task = getTask(i - 1);
        task.unmark();
        System.out.println(task.getRep());
    }
}
