import java.util.*;

public class Luke {

    public static void main(String[] args) {

        String name = "Luke";

        ArrayList<Task> taskList = new ArrayList<Task>();

        int noTasks = 0;

        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] splited = input.split(" ");
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < noTasks; i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i).printTask());
                }
            } else if (splited[0].equals("mark")) {

                int index = Integer.valueOf(splited[1]);
                Task task = taskList.get(index - 1);
                task.setToDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task.printTask());

            } else if (splited[0].equals("unmark")) {

                int index = Integer.valueOf(splited[1]);
                Task task = taskList.get(index - 1);
                task.setToNotDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(task.printTask());

            } else {
                noTasks++;
                Task task = new Task(noTasks, input);
                taskList.add(task);
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
