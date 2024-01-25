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
        Task t = new Task(userInput);
        while (!userInput.equals("bye")) {
            System.out.println(horizontalLine);
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + "[" + task[i].getStatusIcon()+ "] " + task[i]);
                }
            } else if(userInput.matches("mark \\d+")) {
                System.out.println("Nice! I've marked this task as done:");
                int id = Integer.parseInt(userInput.split(" ")[1]);
                task[id - 1].markAsDone();
                System.out.println("[" + task[id - 1].getStatusIcon() + "] " + task[id - 1].toString());
            } else if(userInput.matches("unmark \\d+")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int id = Integer.parseInt(userInput.split(" ")[1]);
                task[id - 1].markAsUndone();
                System.out.println("[" + task[id - 1].getStatusIcon() + "] " + task[id - 1].toString());
            } else {
                task[counter] = t;
                counter++;
                System.out.println("added: " + userInput);
            }
            System.out.println(horizontalLine);
            userInput = sc.nextLine().trim();
            t = new Task(userInput);
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
        sc.close();
    }
}

