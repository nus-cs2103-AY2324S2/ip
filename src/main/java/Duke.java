import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;

        System.out.println("Hello! I'm Blob.\nWhat can I do for you?\n");
        String message = sc.nextLine();

        while (!message.equalsIgnoreCase("bye")) {
            if (message.equalsIgnoreCase("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].getDescription() + "\n");
                }
            } else {
                tasks[count] = new Task(message);
                count++;
                System.out.println("added: " + message + "\n");
            }
            message = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
