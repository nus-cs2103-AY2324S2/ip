import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo =  " _____ _               _\n"
                +       "/  __ (_)             | |\n"
                +       "| /  \\/_  ___ __ _  __| | __ _\n"
                +       "| |   | |/ __/ _` |/ _` |/ _` |\n"
                +       "| \\__/\\ | (_| (_| | (_| | (_| |\n"
                +       " \\____/_|\\___\\__,_|\\__,_|\\__,_|\n";

        System.out.println("Hello from\n" + logo);
        String horizontalLine = "-".repeat(60);
        greeting("Cicada", horizontalLine);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your command and press Enter. Type 'bye' to quit.");
        List<Task> tasks = new ArrayList<Task>();
        List<String> commands = new ArrayList<String>() {
            {
                add("bye");
                add("list");
                add("mark");
                add("unmark");
                add("todo");
                add("deadline");
                add("event");
            }
        };
        List<String> taskTypes = new ArrayList<String>() {
            {
                add("todo");
                add("deadline");
                add("event");
            }
        };

        while (true) {
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ",2);
            if (parts.length == 1) {
                if (userInput.equalsIgnoreCase("bye")) {
                    ending(horizontalLine);
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    System.out.println(horizontalLine);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        int j = i+1;
                        System.out.println(j + "." + tasks.get(i).toString());
                    }
                    System.out.println(horizontalLine);
                } else if (commands.contains(userInput.toLowerCase())){
                    try {
                        if (taskTypes.contains(userInput.toLowerCase())) {
                            throw new DukeException("Error: Missing task description.");
                        }
                    } catch (DukeException e) {
                        System.err.println("OOPS! The description of a " + userInput.toLowerCase() + " cannot be empty");
                    }
                    try {
                        if (userInput.equalsIgnoreCase("mark") || userInput.equalsIgnoreCase("unmark")) {
                            throw new DukeException("Error: Missing index number to " + userInput.toLowerCase() + " .");
                        }
                    } catch (DukeException e) {
                        System.err.println("Please state you want to " + userInput.toLowerCase() + " which task.");
                    }
                } else {
                    try {
                        throw new DukeException("Error: Unable to recognise this command.");
                    } catch (DukeException e) {
                        System.err.println("OOPS! This command doesn't exist. Retry!");
                    }
                }
            } else if (parts.length >= 2) {
                String command = parts[0];
                if (command.equalsIgnoreCase("mark")) {
                    try {
                        String number = parts[1];
                        int index = Integer.parseInt(number);
                        tasks.get(index-1).markDone(horizontalLine);
                    } catch (NumberFormatException e) {
                        System.err.println("Unable to parse the input as an integer. Please put a number after " + command);
                    }
                } else if (command.equalsIgnoreCase("unmark")) {
                    try {
                        String number = parts[1];
                        int index = Integer.parseInt(number);
                        tasks.get(index-1).unmark(horizontalLine);
                    } catch (NumberFormatException e) {
                        System.err.println("Unable to parse the input as an integer. Please put a number after " + command);
                    }
                } else if (command.equalsIgnoreCase("delete")) {
                    try {
                        String number = parts[1];
                        int index = Integer.parseInt(number);
                        delete(tasks, index-1, horizontalLine);
                    } catch (NumberFormatException e) {
                        System.err.println("Unable to parse the input as an integer. Please put a number after " + command);
                    }
                } else if (command.equalsIgnoreCase("todo")) {
                    ToDo newTask = new ToDo(parts[1]);
                    tasks.add(newTask);
                    System.out.println(horizontalLine);
                    newTask.displayTask(tasks.size());
                    System.out.println(horizontalLine);
                } else if (command.equalsIgnoreCase("deadline")) {
                    String[] instruction = parts[1].split(" by ",2);
                    try {
                        if (!parts[1].contains("by")) {
                            throw new DukeException("Error: 'by' keyword is missing. You are required to state the deadline using the 'by' keyword ");
                        }
                    } catch (DukeException e) {
                        System.err.println("OOPS! 'by' keyword is missing. You are required to state the deadline using the 'by' keyword ");
                        continue;
                    }
                    try {
                        if (instruction.length < 2) {
                            throw new DukeException();
                        }
                    } catch (DukeException e) {
                        System.err.println("OOPS! You forget to write do the task by when");
                    }
                    String description = instruction[0];
                    String deadline = instruction[1];
                    Deadline newTask = new Deadline(description, deadline);
                    tasks.add(newTask);
                    System.out.println(horizontalLine);
                    newTask.displayTask(tasks.size());
                    System.out.println(horizontalLine);
                } else if (command.equalsIgnoreCase("event")) {
                    String[] instruction = parts[1].split(" from ", 2);
                    try {
                        if (!parts[1].contains("from") || !parts[1].contains("to")) {
                            throw new DukeException("Error: 'from' and/or 'to' keywords are missing. You are required to state the starting and ending time using these two keywords.");
                        }
                    } catch (DukeException e) {
                        System.err.println("OOPS! 'from' and/or 'to' keywords are missing. You are required to state the starting and ending time using these two keywords.");
                        continue;
                    }
                    try {
                        if (instruction.length < 2) {
                            throw new DukeException();
                        }
                    } catch (DukeException e) {
                        System.err.println("OOPS! You forget to write the starting time of this event.");
                        continue;
                    }
                    String description = instruction[0];
                    String[] subInstruction = instruction[1].split(" to ", 2);
                    try {
                        if (subInstruction.length < 2) {
                            throw new DukeException();
                        }
                    } catch (DukeException e) {
                        System.err.println("OOPS! You forget to write the ending time of this event.");
                        continue;
                    }
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

    public static void delete(List<Task> tasks, int index, String line) {
        String taskStr = tasks.get(index).toString();
        tasks.remove(index);
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskStr);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
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
