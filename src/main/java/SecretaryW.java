import java.util.Scanner;
public class SecretaryW {
    public static void main(String[] args) {
        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Array to store task items
        Task[] tasklist = new Task[100];

        // counter for number of task
        int count = 0;

        String line = "------------------------------------------\n";
        String greeting = "Hello! I'm SecretaryW\n" + "What can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";

        // Greetings
        System.out.println(line + greeting + line);

        // Read user input in the loop
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")){
                break; // Exits loop
            } else if (userInput.equals("list")) {
                System.out.println(line);
                if (count == 0) {
                    System.out.println("No tasks available");
                    System.out.println(line);
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        Task currTask = tasklist[i];
                        System.out.println(" " + (i + 1) + ". " + currTask.getStatusIcon() + " " + currTask.getDescription());
                    }
                    System.out.println(line);
                }
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.substring(5).trim()) - 1;
                // check for bounds
                if (index >= 0 && index < count) {
                    tasklist[index].markAsDone();
                    System.out.println(line + " Nice! I've marked this task as done:");
                    System.out.println("  " + tasklist[index].getStatusIcon() + " " + tasklist[index].getDescription() + "\n" + line);
                } else {
                    System.out.println(line + " Index is out of bounds!\n" + line);
                }
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
                // check for bounds
                if (index >= 0 && index < count) {
                    tasklist[index].markAsUndone();
                    System.out.println(line + " OK, I've marked this task as not done yet");
                    System.out.println("  " + tasklist[index].getStatusIcon() + " " + tasklist[index].getDescription() + "\n" + line);
                } else {
                    System.out.println(line + " Index is out of bounds!\n" + line);
                }
            } else {
                // Add to tasklist
                tasklist[count] = new Task(userInput);
                count++;
                String echo = userInput + "\n";
                System.out.println(line + "added: " + echo + line);
            }
        }

        // Farewell
        System.out.println(line + farewell + line);
        scanner.close();
    }
}