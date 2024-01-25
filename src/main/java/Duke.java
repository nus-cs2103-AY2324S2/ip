import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Hao Wen\n" + "What can I do for you?");
        //System.out.println("Bye. Hope to see you again soon!");

        MyList myList = new MyList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                String userInput = br.readLine();
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }

                if (userInput.equalsIgnoreCase("list")) {
                    System.out.println(myList.getItems());
                } else if (userInput.startsWith("mark")) {
                    try {
                        int index = Integer.parseInt(userInput.substring("mark".length()).trim());
                        System.out.println(myList.markTask(index));
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                } else if (userInput.startsWith("todo")) {
                    String s = userInput.substring("todo".length()).trim();
                    Task task = new Todo(s);
                    System.out.println(myList.addItem(task));
                } else if (userInput.startsWith("deadline")) {
                    String s = userInput.substring("deadline".length()).trim();
                    String[] s1 = s.split("/by");
                    Task task = new Deadline(s1[0].trim(), s1[1].trim());
                    System.out.println(myList.addItem(task));
                } else if (userInput.startsWith("event")) {
                    String s = userInput.substring("event".length()).trim();
                    String[] s1 = s.split("/from");
                    String[] s2 = s1[1].split("/to");
                    Task task = new Event(s1[0].trim(), s2[0].trim(), s2[1].trim());
                    System.out.println(myList.addItem(task));
                } else {
                    System.out.println("Invalid input");
                }
            }
            System.out.println("Bye. Hope to see you again soon!");

        } catch (IOException e) {
            System.err.println("Error");
            //e.printStackTrace();
        }
    }
}
