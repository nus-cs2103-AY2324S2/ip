import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        Task[] task = new Task[100];
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
                        System.out.println((i + 1) + ". " + task[i].toString());
                    }
                    break;
                case "mark":
                    System.out.println("Nice! I've marked this task as done:");
                    int id = Integer.parseInt(userInput.split(" ")[1]);
                    task[id - 1].markAsDone();
                    System.out.println(task[id - 1].toString());
                    break;
                case "unmark":
                    System.out.println("OK, I've marked this task as not done yet:");
                    int num = Integer.parseInt(userInput.split(" ")[1]);
                    task[num - 1].markAsUndone();
                    System.out.println(task[num - 1].toString());
                    break;
                case "todo":
                    System.out.println("Got it. I've added this task:");
                    Todo t = new Todo(userInput.substring(5));
                    System.out.println(t);
                    task[counter] = t;
                    counter++;
                    System.out.println(taskCounter(counter));
                    break;
                case "deadline":
                    System.out.println("Got it. I've added this task:");
                    String description = userInput.substring(9).split("/")[0].trim();
                    String by = userInput.split("/")[1].substring(3).trim();
                    Deadline d = new Deadline(description, by);
                    System.out.println(d);
                    task[counter] = d;
                    counter++;
                    System.out.println(taskCounter(counter));
                    break;
                case "event":
                    System.out.println("Got it. I've added this task:");
                    String input = userInput.substring(6);
                    String descr = input.split("/")[0].trim();
                    String from = input.split("/")[1].substring(5).trim();
                    String to = input.split("/")[2].substring(3).trim();
                    Event e = new Event(descr, from, to);
                    System.out.println(e);
                    task[counter] = e;
                    counter++;
                    System.out.println(taskCounter(counter));
                    break;
                default:
                    System.out.println("Invalid Input");
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

