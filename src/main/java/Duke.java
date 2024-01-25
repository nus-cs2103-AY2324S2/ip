import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =  " _____ _               _\n"
                +       "/  __ (_)             | |      \n"
                +       "| /  \\/_  ___ __ _  __| | __ _ \n"
                +       "| |   | |/ __/ _` |/ _` |/ _` |\n"
                +       "| \\__/\\ | (_| (_| | (_| | (_| |\n"
                +       " \\____/_|\\___\\__,_|\\__,_|\\__,_|\n";

        System.out.println("Hello from\n" + logo);
        String horizontalLine = "-".repeat(60);
        greeting("Cicada", horizontalLine);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your command and press Enter. Type 'bye' to quit.");
        List<Task> tasks = new ArrayList<Task>();
        while (true) {
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ",2);
            if (parts.length == 1) {
                if (userInput.equalsIgnoreCase("bye")) {
                    ending(horizontalLine);
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    System.out.println(horizontalLine);
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        int j = i+1;
                        System.out.println(j + "." + tasks.get(i).toString());
                    }
                    System.out.println(horizontalLine);
                }
            } else if (parts.length >= 2) {
                String command = parts[0];
                if (command.equalsIgnoreCase("mark")) {
                    String number = parts[1];
                    int index = Integer.parseInt(number);
                    tasks.get(index-1).markDone(horizontalLine);
                    continue;
                } else if (command.equalsIgnoreCase("unmark")) {
                    String number = parts[1];
                    int index = Integer.parseInt(number);
                    tasks.get(index-1).unmark(horizontalLine);
                    continue;
                } else if (command.equalsIgnoreCase("todo")) {
                    ToDo newTask = new ToDo(parts[1]);
                    tasks.add(newTask);
                    System.out.println(horizontalLine);
                    newTask.displayTask(tasks.size());
                    System.out.println(horizontalLine);
                } else if (command.equalsIgnoreCase("deadline")) {
                    String[] instruction = parts[1].split(" by ",2);
                    String description = instruction[0];
                    String deadline = instruction[1];
                    Deadline newTask = new Deadline(description, deadline);
                    tasks.add(newTask);
                    System.out.println(horizontalLine);
                    newTask.displayTask(tasks.size());
                    System.out.println(horizontalLine);
                } else if (command.equalsIgnoreCase("event")) {
                    String[] instruction = parts[1].split(" from ", 2);
                    String description = instruction[0];
                    String[] subInstruction = instruction[1].split(" to ", 2);
                    String from = subInstruction[0];
                    String to = subInstruction[1];
                    Event newTask = new Event(description, from, to);
                    tasks.add(newTask);
                    System.out.println(horizontalLine);
                    newTask.displayTask(tasks.size());
                    System.out.println(horizontalLine);
                }
            }
        }
        scanner.close();
    }

    public static void greeting(String chatbotName, String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + chatbotName
                + "\nWhat can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void ending(String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
