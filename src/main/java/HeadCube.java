import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
public class HeadCube {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        load();
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] split = input.split(" ",2);

                if (input.equals("bye")) {
                    exit();
                    break;
                } else if (input.equals("list")) {
                    list();
                } else if (split[0].equals("mark")){
                    mark(Integer.parseInt(split[1]));

                } else if (split[0].equals("delete")) {
                    delete(Integer.parseInt(split[1]));
                } else if (input.equals("save")) {
                    save();
                } else {
                    add(input);
                }
            } catch (HeadCubeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void greet() {
        System.out.println("Hello! I'm HeadCube\n" + "What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void add(String task) throws HeadCubeException{
        String[] split = task.split(" ",2 );
        String event = split[0];
        String description;

        if (event.equals("todo")) {
            if (split.length < 2 || split[1].isBlank()) {
                throw new HeadCubeException("Todo cannot be empty!!");
            }
            tasks.add(new ToDos(split[1]));
        } else if (event.equals("deadline")) {
            String[] parts = split[1].split(" /by ",2);
            description = parts[0];
            String by = parts[1];
            tasks.add(new Deadlines(description,by));
        } else if (event.equals("event")) {
            String[] parts = split[1].split(" /from ", 2);
            description = parts[0];
            String[] times = parts[1].split(" /to ", 2);
            String start = times[0].trim();
            String end = times[1].trim();
            tasks.add(new Events(description, start, end));
        } else {
            throw new HeadCubeException("I do not understand what that means!!");
        }

        System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println();
    }

    public static void mark(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.done();
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public static void delete(int taskNumber) {
        Task removedTask = tasks.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    public static void save() {
        try {
            String directoryPath = "./data";
            String filePath = directoryPath + "/HeadCube.txt";
            Files.createDirectories(Paths.get(directoryPath));
            File file = new File(filePath);
            if (!file.exists()) {
               file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
            System.out.println("Finished saving");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks" + e.getMessage());
        }
    }

    public static void load() {
        File file = new File("./data/HeadCube.txt");
        if (!file.exists()) {
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parse(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No tasks to load" + e.getMessage());
        }
    }

    private static Task parse(String input) {
        String[] parts = input.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String event = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;

        if ("T".equals(event)) {
            task = new ToDos(description);
        } else if ("D".equals(event)) {
            if (parts.length > 3) {
                String by = parts[3].replace("(by: ", "").replace(")", "");
                task = new Deadlines(description,by);
            }
        } else {
            if (parts.length > 3) {
                String timeInfo = parts[3].replace("(from: ", "").replace(")", "");
                String[] times = timeInfo.split(" to: ");
                String start = times[0];
                String end = times.length > 1 ? times[1] : "";
                task = new Events(description, start, end);
            }
        }

        if (isDone) {
            task.done();
        }
        return task;
    }
}