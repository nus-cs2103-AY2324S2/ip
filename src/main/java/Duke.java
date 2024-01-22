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

            if (input.equals("bye")) {
                System.out.println(goodbye);
                break;
            }

            if (input.equals("list")) {
                System.out.println(horizontalLine + "Here are the tasks in your list:");

                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks[i]);
                }
                System.out.println(horizontalLine);
                continue;
            }

            String[] parts = input.split(" ", 2);

            if (parts[0].equals("mark") || parts[0].equals("unmark")) {
                int taskId = Integer.parseInt(parts[1]) - 1;
                Task task = tasks[taskId];
                String statement = task.changeMark(parts[0]);
                System.out.println(horizontalLine + statement + task + "\n" + horizontalLine);
                continue;
            }

            Task newTask = new Task(input);
            tasks[counter] = newTask;
            counter++;
            System.out.println(horizontalLine + "added: " + input + "\n" + horizontalLine);
        }
        sc.close();
    }
}
