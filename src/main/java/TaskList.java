import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    public void markAsDone(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        t.markAsDone();

    }

    public void markAsUndone(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        t.markAsNotDone();

    }

    public Task addToDo(String taskDesc) {
        Task t = new ToDos(taskDesc);
        this.taskList.add(t);
        return t;
    }

    public Task addEvent(String taskDesc, String from, String to) {
        Task t = new Events(taskDesc, from, to);
        this.taskList.add(t);
        return t;
    }

    public Task addDeadline(String taskDesc, String by) {
        Task t = new Deadlines(taskDesc, by);
        this.taskList.add(t);
        return t;
    }



    public void printTask(int idx) {
        System.out.printf("%s\n", this.taskList.get(idx).toString());
    }

    public void printAddTask(Task t) {
        System.out.println("Got it, I've added this task:");
        System.out.printf("%s\n", t);
        System.out.printf("Now you have %d tasks in the list\n", taskList.size());


    }

    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "Congrats! Your list is empty. Go enjoy your day!";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        IntStream.iterate(1, x -> x + 1)
                .limit(this.taskList.size())
                .forEachOrdered(i -> {
                    String s = String.format("%d. %s\n", i, this.taskList.get(i - 1));
                    sb.append(s);
                });
        return sb.toString().stripTrailing();

    }
}
