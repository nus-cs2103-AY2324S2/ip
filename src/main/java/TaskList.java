import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        Task newTask = null;
        if (task.startsWith("todo")) {
            newTask = new ToDo(task.split("todo ")[1]);
        } else if (task.startsWith("deadline")) {
            String[] deadline = task.split("deadline ")[1].split(" /by ");
            newTask = new Deadline(deadline[0], deadline[1]);
        } else if (task.startsWith("event")) {
            String[] event = task.split("event ")[1].split(" /");
            newTask = new Event(event[0], event[1], event[2]);
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            return;
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task: \n" + newTask.toString() + "\nNow you have "
                + tasks.size() + " tasks in the list.\n");
    }

    public void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
        System.out.println();
    }

    public void markTaskAsDone(int index) {
        tasks.get(index - 1).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        tasks.get(index - 1).unmarkDone();
    }
}