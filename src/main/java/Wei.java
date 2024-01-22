import java.util.Scanner;

public class Wei {
    public static void main(String[] args) {
        String greet = "Hello! I'm Wei.\n" + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";
        String split = "______________________________";
        // greet
        System.out.println(greet);
        System.out.println(split);

        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                break;
            }
            // list
            else if (input.equals("list")) {
                if(tasks[0] == null) {
                    System.out.println(split);
                    continue;
                }
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numOfTasks; i++) {
                    int order = i + 1;
                    String text = tasks[i].stringify();
                    System.out.println(order + ". " + text);
                }
            }
            // mark
            else if (input.matches("mark \\d+")) {
                int order = Integer.parseInt(input.substring(5)) - 1;
                tasks[order].setStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[order].stringify());
            }
            // unmark
            else if (input.matches("unmark \\d+")) {
                int order = Integer.parseInt(input.substring(7)) - 1;
                tasks[order].setStatus(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[order].stringify());
            }
            // add
            else {
                Task newTask = new Task(input);
                tasks[numOfTasks] = newTask;
                numOfTasks++;
                System.out.println("added: " + input);
            }
            System.out.println(split);
        }

        // exit
        System.out.println(exit);
        System.out.println(split);
    }
}
