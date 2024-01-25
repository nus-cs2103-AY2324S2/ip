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
                    try {
                        String s = userInput.substring("todo".length()).trim();
                        if (s.isEmpty()) {
                            throw new DukeException("Task description cannot be empty.");
                        }
                        Task task = new Todo(s);
                        System.out.println(myList.addItem(task));
                    } catch (DukeException e) {
                        System.out.println("Error: " + e.getMsg());
                    }
                } else if (userInput.startsWith("deadline")) {
                    try {
                        String s = userInput.substring("deadline".length()).trim();
                        String[] s1 = s.split("/by");
                        if (s1.length > 2) {
                            throw new DukeException("Multiple /by");
                        }
                        Task task = new Deadline(s1[0].trim(), s1[1].trim());
                        System.out.println(myList.addItem(task));
                    } catch (DukeException e) {
                        System.out.println("Error: " + e.getMsg());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please enter format deadline (task) /by (input)");
                    }
                } else if (userInput.startsWith("event")) {
                    try {
                        String s = userInput.substring("event".length()).trim();
                        String[] s1 = s.split("/from");
                        if (s1.length > 2) {
                            throw new DukeException("Multiple /from");
                        }
                        String[] s2 = s1[1].split("/to");
                        if (s2.length > 2) {
                            throw new DukeException("Multiple /to");
                        }
                        Task task = new Event(s1[0].trim(), s2[0].trim(), s2[1].trim());
                        System.out.println(myList.addItem(task));
                    } catch (DukeException e) {
                        System.out.println("Error: " + e.getMsg());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please enter format event (task) /from (input) /to (input)");
                    }
                } else {
                    System.out.println("OOPS! That was an invalid input");
                }
            }
            System.out.println("Bye. Hope to see you again soon!");

        } catch (IOException e) {
            System.err.println("Error");
        }
    }
}
