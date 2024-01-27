import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;

public class Lai {
    public static void printDottedLine() {
        System.out.println("---------------------------------------------------------");
    }

    public static void checkDescription(String desc) throws LaiException {
        if (desc.equals("")) {
            throw new LaiException("Dude. What am I supposed to do with an empty description?");
        }
    }

    public static String[] processInput(Scanner scanner) {
        System.out.print("> ");

        // Separating the command and description from user input
        String[] commandDesc = scanner.nextLine().split("\\s+", 2);
        String command = commandDesc[0];
        String desc = "";

        if (commandDesc.length > 1) {
            desc = commandDesc[1];
        }

        return new String[]{ command, desc };
    }

    public static void addTask(List<Task> tasks, Task newTask) {
        tasks.add(newTask);
        updateTasksFile(tasks);

        printDottedLine();
        System.out.println("Added: " + newTask);
        System.out.println(String.format("Total number of tasks: %s", tasks.size()));
        printDottedLine();
    }

    public static void updateTasksFile(List<Task> tasks) {
        File file = new File("data/tasks.txt");

        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            System.out.println("Error occurred: Failed to create directory " + parentDir.getPath());
            return;
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public static List<Task> readTasksFile() {
        File file = new File("data/tasks.txt");
        List<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                boolean isDone = line.charAt(3) == 'X';
                String description = line.substring(7);

                if (line.startsWith("[T]")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (line.startsWith("[D]")) {
                    int byIndex = description.indexOf(" (by: ");
                    String byString = description.substring(byIndex + 6, description.length() - 1);

                    tasks.add(new Deadline(description.substring(0, byIndex), isDone, byString));

                } else if (line.startsWith("[E]")) {
                    int fromIndex = description.indexOf(" (from: ");
                    int toIndex = description.indexOf(" to: ");
                    String fromString = description.substring(fromIndex + 8, toIndex);
                    String byString = description.substring(toIndex + 5, description.length() - 1);

                    tasks.add(new Event(description.substring(0, fromIndex), fromString, byString));
                } else {
                    throw new LaiException("Your file does not seem to be in the correct format.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (LaiException e) {
            printDottedLine();
            System.out.println(e);
            printDottedLine();
        }

        return tasks;
    }

    public static void main(String[] args) {
        List<Task> tasks = readTasksFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi there, I am Lai. Your friendly fairly useless chatbot.");
        System.out.println("What can I assist you with today?");
        printDottedLine();

        String[] input = processInput(scanner);
        String command = input[0];
        String desc = input[1];

        while (!command.equals("bye")) {
            try {
                if (command.equals("mark")) {
                    checkDescription(desc);

                    int index = Integer.valueOf(desc);
                    Task t = tasks.get(index - 1);
                    t.setDone();
                    updateTasksFile(tasks);

                    printDottedLine();
                    System.out.println("You actually did something? Marked done:");
                    System.out.println(t);
                    printDottedLine();
                } else if (command.equals("unmark")) {
                    checkDescription(desc);

                    int index = Integer.valueOf(desc);
                    Task t = tasks.get(index - 1);
                    t.setNotDone();
                    updateTasksFile(tasks);

                    printDottedLine();
                    System.out.println("Come on now, don't be useless. Marked not done:");
                    System.out.println(t);
                    printDottedLine();
                } else if (command.equals("delete")) {
                    int index = Integer.valueOf(desc);
                    Task t = tasks.get(index - 1);
                    tasks.remove(t);
                    updateTasksFile(tasks);

                    printDottedLine();
                    System.out.println("I have deleted:");
                    System.out.println(t);
                    printDottedLine();
                } else if (command.equals("deadline")) {
                    checkDescription(desc);

                    // Separating the deadline from description
                    String[] descBy = desc.split(" /by ", 2);
                    desc = descBy[0];
                    String by = "";
                    if (descBy.length > 1) {
                        by = descBy[1];
                    }

                    Deadline newTask = new Deadline(desc, by);
                    addTask(tasks, newTask);
                } else if (command.equals("todo")) {
                    checkDescription(desc);

                    ToDo newTask = new ToDo(desc);
                    addTask(tasks, newTask);
                } else if (command.equals("event")) {
                    checkDescription(desc);

                    // Separating the start from description
                    String[] descFrom = desc.split(" /from ", 2);
                    desc = descFrom[0];
                    String from = "";
                    if (descFrom.length > 1) {
                        from = descFrom[1];
                    }
                    // Separating the end out
                    String[] fromTo = from.split(" /to ", 2);
                    from = fromTo[0];
                    String to = "";
                    if (fromTo.length > 1) {
                        to = fromTo[1];
                    }

                    Event newTask = new Event(desc, from, to);
                    addTask(tasks, newTask);
                } else if (command.equals("list")) {
                    printDottedLine();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(String.format("%s. %s", i + 1, tasks.get(i)));
                    }
                    printDottedLine();
                } else {
                    throw new LaiException("I don't recognise this command at all. You likely made an extremely " +
                            "disappointing mistake, or I did. I can't tell for sure.");
                }

                input = processInput(scanner);
                command = input[0];
                desc = input[1];
            } catch (LaiException e) {
                printDottedLine();
                System.out.println(e);
                printDottedLine();

                input = processInput(scanner);
                command = input[0];
                desc = input[1];
            } catch (NumberFormatException e) {
                printDottedLine();
                System.out.println("Error occurred: Numbers only, please.");
                printDottedLine();

                input = processInput(scanner);
                command = input[0];
                desc = input[1];
            }
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Goodbye, we shall meet again. Hopefully.");
        System.out.println("---------------------------------------------------------");
    }
}
