import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import exceptions.InternalTestCases;
import exceptions.TaskIndexException;
import enums.Commands;
import enums.TaskStatus;

public class Pan {
    public static List<Task> tasks = new ArrayList<Task>();
    public static void main(String[] args) {
        hello();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("");
                String instruction = scanner.nextLine();
                System.out.println("");

                if (instruction.equals("list")) {
                    list();
                    continue;
                } else if (instruction.equals(Commands.BYE.name().toLowerCase())) {
                    bye();
                    break;
                } else if (instruction.matches("(mark) \\d+")) {
                    String index = instruction.substring(4).trim();
                    if (Integer.parseInt(index) > tasks.size()) {
                        throw new TaskIndexException("You have entered an invalid index!");
                    }
                    mark(Integer.parseInt(index));
                    continue;
                } else if (instruction.matches("(unmark) \\d+")) {
                    String index = instruction.substring(6).trim();
                    if (Integer.parseInt(index) > tasks.size()) {
                        throw new TaskIndexException("You have entered an invalid index!");
                    }
                    unmark(Integer.parseInt(index));
                    continue;
                } else if (instruction.matches("(todo)\\s(.+)")) {
                    String desc = instruction.substring(4).trim();
                    ToDos todos = new ToDos(desc, TaskStatus.INCOMPLETE);
                    tasks.add(todos);
                    add(todos);
                    continue;
                } else if (instruction.matches("(deadline)\\s(.+)\\s(/by)\\s(.+)")) {
                    String postfix = instruction.substring(8).trim();
                    String desc = postfix.split("/by")[0].trim();
                    String byDate = postfix.split("/by")[1].trim();
                    Deadlines deadlines = new Deadlines(desc, TaskStatus.INCOMPLETE, byDate);
                    tasks.add(deadlines);
                    add(deadlines);
                    continue;
                } else if (instruction.matches("(event)\\s(.+)\\s(/from)\\s(.+)\\s(/to)\\s(.+)")) {
                    String postfix = instruction.substring(5).trim();
                    String desc = postfix.split("/from")[0].trim();
                    String from = postfix.split("/from")[1].split("/to")[0].trim();
                    String to = postfix.split("/from")[1].split("/to")[1].trim();
                    Events events = new Events(desc, TaskStatus.INCOMPLETE, from, to);
                    tasks.add(events);
                    add(events);
                    continue;
                } else if (instruction.matches("(delete) \\d+")) {
                    String index = instruction.substring(6).trim();
                    if (Integer.parseInt(index) > tasks.size()) {
                        throw new TaskIndexException("You have entered an invalid index!");
                    }
                    delete(Integer.parseInt(index));
                    continue;
                } else {
                    // catch other test cases
                    InternalTestCases.TestMissingParameters(instruction);
                    InternalTestCases.TestInvalidCommand(instruction);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        scanner.close();
    }

    public static void hello() {
        System.out.println("Hello! I'm Pan\nWhat can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i+1) + "." + tasks.get(i).toString());
        }
    }

    public static void add(Task instruction) {
        System.out.println("Got it. I've added this task:\n\t" + instruction.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        save();
    }

    public static void mark(int index) {
        Task task = tasks.get(index - 1);
        task.setIsDone(TaskStatus.COMPLETE);
        System.out.println("Nice! I've marked this task as done:\n\t" + task.toString());
        save();
    }

    public static void unmark(int index) {
        Task task = tasks.get(index - 1);
        task.setIsDone(TaskStatus.INCOMPLETE);
        System.out.println("OK, I've marked this task as not done yet:\n\t" + task.toString());
        save();
    }

    public static void delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        save();
    }

    public static void save() {
        try{
            String filePath = "./output/pan.txt";
            File outputFile = new File(filePath);
            FileWriter writer = new FileWriter(outputFile, false);
            // attempts to create the new file
            outputFile.createNewFile();
            StringBuilder sb = new StringBuilder();
            tasks.stream().forEach(task -> sb.append(task.toString() + "\n"));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } 
    }
}
