import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lumiere {
    private static String fileLocation = "./lumiere.txt";
    private static Path filePath = Paths.get(fileLocation);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Task> list = loadTasksFromFile();
        System.out.println("Hello! I'm Lumiere\n" + "Tell me what I can do for you, be my guest!\n");

        while (true) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(Integer.toString(i + 1) + "." + list.get(i).stringify());
                }
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.contains("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int space = command.indexOf(" ");
                int num = Integer.parseInt(command.substring(space + 1)); // unmark X
                Task curr = list.get(num - 1);
                curr.unmark();
                list.set(num - 1, curr);
                saveTasksToFile(list);
                System.out.println(list.get(num - 1).stringify());
            } else if (command.contains("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int space = command.indexOf(" ");
                int num = Integer.parseInt(command.substring(space + 1)); // mark X
                Task curr = list.get(num - 1);
                curr.mark();
                list.set(num - 1, curr);
                saveTasksToFile(list);
                System.out.println(list.get(num - 1).stringify());
            } else if (command.contains("delete")) {
                System.out.println("Noted. I've removed this task:");
                int space = command.indexOf(" ");
                int num = Integer.parseInt(command.substring(space + 1)); // delete X
                System.out.println(list.get(num - 1).stringify());
                list.remove(num - 1);
                saveTasksToFile(list);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                // create Task object with command
                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    System.out.println("Got it. I've added this task:");
                    int space = command.indexOf(" ");
                    String type = command.substring(0, space);

                    if (type.equals("todo")) {
                        String rest = command.substring(space + 1);
                        Todo task = new Todo(rest, false);
                        System.out.println(task.stringify());
                        list.add(task);
                    } else if (type.equals("deadline")) {
                        String rest = command.substring(space + 1);
                        String[] description = rest.split(" /by ");
                        Deadline task = new Deadline(description[0], false, description[1]);
                        System.out.println(task.stringify());
                        list.add(task);
                    } else if (type.equals("event")) {
                        String rest = command.substring(space + 1);
                        String[] description = rest.split(" /from ");
                        String[] time = description[1].split(" /to ");
                        Event task = new Event(description[0], false, time[0], time[1]);
                        System.out.println(task.stringify());
                        list.add(task);
                    }
                    saveTasksToFile(list);
                    System.out.println("Now you have " + Integer.toString(list.size()) + " tasks in the list.");
                }
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        sc.close();
    }

    public static void saveTasksToFile(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation))) {
            for (Task task : tasks) {
                // change task to string
                String line = "";
                if (task instanceof Todo) {
                    line = "T | " + task.isMarked() + " | " + task.getItem();
                } else if (task instanceof Deadline) {
                    Deadline t = (Deadline) task;
                    line = "D | " + t.isMarked() + " | " + t.getItem() + " | " + t.getbyWhen();
                } else if (task instanceof Event) {
                    Event t = (Event) task;
                    line = "E | " + t.isMarked() + " | " + t.getItem() + " | " + t.getStart() + " | " + t.getEnd();
                }
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(" | ");

                if (info.length == 3) {
                    boolean marked;
                    if (info[1].equals("true"))
                        marked = true;
                    else
                        marked = false;
                    Todo task = new Todo(info[2], marked);
                    tasks.add(task);
                } else if (info.length == 4) {
                    boolean marked;
                    if (info[1].equals("true"))
                        marked = true;
                    else
                        marked = false;
                    Deadline task = new Deadline(info[2], marked, info[3]);
                    tasks.add(task);
                } else if (info.length == 5) {
                    boolean marked;
                    if (info[1].equals("true"))
                        marked = true;
                    else
                        marked = false;
                    Event task = new Event(info[2], marked, info[3], info[4]);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            // Handle file not found
            if (!Files.exists(filePath)) {
                try {
                    Files.createFile(filePath);
                } catch (IOException err) {
                    err.printStackTrace();
                    System.out.println("Failed to create lumiere.txt file, please create manually!");
                }
            }
        }
        return tasks;
    }
}
