import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        ArrayList<Task> storage = new ArrayList<>();

        Task message;
        System.out.println("    -----------------------------------");
        System.out.println("    Hello! I'm ByteTalker");
        System.out.println("    What can I do for you?");
        System.out.println("    -----------------------------------");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            message = new Task(userInput.nextLine().strip());
            String[] split_message = message.getMessage().split(" ");
            System.out.println("    -----------------------------------");
            if (message.getMessage().equals("bye")) {
                break;
            } else if (message.getMessage().equals("list")) {
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println("    " + (i + 1) + ".[" + storage.get(i).getStatusIcon() + "] " + storage.get(i).getMessage());
                }
            } else if (split_message[0].equals("mark")) {
                Integer index = Integer.parseInt(split_message[1]) - 1;
                storage.get(index).setStatus(true);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + "  [" + storage.get(index).getStatusIcon() + "] " + storage.get(index).getMessage());
            } else if (split_message[0].equals("unmark")) {
                Integer index = Integer.parseInt(split_message[1]) - 1;
                storage.get(index).setStatus(false);
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("    " + "  [" + storage.get(index).getStatusIcon() + "] " + storage.get(index).getMessage());
            } else {
                storage.add(message);
                System.out.println("    added: " + message.getMessage());
            }
            System.out.println("    -----------------------------------");
        }
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    -----------------------------------");
    }
}
