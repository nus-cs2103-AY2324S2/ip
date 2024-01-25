import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Task class
    static class Task {
        String description;
        boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return (this.isDone ? "[X] " : "[ ] ") + this.description;
        }
    }

    public static void exitMessage() {
        String divider = "-----------------------------------------------------";
        System.out.println(divider);
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }

    public static void greeting() {
        String divider = "-----------------------------------------------------";
        System.out.println(divider);
        String welcomeMessage = "Hello! I'm ShaunBot";
        String greet = "What can I do for you?";
        System.out.println(welcomeMessage);
        System.out.println(greet);
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String divider = "-----------------------------------------------------";
        greeting();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                }
                System.out.println(divider);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks.get(index).markAsDone();
                System.out.println(divider);
                System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
                System.out.println(divider);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks.get(index).unmarkAsDone();
                System.out.println(divider);
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
                System.out.println(divider);
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(divider);
                System.out.println("added: " + input);
                System.out.println(divider);
            }
        }

        exitMessage();
    }
}
