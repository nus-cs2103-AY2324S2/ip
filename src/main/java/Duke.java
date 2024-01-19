import java.util.*;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String markStatus(int index) {
        isDone = true;
        return "------------------------------- \n" +
                "Nice! I've marked this as done: \n " + "[" + getStatusIcon() + "] " + description +
                "\n-------------------------------";
    }

    public String unmarkStatus(int index) {
        isDone = false;
        return "------------------------------- \n" +
                "OK, I've marked this task as not done yet: \n " + "[" + getStatusIcon() + "] " + description +
                "\n-------------------------------";
    }
}

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------------");
        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?");
        System.out.println("-------------------------------");

        while (true) {
            String input = scan.nextLine().trim();
            String[] words = input.split("\\s+");

            if (words[0].equalsIgnoreCase("bye")) {
                System.out.println("-------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------------");
                break;
            } else if (words[0].equalsIgnoreCase("list")) {
                System.out.println("-------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ".[" + (task != null ? task.getStatusIcon() : "") + "] " + task.description);
                }
                System.out.println("-------------------------------");
            } else if (words[0].equalsIgnoreCase("mark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    Task task = tasks.get(index);
                    System.out.println(task.markStatus(index));
                } else {
                    System.out.println("-------------------------------");
                    System.out.println("You have not created a task " + index + " yet");
                    System.out.println("-------------------------------");
                }
            } else if (words[0].equalsIgnoreCase("unmark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    Task task = tasks.get(index);
                    System.out.println(task.unmarkStatus(index));
                } else {
                    System.out.println("-------------------------------");
                    System.out.println("You have not created a task " + index + " yet");
                    System.out.println("-------------------------------");
                }
            } else {
                System.out.println("-------------------------------");
                System.out.println("added: " + input);
                tasks.add(new Task(input));
                System.out.println("-------------------------------");
            }
        }
    }
}
