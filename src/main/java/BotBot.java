import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BotBot {
    public static String LIST_PATH = "data/list.txt";

    public static void main(String[] args) throws BotBotException, IOException {
        // Init
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        TaskList list = new TaskList();
        BotBot.greet();

        // Create/access file to store list
        File file = new File(LIST_PATH);;
        boolean listCreated = file.createNewFile();
        if (!listCreated) {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String next = s.nextLine();
                list.addTaskInit(Task.parseTask(next));
            }
        }

        // Main bot loop
        while (scanner.hasNext()) {
            String nextTask = scanner.next();
            BotBot.divider();
            // Logic sequence
            if (nextTask.startsWith("bye")) {
                BotBot.exit();
                break;
            } else if (nextTask.startsWith("mark")) {
                System.out.println("Good job on completing the task:");
                System.out.println(Integer.parseInt(nextTask.split(" ", 2)[1]));
                list.mark(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else if (nextTask.startsWith("unmark")) {
                System.out.println("I have unmarked a task:");
                list.unmark(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else if (nextTask.startsWith("list")) {
                System.out.println("These are the tasks in your list:");
                list.printList();
            } else if (nextTask.startsWith("delete")) {
                list.deleteTask(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else {
                list.addTask(nextTask);
            }
            BotBot.divider();
        }
        scanner.close();

        // Save back to file
        if(file.delete()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(LIST_PATH);

        try {
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.getFileRep(i));
                if (i != list.size() - 1) {
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // Print functionalities
    private static void print(String string) {
        System.out.println(string);
    }
    private static void divider() {
        BotBot.print("########################################");
    }
    public static void greet() {
        BotBot.divider();
        BotBot.print("I am BotBot!\nWhat can I do for you?");
        BotBot.divider();
    }
    public static void exit() {
        BotBot.print("Goodbye! See you soon!");
        BotBot.divider();
    }

}
