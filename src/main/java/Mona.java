import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Mona {
    protected static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) throws MonaException{
        File log = new File("data/duke.txt");
        if (!log.exists()) {
            createLog();
        } else {
            readLog(log);
        }
        String introduction = "  ____________________________________________________________\n"
                + "   Hello! I'm Mona\n"
                + "   What can I do for you?\n"
                + "  ____________________________________________________________\n";
        String farewell = "  ____________________________________________________________\n"
                + "   Bye. Hope to see you again soon!\n"
                + "  ____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        System.out.println(introduction);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(farewell);
                break;
            }
            if (input.equals("list")) {
                System.out.println("  ____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    Task currTask = tasks.get(i);
                    System.out.println("    " + (i + 1) + ". " + currTask);
                }
                System.out.println("  ____________________________________________________________");
            } else if (input.startsWith("todo")) {
                if (input.length() < 6) {
                    String response = "  ____________________________________________________________\n"
                            + "     OOPS!!! The description of a todo cannot be empty \n"
                            + "  ____________________________________________________________\n";
                    System.out.println(response);
                    throw new MonaException("Mona needs a description in order to add the todo task...");
                }
                String details = input.substring(5);
                Todo newTask = new Todo(details);
                tasks.add(newTask);
                String response = "  ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     " + newTask + "\n"
                        + "     Now you have " + tasks.size() + " tasks in the list.\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
                writeToFile(log);
            } else if (input.startsWith("deadline")) {
                if (input.length() < 10) {
                    String response = "  ____________________________________________________________\n"
                            + "     OOPS!!! The description of a deadline cannot be empty \n"
                            + "  ____________________________________________________________\n";
                    System.out.println(response);
                    throw new MonaException("Mona needs a description in order to add the deadline task...");
                }
                String details = input.substring(9);
                String[] parts = details.split(" /by ");
                Deadline newTask = new Deadline(parts[0], parts[1]);
                tasks.add(newTask);
                String response = "  ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     " + newTask + "\n"
                        + "     Now you have " + tasks.size() + " tasks in the list.\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
                writeToFile(log);
            } else if (input.startsWith("event")) {
                if (input.length() < 7) {
                    String response = "  ____________________________________________________________\n"
                            + "     OOPS!!! The description of an event cannot be empty \n"
                            + "  ____________________________________________________________\n";
                    System.out.println(response);
                    throw new MonaException("Mona needs a description in order to add the event task...");
                }
                String[] details = input.substring(6).split(" /from ");
                String[] startAndEnd = details[1].split(" /to ");
                Event newTask = new Event(details[0], startAndEnd[0], startAndEnd[1]);
                tasks.add(newTask);
                String response = "  ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     " + newTask + "\n"
                        + "     Now you have " + tasks.size() + " tasks in the list.\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
                writeToFile(log);
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currTask = tasks.get(taskIndex);
                currTask.markAsDone();
                String response = "  ____________________________________________________________\n"
                        + "     Nice! I've marked this task as done: \n"
                        + "     " + currTask + "\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
                writeToFile(log);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currTask = tasks.get(taskIndex);
                currTask.markAsNotDone();
                String response = "  ____________________________________________________________\n"
                        + "     OK, I've marked this task as not done yet: \n"
                        + "     " + currTask + "\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
                writeToFile(log);
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                Task removedTask = tasks.remove(taskIndex);
                String response = "  ____________________________________________________________\n"
                        + "     Noted. I've removed this task: \n"
                        + "     " + removedTask + "\n"
                        + "     Now you have " + tasks.size() + " tasks in the list.\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
                writeToFile(log);
            } else {
                String response = "  ____________________________________________________________\n"
                        + "     OOPS!!! I'm sorry, but I don't know what that means :< \n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
                throw new MonaException("Mona does not recognise this command...");
            }
        }


        }
    public static void createLog() {
        String workingDirectory = System.getProperty("user.dir");
        try {
            Path dataDirectory = Paths.get(workingDirectory + "/data");
            Files.createDirectories(dataDirectory);
            File logFile = new File(workingDirectory + "/data/duke.txt");
            logFile.createNewFile();
            System.out.println(logFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error occurred setting up log" + e.getMessage());
        }
    }

    public static void readLog(File f) {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] task = s.nextLine().split("\\|");
                tasks.add(parseLogEntry(task));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        }
    }
    public static Task parseLogEntry(String[] logEntry) {
        String description = logEntry[2];
        boolean isCompleted = logEntry[1].equals("1");
        switch (logEntry[0]) {
            case "T":
                Task currTask = new Todo(description);
                currTask.setCompletion(isCompleted);
                return currTask;
            case "D":
                currTask = new Deadline(description, logEntry[3]);
                currTask.setCompletion(isCompleted);
                return currTask;
            case "E":
                currTask = new Event(description, logEntry[3], logEntry[4]);
                currTask.setCompletion(isCompleted);
                return currTask;
            default:
                return null;
        }
    }
    public static void writeToFile(File f) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.parseToLogRepresentation() + "\n");
        }
        try {
            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println("Problem writing to log: " + e.getMessage());
        }
    }
}
