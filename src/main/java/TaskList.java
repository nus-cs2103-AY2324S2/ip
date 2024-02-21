import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    public void displayList() {
        System.out.println("____________________________________________________________");

        if (items.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < items.size(); i++) {
                Task task = items.get(i);
                System.out.println(((i + 1) + "." + task));
            }
        }
        System.out.println("____________________________________________________________");
    }

    public void markTask(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a task number.");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= items.size()) {
            throw new IndexOutOfBoundsException("Please check how many tasks are there in your list.");
        }
        items.get(taskIndex).markAsDone();
        System.out.println("Nice, I've marked this task as done for you:");
        System.out.println((items.get(taskIndex)));
    }

    public void unmarkTask(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a task number.");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= items.size()) {
            throw new IndexOutOfBoundsException("Please check how many tasks are there in your list.");
        }
        items.get(taskIndex).markAsUndone();
        System.out.println("Nice, I've marked this task as undone for you:");
        System.out.println((items.get(taskIndex)));
    }
    public void addTask(String userInput) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("The description of a task cannot be empty.");
        }
        String command = parts[0].toLowerCase();
        String taskInfo = parts[1];

        Task newTask;

        switch (command) {
            case "todo":
                newTask = new ToDo(taskInfo);
                break;
            case "deadline":
                String[] details = taskInfo.split("/by", 2);
                if (details.length < 2) {
                    throw new IllegalArgumentException("Invalid format for deadline, please provide a deadline using /by.");
                }
                String deadlineDescription = details[0].trim();
                String deadline = details[1].trim();
                newTask = new Deadline(deadlineDescription, deadline);
                break;
            case "event":
                String[] taskDetails = taskInfo.split("/from", 2);
                if (taskDetails.length < 2) {
                    throw new IllegalArgumentException("Invalid format for event, please provide event details by using /from and /to.");
                }
                String eventDescription = taskDetails[0].trim();
                String dateTimeDetails = taskDetails[1].trim();
                String[] dateTimeSplit = dateTimeDetails.split("/to", 2);
                if (dateTimeSplit.length < 2) {
                    throw new IllegalArgumentException("Invalid format for event, please provide event details by using /from and /to.");
                }
                String from = dateTimeSplit[0].trim();
                String until = dateTimeSplit[1].trim();
                newTask = new Event(eventDescription, from, until);
                break;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't understand what you intend to do.");
        }

        items.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + items.size() + " tasks in the list.");
    }
    public void deleteTask(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide the task number to delete.");
        }

        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= items.size()) {
            throw new IndexOutOfBoundsException("Invalid task number. Please check how many tasks your have in the list.");
        }

        Task removedTask = items.remove(taskIndex);
        System.out.println("---------------------------");
        System.out.println("I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + items.size() + " task(s) left in the list. ");
        System.out.println("---------------------------");
    }

    public ArrayList<String> getFileStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(item.toFileFormat());
        }
        return ret;
    }
}
