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
        ArrayList<Message> storage = new ArrayList<>();

        Message message;
        System.out.println("-----------------------------------");
        System.out.println("Hello! I'm ByteTalker");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            message = new Message(userInput.nextLine());
            System.out.println("-----------------------------------");
            if (!message.getMessage().equals("bye") && !message.getMessage().equals("list")) {
                storage.add(message);
                System.out.println("added: " + message.getMessage());
                System.out.println("-----------------------------------");
            } else if (!message.getMessage().equals("bye") && message.getMessage().equals("list")) {
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println(i+1 + ". " + storage.get(i).getMessage());
                }
                System.out.println("-----------------------------------");
            } else {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-----------------------------------");
    }
}
