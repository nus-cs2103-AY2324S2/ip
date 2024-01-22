import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "_____________________________________________________\n";

        String greeting = horizontalLine + "Hello! I'm KwunBot!\nWhat can I do for you?\n" + horizontalLine;
        System.out.println(greeting);

        String goodbye = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        Task[] tasks = new Task[100]; // Assume there are no more than 100 tasks
        int counter = 0;

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine(); // Read user input

            if (input.equals("bye")) { // Handle 'bye' command
                System.out.println(goodbye);
                break;
            }

            if (input.equals("list")) { // Handle 'list' command
                System.out.println(horizontalLine + "Here are the tasks in your list:");

                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks[i]);
                }
                System.out.println(horizontalLine);
                continue;
            }

            // Handle other commands
            String[] parts = input.split(" ", 2);

            if (parts[0].equals("mark") || parts[0].equals("unmark")) { // Handle 'mark' commands
                int taskId = Integer.parseInt(parts[1]) - 1;
                Task task = tasks[taskId];
                String statement = task.changeMark(parts[0]);
                System.out.println(horizontalLine + statement + task + "\n" + horizontalLine);
                continue;
            }

            // Handle add tasks commands
            Task newTask = null;

            switch (parts[0]) {
                case "todo": {
                    newTask = new Todo(parts[1]);
                    break;
                }
                case "deadline": {
                    String[] splitDate = parts[1].split(" /by ", 2);
                    newTask = new Deadline(splitDate[0], splitDate[1]);
                    break;
                }
                case "event": {
                    String[] splitTaskName = parts[1].split(" /from ", 2);
                    String[] splitFromToDates = splitTaskName[1].split(" /to ", 2);
                    newTask = new Event(splitTaskName[0], splitFromToDates[0], splitFromToDates[1]);
                    break;
                }
            }

            tasks[counter] = newTask;
            counter++;
            String taskCounter = String.format("Now you have %s tasks in the list.\n", counter);
            System.out.println(horizontalLine
                    + "Got it. I've added this task:\n" + newTask + "\n" + taskCounter
                    + horizontalLine);
        }
        sc.close();
    }
}
