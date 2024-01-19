import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm Baron. What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        String input;
        List<Task> tasks = new ArrayList<>();
        do {
            input = scanner.nextLine();
            String command = input;
            String value = "";

            if (command.indexOf(" ") != -1) {
                command = command.substring(0, command.indexOf(" "));
                value = input.substring(input.indexOf(" ") + 1);
            }

            if (command.equals("list")) {
                System.out.println("--------------------");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("--------------------");
            } else if ((command.equals("mark") || command.equals("unmark"))  && !value.isEmpty()) {
                int taskIndex = Integer.parseInt(value) - 1;
                Task task = tasks.get(taskIndex);
                System.out.println("--------------------");
                if (command.equals("mark")) {
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" + task);
                } else {
                    task.markAsUndone();
                    System.out.println("Ok! I've marked this task as not yet done: \n" + task);
                }
                System.out.println("--------------------");
                // System.out.println(tasks.get(value).getName());
            } else {
                tasks.add(new Task(input));
                System.out.println("--------------------");
                System.out.println("Added: " + input);
                System.out.println("--------------------");
            }
        } while (!input.equals("bye"));
        
        scanner.close();
        System.out.println("Bye, good riddance");
    }

}
