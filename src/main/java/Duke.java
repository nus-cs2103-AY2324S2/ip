import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        //Greetings
        String logo = "Tommy";
        String divider = "____________________________";
        System.out.println(divider);
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println(divider);


        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {

            if (userInput.contains("unmark")) {
                // Unmark the tasks
                System.out.println(divider);

                int indexToUnmark = Integer.parseInt(userInput.substring(7));
                Task taskToUnmark = tasks.get(indexToUnmark - 1);
                taskToUnmark.isDone = false;

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskToUnmark.getStatusIcon() + " " + taskToUnmark.description);

                System.out.println(divider);

            } else if (userInput.contains("mark")) {
                System.out.println(divider);

                int indexToMark = Integer.parseInt(userInput.substring(5));
                Task taskToMark = tasks.get(indexToMark - 1);
                taskToMark.isDone = true;

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskToMark.getStatusIcon() + " " + taskToMark.description);

                System.out.println(divider);

            } else if (userInput.equals("list")) {
                // listing
                System.out.println(divider);

                for (Task task: tasks) {
                    System.out.println(task.index + "." + task.getStatusIcon() + " " + task.description);
                }

                System.out.println(divider);

            } else {
                // Adding tasks
                System.out.println(divider);

                Task task = new Task(userInput);
                tasks.add(task);
                System.out.println("added: " + userInput);

                System.out.println(divider);

            }

            userInput = scanner.nextLine();
        }

        //Farewell
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);

        scanner.close();
    }
}

