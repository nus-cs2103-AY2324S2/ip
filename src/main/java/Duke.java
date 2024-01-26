import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        List<Task> task = new ArrayList<>();
        String horizontalLine = "_".repeat(60);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String userInput = sc.nextLine().trim();
        String category = userInput.split(" ")[0];
        while (!userInput.equals("bye")) {
            System.out.println(horizontalLine);
            switch (category) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + ". " + task.get(i).toString());
                    }
                    break;
                case "mark":
                    String[] toMark = userInput.split(" ");
                    if (toMark.length <= 1) {
                        System.out.println("Error. Unknown number.");
                        break;
                    }
                    int id = Integer.parseInt(userInput.split(" ")[1]);
                    if (id > counter) {
                        System.out.println("Error. Task does not exist.");
                        break;
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    task.get(id - 1).markAsDone();
                    System.out.println(task.get(id - 1).toString());
                    break;
                case "unmark":
                    String[] toUnmark = userInput.split(" ");
                    if (toUnmark.length <= 1) {
                        System.out.println("Error. Unknown number.");
                        break;
                    }
                    int num = Integer.parseInt(userInput.split(" ")[1]);
                    if (num > counter) {
                        System.out.println("Error. Task does not exist.");
                        break;
                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                    task.get(num - 1).markAsUndone();
                    System.out.println(task.get(num - 1).toString());
                    break;
                case "todo":
                    String[] todos = userInput.split(" ");
                    if (todos.length <= 1) {
                        System.out.println("Error. Todo cannot be empty.");
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    Todo t = new Todo(userInput.substring(5));
                    System.out.println(t);
                    task.add(t);
                    counter++;
                    System.out.println(taskCounter(counter));
                    break;
                case "deadline":
                    String[] deadlines = userInput.split(" ");
                    if (deadlines.length <= 1) {
                        System.out.println("Error. Deadline cannot be empty.");
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    String description = userInput.substring(9).split("/")[0].trim();
                    String by = userInput.split("/")[1].substring(3).trim();
                    Deadline d = new Deadline(description, by);
                    System.out.println(d);
                    task.add(d);
                    counter++;
                    System.out.println(taskCounter(counter));
                    break;
                case "event":
                    String[] events = userInput.split(" ");
                    if (events.length <= 1) {
                        System.out.println("Error. Event cannot be empty.");
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    String input = userInput.substring(6);
                    String descr = input.split("/")[0].trim();
                    String from = input.split("/")[1].substring(5).trim();
                    String to = input.split("/")[2].substring(3).trim();
                    Event e = new Event(descr, from, to);
                    System.out.println(e);
                    task.add(e);
                    counter++;
                    System.out.println(taskCounter(counter));
                    break;
                case "delete":
                    String[] toDelete = userInput.split(" ");
                    if (toDelete.length <= 1) {
                        System.out.println("Error. Unknown number.");
                        break;
                    }
                    int j = Integer.parseInt(userInput.split(" ")[1]);
                    if (j > counter) {
                        System.out.println("Error. Task does not exist.");
                        break;
                    }
                    System.out.println("Noted. I have removed this task:");
                    System.out.println(task.get(j - 1));
                    task.remove(j - 1);
                    counter--;
                    System.out.println(taskCounter(counter));
                    break;
                default:
                    System.out.println("HUH? What do you mean?");
                    break;
            }
            System.out.println(horizontalLine);
            userInput = sc.nextLine().trim();
            category = userInput.split(" ")[0];
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
        sc.close();
    }

    private static String taskCounter(int counter) {
        if (counter <= 1) {
            return "Now you have " + counter + " task in the list.";
        } else {
            return "Now you have " + counter + " tasks in the list.";
        }
    }
}