import java.time.LocalDateTime;
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
        TaskManager taskManager = new TaskManager();
        do {
            input = scanner.nextLine();
            String command = input;
            String value = "";

            if (command.indexOf(" ") != -1) {
                command = command.substring(0, command.indexOf(" "));
                value = input.substring(input.indexOf(" ") + 1);
            }

            if (command.equals("list")) {
                taskManager.print();
            } else if ((command.equals("mark") || command.equals("unmark"))  && !value.isEmpty()) {
                int taskIndex = Integer.parseInt(value) - 1;
                Task task = taskManager.get(taskIndex);
                System.out.println("--------------------");
                if (command.equals("mark")) {
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" + task);
                } else {
                    task.markAsUndone();
                    System.out.println("Ok! I've marked this task as not yet done: \n" + task);
                }
                System.out.println("--------------------");
            } else if (command.equals("todo")) {
                Todo todo = new Todo(value);
                taskManager.add(todo);
            } else if (command.equals("deadline")) {
                String deadline = value.substring(value.indexOf("/by") + 4);
                String name = value.substring(0, value.indexOf("/by") - 1);
                Deadline deadlineTask = new Deadline(name, deadline);
                taskManager.add(deadlineTask);
            } else if (command.equals("event")) {
                int endDateIndex = value.indexOf("/to") + 4;
                int startDateIndex = value.indexOf("/from") + 6;
                String endDate = value.substring(endDateIndex);
                String startDate = value.substring(startDateIndex, endDateIndex - 5);
                String name = value.substring(0, startDateIndex - 7);
                Event event = new Event(name, startDate, endDate);
                taskManager.add(event);
            }
            else {
                taskManager.add(new Task(input));
            }
        } while (!input.equals("bye"));
        
        scanner.close();
        System.out.println("Bye, good riddance");
    }

}
