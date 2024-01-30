import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public void addTask(String task) throws DukeException {
        Task newTask = null;
        if (task.startsWith("todo")) {
            if (task.split("todo ").length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. \n");
            } else {
                newTask = new ToDo(task.split("todo ")[1]);
            }
        } else if (task.startsWith("deadline")) {
            String[] deadline = task.split("deadline ")[1].split(" /by ");
            newTask = new Deadline(deadline[0], deadline[1]);
        } else if (task.startsWith("event")) {
            String[] event = task.split("event ")[1].split(" /");
            newTask = new Event(event[0], event[1], event[2]);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n");
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task: \n" + newTask.toString() + "\nNow you have "
                + tasks.size() + " tasks in the list.\n");

        updateStorage();
    }

    public void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
        System.out.println();
    }

    public void markTaskAsDone(int index) throws DukeException {
        tasks.get(index - 1).markAsDone();
        try {
            updateStorage();
        } catch (DukeException e) {
            throw new DukeException("Error updating storage");
        }
    }

    public void unmarkTaskAsDone(int index) throws DukeException {
        tasks.get(index - 1).unmarkDone();
        try {
            updateStorage();
        } catch (DukeException e) {
            throw new DukeException("Error updating storage");
        }
    }

    public void deleteTask(int index) throws DukeException {
        System.out.println("Noted. I've removed this task: \n" + tasks.get(index - 1).toString() + "\nNow you have "
                + (tasks.size() - 1) + " tasks in the list.\n");
        tasks.remove(index - 1);

        try {
            updateStorage();
        } catch (DukeException e) {
            throw new DukeException("Error updating storage");
        }
    }

    public void updateStorage() throws DukeException {
        try {
            storage.save(tasks);
            System.out.println("Storage updated\n");
        } catch (Exception e) {
            throw new DukeException("Error updating storage");
        }
    }
}