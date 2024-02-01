import java.util.ArrayList;
import java.util.Scanner;
public class TaskList {
    ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(Scanner sc) {
        this.taskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String[] split = task.split(",");
            String taskType = split[0];
            Boolean isDone = split[1].equals("X");
            String desc = split[2];
            if (taskType.equals("T")) {
                ToDo todo = new ToDo(desc);
                this.taskList.add(todo);
                if (isDone) {
                    todo.mark();
                }
            } else if (taskType.equals("D")) {
                String by = split[3];

                Deadline deadline = new Deadline(desc, by);
                this.taskList.add(deadline);
                if (isDone) {
                    deadline.mark();
                }
            } else {
                String from = split[3];
                String to = split[4];
                Event event = new Event(desc, from, to);
                this.taskList.add(event);
                if (isDone) {
                    event.mark();
                }
            }

        }
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task remove(int index) {
        return this.taskList.remove(index);
    }
    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        return this.taskList.toString();
    }
}
