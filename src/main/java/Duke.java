import java.util.ArrayList;
import java.util.Scanner;

class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Jelly");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            System.out.println("--------------------");
            System.out.println(input);
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (input.startsWith("do ")) {
                int index = Integer.parseInt(input.substring(3));
                if (index < tasks.size()) {
                    tasks.get(index).markAsDone();
                    System.out.println("Marked as done: " + tasks.get(index));
                }
            } else if (input.startsWith("undo ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index < tasks.size()) {
                    tasks.get(index).markAsNotDone();
                    System.out.println("Marked as not done: " + tasks.get(index));
                }
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }

        scanner.close();
    }
}
