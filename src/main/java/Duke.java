import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //        String logo = " ____        _        \n"
        //                + "|  _ \\ _   _| | _____ \n"
        //                + "| | | | | | | |/ / _ \\\n"
        //                + "| |_| | |_| |   <  __/\n"
        //                + "|____/ \\__,_|_|\\_\\___|\n";
        //        System.out.println("Hello from\n" + logo);
        String botName = "Wind";
        System.out.println("Hello I'm " + botName + "\n"
                + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>(100);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    int number = i + 1;
                    System.out.println(number + ". " + tasks.get(i));
                }
                continue;
            }
            String[] splitedInput = input.split(" ");
            if (splitedInput[0].equals("mark")) {
                int itemNumber = Integer.parseInt(splitedInput[1]);
                tasks.get(itemNumber - 1).setIsDone(true);
                System.out.println("Nice! I've marked this task as done:\n"
                        + tasks.get(itemNumber - 1));
                continue;
            }
            if (splitedInput[0].equals("unmark")) {
                int itemNumber = Integer.parseInt(splitedInput[1]);
                tasks.get(itemNumber - 1).setIsDone(false);
                System.out.println("Ok, I've marked this task as not done yet\n"
                        + tasks.get(itemNumber - 1));
                continue;
            }

            if (splitedInput[0].equals("todo")) {
                tasks.add(new Todo(input.substring(4).trim()));
                printAddTaskMessage(tasks.get(tasks.size() - 1),
                        tasks);
                continue;
            }
            if (splitedInput[0].equals("deadline")) {
                tasks.add(Deadline.buildDeadLine(input));
                printAddTaskMessage(tasks.get(tasks.size() - 1), tasks);
                continue;
            }
            if (splitedInput[0].equals("event")) {
                tasks.add(Event.buildEvent(input));
                printAddTaskMessage(tasks.get(tasks.size() - 1), tasks);
                continue;
            }
            tasks.add(new Task(input));
            System.out.println("added: " + input);
        }

    }

    public static void printAddTaskMessage(Task task, List<Task> tasks) {
        System.out.println("Got it. I've added this task:\n"
                + " " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }
}

