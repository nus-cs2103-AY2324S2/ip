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
        System.out.println("    -----------------------------------");
        System.out.println("    Hello! I'm ByteTalker");
        System.out.println("    What can I do for you?");
        System.out.println("    -----------------------------------");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String userInput_String = userInput.nextLine().strip();
            String[] split_message = userInput_String.split(" ");
            System.out.println("    -----------------------------------");
            if (userInput_String.equals("bye")) {
                break;
            } else if (userInput_String.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println("    " + (i + 1) + "." + storage.get(i).getMessage());
                }
            } else if (split_message[0].equals("mark")) {
                Integer index = Integer.parseInt(split_message[1]) - 1;
                storage.get(index).setStatus(true);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + storage.get(index).getMessage());
            } else if (split_message[0].equals("unmark")) {
                Integer index = Integer.parseInt(split_message[1]) - 1;
                storage.get(index).setStatus(false);
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + storage.get(index).getMessage());
            } else {
                Task message;
                if (split_message[0].equals("todo")) {
                    message = Task.TODO;
                    String task = " ";
                    for (int i = 1; i < split_message.length; i++) {
                        task = task + split_message[i] + " ";
                    }
                    task = task.strip();
                    message.setTask(task);
                } else if (split_message[0].equals("deadline")) {
                    message = Task.DEADLINE;
                    String content = " ";
                    for (int i = 1; i < split_message.length; i++) {
                        if (split_message[i].equals("/by")) {
                            content = content.strip();
                            message.setTask(content);
                            content = "";
                        } else {
                            content = content + split_message[i] + " ";
                        }
                    }
                    content = content.strip();
                    message.setTo(content);
                } else {
                    message = Task.Event;
                    String content = " ";
                    for (int i = 1; i < split_message.length; i++) {
                        if (split_message[i].equals("/from")) {
                            content = content.strip();
                            message.setTask(content);
                            content = "";
                        } else if (split_message[i].equals("/to")) {
                            content = content.strip();
                            message.setFrom(content);
                            content = "";
                        } else {
                            content = content + split_message[i] + " ";
                        }
                    }
                    content = content.strip();
                    message.setTo(content);
                }
                storage.add(message);
                System.out.println("    Got it. I've added this task:");
                System.out.println("       " + message.getMessage());
                System.out.println("    Now you have " + storage.size() + " tasks in the list.");
            }
            System.out.println("    -----------------------------------");
        }
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    -----------------------------------");
    }
}
