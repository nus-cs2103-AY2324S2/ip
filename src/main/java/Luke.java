import java.util.*;

public class Luke {

    public static void main(String[] args) {

        String name = "Luke";

        Task[] taskList = new Task[100];

        int noTasks = 0;

        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] splited = input.split(" ");
            String command = splited[0];

            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < noTasks; i++) {
                        System.out.println((i + 1) + ". " + taskList[i].toString());
                    }
                    break;
                case "mark":
                    int markIndex = Integer.valueOf(splited[1]);
                    Task markTask = taskList[markIndex - 1];
                    markTask.setToDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(markTask.toString());
                    break;
                case "unmark":
                    int unmarkIndex = Integer.valueOf(splited[1]);
                    Task unmarkTask = taskList[unmarkIndex - 1];
                    unmarkTask.setToNotDone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(unmarkTask.toString());
                    break;
                case "todo":
                    System.out.println("Got it. I've added this task:");

                    Todo todo = new Todo(input.substring(5));
                    taskList[noTasks] = todo;
                    System.out.println(todo.toString());
                    noTasks++;
                    System.out.println("Now you have " + noTasks + " tasks in the list.");
                    break;
                case "deadline":
                    System.out.println("Got it. I've added this task:");

                    String[] deadlineSplit = input.split("/");
                    String deadlineDescription = deadlineSplit[0].substring(9);
                    String by = deadlineSplit[1].substring(3);

                    Deadline deadline = new Deadline(deadlineDescription, by);
                    taskList[noTasks] = deadline;
                    System.out.println(deadline.toString());
                    noTasks++;
                    System.out.println("Now you have " + noTasks + " tasks in the list.");
                    break;
                case "event":
                    System.out.println("Got it. I've added this task:");

                    String[] eventSplit = input.split("/");
                    String eventDescription = eventSplit[0].substring(6);
                    String from = eventSplit[1].substring(5);
                    String to = eventSplit[2].substring(3);

                    Event event = new Event(eventDescription, from, to);
                    taskList[noTasks] = event;
                    System.out.println(event.toString());
                    noTasks++;
                    System.out.println("Now you have " + noTasks + " tasks in the list.");
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
