package Bentley;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
     

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void addTodoTask(String userInput) {
        if (userInput.length() <= 5) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("looks like the description is missing");
        }
        tasks.add(new Todo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addDeadlineTask(String userInput) {
        if (userInput.length() <= 9) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        String[] parts = userInput.substring(9).split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        tasks.add(new Deadline(description, by));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addEventTask(String userInput) {
        if (userInput.length() <= 6) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        String[] parts = userInput.substring(6).split("/from");
        String description = parts[0].trim();
        String[] eventParts = parts[1].trim().split("/to");
        String from = eventParts[0].trim();
        String to = eventParts[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new IllegalArgumentException(
                    "looks like something is missing (description/ start date/ end date)");
        }
        tasks.add(new Event(description, from, to));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    
    }

    public void markAsDone(String userInput) {
        System.out.println(" Nice! I've marked this task as done:");
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        tasks.get(taskNumber - 1).markAsDone();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    

    public void markAsUndone(String userInput) {
        System.out.println(" OK, I've marked this task as not done yet:");
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        tasks.get(taskNumber - 1).markAsUndone();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    

public void deleteTask(String userInput) {
    
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
}

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
