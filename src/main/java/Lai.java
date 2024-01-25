import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lai {
    public static void printDottedLine() {
        System.out.println("---------------------------------------------------------");
    }

    public static String[] processInput(Scanner scanner) {
        System.out.print("> ");

        // Separating the command and description from user input
        String[] commandDesc = scanner.nextLine().split("\\s+", 2);
        String command = commandDesc[0];
        String desc = "";
        if (commandDesc.length > 1) {
            desc = commandDesc[1];
        }

        return new String[]{ command, desc };
    }

    public static void addTask(List<Task> tasks, Task newTask) {
        tasks.add(newTask);
        printDottedLine();
        System.out.println("Added: " + newTask);
        System.out.println(String.format("Total number of tasks: %s", tasks.size()));
        printDottedLine();
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi there, I am Lai. Your friendly fairly useless chatbot.");
        System.out.println("What can I assist you with today?");
        printDottedLine();

        String[] input = processInput(scanner);
        String command = input[0];
        String desc = input[1];

        while (!command.equals("bye")) {
            if (command.equals("mark")) {
                int index = Integer.valueOf(desc);
                Task t = tasks.get(index - 1);
                t.setDone();

                printDottedLine();
                System.out.println("You actually did something? Marked done:");
                System.out.println(t);
                printDottedLine();
            } else if (command.equals("unmark")) {
                int index = Integer.valueOf(desc);
                Task t = tasks.get(index - 1);
                t.setNotDone();

                printDottedLine();
                System.out.println("Come on now, don't be useless. Marked not done:");
                System.out.println(t);
                printDottedLine();
            } else if (command.equals("deadline")) {
                // Separating the deadline from description
                String[] descBy = desc.split("/by ", 2);
                desc = descBy[0];
                String by = "";
                if (descBy.length > 1) {
                    by = descBy[1];
                }

                Deadline newTask = new Deadline(desc, by);
                addTask(tasks, newTask);
            } else if (command.equals("todo")) {
                ToDo newTask = new ToDo(desc);
                addTask(tasks, newTask);
            } else if (command.equals("event")) {
                // Separating the start from description
                String[] descFrom = desc.split("/from ", 2);
                desc = descFrom[0];
                String from = "";
                if (descFrom.length > 1) {
                    from = descFrom[1];
                }
                // Separating the end out
                String[] fromTo = from.split("/to ", 2);
                from = fromTo[0];
                String to = "";
                if (fromTo.length > 1) {
                    to = fromTo[1];
                }

                Event newTask = new Event(desc, from, to);
                addTask(tasks, newTask);
            } else if (command.equals("list")) {
                printDottedLine();
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%s. %s", i + 1, tasks.get(i)));
                }
                printDottedLine();
            }

            input = processInput(scanner);
            command = input[0];
            desc = input[1];
        }


        System.out.println("---------------------------------------------------------");
        System.out.println("Goodbye, we shall meet again. Hopefully.");
        System.out.println("---------------------------------------------------------");
    }
}
