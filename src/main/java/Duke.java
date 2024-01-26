import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String confirmation = "Got it. I've added this task:";


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
                    System.out.println(task.index + "." + task.getType() + task.getStatusIcon() + " " + task.description);
                }

                System.out.println(divider);

            } else if (userInput.contains("todo")) {
                // to-do
                System.out.println(divider);

                Todo todo = new Todo(userInput.substring(5));
                tasks.add(todo);
                System.out.println(confirmation);
                System.out.println(todo.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                System.out.println(divider);

            } else if (userInput.contains("deadline")){
                // Deadline
                System.out.println(divider);

                //extract date
                int indexOfBy = userInput.indexOf("/by");
                int indexOfDate = indexOfBy + 4;
                String dateOfDeadline = userInput.substring(indexOfDate);
                String desc = userInput.substring(9, indexOfBy - 1 ) + " (by: " + dateOfDeadline + ")";

                Deadline deadline = new Deadline(desc, dateOfDeadline);
                tasks.add(deadline);
                System.out.println(confirmation);
                System.out.println(deadline.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                System.out.println(divider);
            } else if (userInput.contains("event")){
                // Event
                System.out.println(divider);

                //extract dates
                int indexOfFrom = userInput.indexOf("/from");
                int indexOfTo = userInput.indexOf("/to");

                String from = userInput.substring(indexOfFrom + 6, indexOfTo - 1);
                String to = userInput.substring(indexOfTo + 4);
                String desc = userInput.substring(6, indexOfFrom - 1 ) + " (from: " + from + " to: " + to + ")";

                Event event = new Event(desc, from, to);
                tasks.add(event);
                System.out.println(confirmation);
                System.out.println(event.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

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

