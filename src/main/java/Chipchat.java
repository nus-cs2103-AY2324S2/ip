import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class Chipchat {
    private static List<Task> taskList = new ArrayList<>();
    private static String BOT_NAME = "Chipchat";
    private static String STORAGE_PATH = "/src/main/data/storage.txt";
    private static void saveData() {
        String pathToWorkingDir = System.getProperty("user.dir");
        String absPath = pathToWorkingDir + STORAGE_PATH;
        String[] paths = absPath.split("/");
        Path filePath = Paths.get(paths[0], Arrays.copyOfRange(paths, 1, paths.length));
//        System.out.println("filePath: "+filePath);

        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException x) {
            System.err.format("File existence check, IOException: %s%n", x);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            for (Task t : Chipchat.taskList) {
                String dataString = t.dataString();
//                System.out.println("dataString: " + dataString);
                writer.write(dataString, 0, dataString.length());
            }
        } catch (IOException x) {
            System.err.format("File write, IOException: %s%n", x);
        }
    }

    private static void loadData() {
        String pathToWorkingDir = System.getProperty("user.dir");
        String absPath = pathToWorkingDir + STORAGE_PATH;
        String[] paths = absPath.split("/");
        Path filePath = Paths.get(paths[0], Arrays.copyOfRange(paths, 1, paths.length));
//        System.out.println("filePath: "+filePath);

        if (Files.notExists(filePath)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Task task = parseLoadedTask(line);
                Chipchat.taskList.add(task);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private static Task parseLoadedTask(String line) {
        String[] details = line.split("\\|");
        String taskType = details[0];
        String isDone = details[1];
        String description = details[2];
        System.out.println(taskType + isDone + description);

        Task task;
        switch (taskType) {
            case "todo":
                task = new Todo(description);
                break;
            case "deadline":
                task = new Deadline(description, details[3]);
                break;
            case "event":
                task = new Event(description, details[3], details[4]);
                break;
            default:
                task = null;
        }
        if (task != null && isDone.equals("true")) {
            task.mark();
        }
        return task;
    }

    public static void main(String[] args) {
        String greeting_msg = "Hello! I'm " + Chipchat.BOT_NAME + "\n"
                + "What can I do for you?";
        String exit_msg = "Bye. Hope to see you again soon!\n";
        System.out.println(greeting_msg);

        Chipchat.loadData();

        Scanner reader = new Scanner(System.in);

        while (true) {
            String user_input = reader.nextLine();
            String[] inputs = user_input.split(" ", 2);

            try {
                if (inputs[0].isEmpty()) throw new ChipchatException("Please enter something");
                else if (inputs[0].equals("bye")) {
                    break;

                } else if (inputs[0].equals("list")) {
                    System.out.println("\nHere are the tasks in your list:");
                    for (int i = 0; i < Chipchat.taskList.size(); i++) {
                        int k = i + 1;
                        System.out.println(k + ". " + Chipchat.taskList.get(i));
                    }

                } else if (inputs[0].equals("mark")) {
                    if (inputs.length == 1 || inputs[1].isEmpty()) throw new ChipchatException("Sorry. Please specify the item no. you want to mark");
                    int idx = Integer.parseInt(inputs[1]);
                    Task tmp = Chipchat.taskList.get(idx-1);
                    tmp.mark();
                    System.out.println("\nNice! I've marked this task as done:\n" + tmp);

                } else if (inputs[0].equals("unmark")) {
                    if (inputs.length == 1 || inputs[1].isEmpty())
                        throw new ChipchatException("Sorry. Please specify the item no. you want to unmark");
                    int idx = Integer.parseInt(inputs[1]);
                    Task tmp = Chipchat.taskList.get(idx - 1);
                    tmp.unmark();
                    System.out.println("\nNice! I've marked this task as done:\n" + tmp);

                } else if (inputs[0].equals("delete")) {
                    if (inputs.length == 1 || inputs[1].isEmpty())
                        throw new ChipchatException("Sorry. Please specify the item no. you want to delete");
                    int idx = Integer.parseInt(inputs[1]);
                    if (idx < 1 || idx > taskList.size())
                        throw new ChipchatException("Sorry that item no. is not the list");
                    Task tmp = Chipchat.taskList.get(idx - 1);
                    System.out.println("\nNoted. I've removed this task:\n" + tmp);
                    Chipchat.taskList.remove(idx - 1);
                } else if (inputs[0].equals("todo")) {
                    if (inputs.length == 1 || inputs[1].isEmpty()) throw new ChipchatException("Sorry. Please specify a description");
                    Task tmp = new Todo(inputs[1]);
                    Chipchat.taskList.add(tmp);
                    System.out.println("\nGot it. I've added this task:");
                    System.out.println(tmp);
                    System.out.println("Now you have " + Chipchat.taskList.size() + " tasks in the list.");
                } else if (inputs[0].equals("deadline")) {
                    if (inputs.length == 1 || inputs[1].isEmpty()) throw new ChipchatException("Sorry. Please specify a description");
                    String[] str = inputs[1].split(" /by ");
                    Task tmp = new Deadline(str[0], str[1]);
                    Chipchat.taskList.add(tmp);
                    System.out.println("\nGot it. I've added this task:");
                    System.out.println(tmp);
                    System.out.println("Now you have " + Chipchat.taskList.size() + " tasks in the list.");
                } else if (inputs[0].equals("event")) {
                    if (inputs.length == 1 || inputs[1].isEmpty()) throw new ChipchatException("Sorry. Please specify a description");
                    String[] str = inputs[1].split(" /from ");
                    String[] from_to = str[1].split(" /to ");
                    Task tmp = new Event(str[0], from_to[0], from_to[1]);
                    Chipchat.taskList.add(tmp);
                    System.out.println("\nGot it. I've added this task:");
                    System.out.println(tmp);
                    System.out.println("Now you have " + Chipchat.taskList.size() + " tasks in the list.");
                } else {
                    throw new ChipchatException("Sorry. I don't know what that means.");
                }
                Chipchat.saveData();
            } catch (ChipchatException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(exit_msg);
    }
}
