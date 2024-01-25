import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("What can I do for you?");

        ArrayList<Task> myList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.print(" ");

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");

            String command = parts[0];

            if (command.equals("mark")) {
                System.out.println("");
                int num = Integer.parseInt(parts[1]) - 1;

                if (num >= 0 && num < myList.size()) {
                    Task task = myList.get(num);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(task);
                } else {
                    System.out.println("Invalid task number.");
                }

            } else if (command.equals("unmark")) {
                System.out.println("");
                int num = Integer.parseInt(parts[1]) - 1;

                if (num >= 0 && num < myList.size()) {
                    Task task = myList.get(num);
                    task.markAsNotDone();
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    System.out.println(task);
                } else {
                    System.out.println("Invalid task number.");
                }

            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (Task task : myList) {
                    System.out.println((myList.indexOf(task) + 1) + "." + task);
                }

            } else if (command.equals("bye")) {
                break;

            } else {
                String combine = parts[0] + " " + parts [1];
                Task newTask = new Task(combine);
                myList.add(newTask);
                System.out.println("Added: " + newTask);
            }

            System.out.print(" ");
        }

        System.out.println("Bye! Hope to see you again!!");
    }
}

