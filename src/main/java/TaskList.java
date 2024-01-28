import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.TaskStatus;

class TaskList {
    private List<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<Task>();
        this.storage = storage;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void add(Task instruction) {
        tasks.add(instruction);
        System.out.println("Got it. I've added this task:\n\t" + instruction.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        this.storage.save(tasks);
    }

    public void mark(int index) {
        Task task = tasks.get(index - 1);
        task.setIsDone(TaskStatus.COMPLETE);
        System.out.println("Nice! I've marked this task as done:\n\t" + task.toString());
        this.storage.save(tasks);
    }

    public void unmark(int index) {
        Task task = tasks.get(index - 1);
        task.setIsDone(TaskStatus.INCOMPLETE);
        System.out.println("OK, I've marked this task as not done yet:\n\t" + task.toString());
        this.storage.save(tasks);
    }

    public void delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        this.storage.save(tasks);
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i+1) + "." + tasks.get(i).toString());
        }
    }

    public LocalDate convertDate(String dateStr) {
        // assuming that byDate is in yyyy-mm-dd
        String[] dateArr = dateStr.split("-");
        return LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
    }
}