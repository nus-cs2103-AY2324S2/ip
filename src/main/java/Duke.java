import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String isDone(ArrayList<Boolean> check, int index) {
        if (check.get(index)) {
            return "[X]";
        } else {
            return "[ ]";

        }
    }
    private static void markTask(ArrayList<Boolean> check, int index, boolean done) {
        check.set(index, done);
    }

    public static void main(String[] args) {
        ArrayList<Task> storage = new ArrayList<>();
        String logo = "__________________________________\n" +
                "Hello! I'm Tim \n" +
                "What can i do for you? \n" +
                "__________________________________\n";
        String exit = "Bye. Hope to see you again soon!\n" +
                "__________________________________";

        System.out.println(logo);

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.isEmpty()) {
            int taskNum = storage.size() + 1;
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            }

            if (input.equals("list")) {
                System.out.println("__________________________________\n" +
                        "Here are the tasks in your list:\n");
                for (int i = 0; i < storage.size(); i++) {
                    int counter = i + 1;
                    String output = counter + ". " + storage.get(i).toString();
                    System.out.println(output);
                }
            } else if (input.startsWith("mark")) {
                String[] temp = input.split(" ");
                int index = Integer.parseInt(temp[1]) - 1;
                Task task = storage.get(index);
                task.markTask();
                String output = "Nice! I've marked this task as done:\n" +
                        "   " + task.toString();
                System.out.println(output);


            } else if (input.startsWith("unmark")) {
                String[] temp = input.split(" ");
                int index = Integer.parseInt(temp[1]) - 1;
                Task task = storage.get(index);
                task.unmarkTask();
                String output = "OK, I've marked this task as not done yet:\n" +
                        "   " + task.toString();
                System.out.println(output);

            } else if (input.startsWith("todo")) {
                String msg = input.substring(5);
                ToDo task = new ToDo(msg);
                String output = "__________________________________\n" +
                        "Ok, I have added this task:\n" +
                        "   " + task.toString() +
                        String.format("\nNow you have %d tasks in the list", taskNum);
                System.out.println(output);
                storage.add(task);

            } else if (input.startsWith("deadline")) {
                String msg = input.substring(9);
                
                Deadline task = new Deadline(msg);
                String output = "__________________________________\n" +
                        "Ok, I have added this task:\n" +
                        "   " + task.toString() +
                        String.format("\nNow you have %d tasks in the list", taskNum);
                System.out.println(output);
                storage.add(task);

            } else if (input.startsWith("event")) {
                String msg = input.substring(6);
                Event task = new Event(msg);
                String output = "__________________________________\n" +
                        "Ok, I have added this task:\n" +
                        "   " + task.toString() +
                        String.format("\nNow you have %d tasks in the list", taskNum);
                System.out.println(output);
                storage.add(task);

            } else {
                Task task = new Task(input);
                String output = "__________________________________\n" +
                        "Ok, I have added this task:\n" +
                        "   " + task.toString() +
                        String.format("\nNow you have %d tasks in the list", taskNum);
                System.out.println(output);
                storage.add(task);
            }
            System.out.println("__________________________________\n");
            input = scan.nextLine();

        }

    }
}
