import helperpackage.Task;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________________");

        System.out.println("Hello! I'm NextGenerationJarvis.");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        ArrayList<Task> taskList = new ArrayList<>();

        while (!userInput.toLowerCase().equals("bye")) {
            
            if (userInput.toLowerCase().equals("list")) {
                System.out.println("\n________________________________________");
                System.out.println("Here are the tasks in your list:");

                for (int i = 1; i <= taskList.size(); i++) {
                    Task t = taskList.get(i - 1);
                    System.out.println(i + ". " + t.toString());
                }

                System.out.println("________________________________________\n");

            } else {
                StringTokenizer st = new StringTokenizer(userInput);
                String cmd = st.nextToken().toLowerCase();

                if (cmd.equals("mark")) {
                    int index = Integer.parseInt(st.nextToken());
                    Task t = taskList.get(index - 1);
                    t.markAsDone();
                    
                    System.out.println("\n________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + t.toString());
                    System.out.println("________________________________________\n");

                } else if (cmd.equals("unmark")) {
                    int index = Integer.parseInt(st.nextToken());
                    Task t = taskList.get(index - 1);
                    t.unmark();
                    
                    System.out.println("\n________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + t.toString());
                    System.out.println("________________________________________\n");

                } else {
                    taskList.add(new Task(userInput));

                    System.out.println("\n________________________________________");
                    System.out.println("added:" + userInput);
                    System.out.println("________________________________________\n");
                }
            }

            userInput = scanner.nextLine();
        }    

        System.out.println("\n________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________\n");

        scanner.close();
    }
}
