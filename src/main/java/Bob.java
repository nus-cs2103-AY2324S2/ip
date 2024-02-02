import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Bob {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final ArrayList<Task> TASKS = new ArrayList<>();

    private static File dataFile;
    private static final String DATA_DIR = "data";
    private static final String DATA_PATH = DATA_DIR + "/bob.txt";

    // TODO: Might need to move these to a separate class
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private static File createOrRetrieve() throws IOException {
        Path path = Paths.get(DATA_PATH);
        Path parent = path.getParent();
        Files.createDirectories(parent);
        if (Files.notExists(path)) {
            return Files.createFile(path).toFile();
        }
        return path.toFile();
    }

    private static void load() {
        try {
            dataFile = createOrRetrieve();
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String formattedTask = s.nextLine();
                String[] parameters = formattedTask.split(" \\| ");
                addTask(parameters[0], Arrays.copyOfRange(parameters, 2, parameters.length));
                TASKS.get(TASKS.size() - 1).isDone = parameters[1].equals("1");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage() + ". Data is not loaded.");
            System.exit(-1);
        }
    }

    private static void save(boolean isAppend) {
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile(), isAppend);
            BufferedWriter bw = new BufferedWriter(fw);
            if (isAppend) {
                bw.write(TASKS.get(TASKS.size() - 1).format());
                bw.newLine();
            } else {
                for (Task task : TASKS) {
                    bw.write(task.format());
                    bw.newLine();
                }
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage() + ". Changes not saved in the hard disk.");
        }
    }

    public static void handleMark(int taskIndex, boolean done) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= TASKS.size()) {
            throw new InvalidTaskIndexException();
        }

        Task task = TASKS.get(taskIndex);
        task.setDone(done);
        Ui.mark(task, done);

        save(false);
    }

    public static void handleDelete(int taskIndex) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= TASKS.size()) {
            throw new InvalidTaskIndexException();
        }

        Task task = TASKS.get(taskIndex);
        TASKS.remove(taskIndex);
        Ui.delete(task, TASKS.size());

        save(false);
    }

    public static void handleList() {
        Ui.list(TASKS);
    }

    public static Task addTask(String taskType, String[] parameters) {
        Task task;
        switch (taskType) {
            case Commands.TODO:
                task = new Todo(parameters[0]);
                break;
            case Commands.DEADLINE:
                task = new Deadline(parameters[0], parameters[1]);
                break;
            default:
                task = new Event(parameters[0], parameters[1], parameters[2]);
        }

        TASKS.add(task);

        return task;
    }

    public static void handleAdd(String taskType, String[] parameters) {
        try {
            Task task = addTask(taskType, parameters);
            Ui.add(task, TASKS.size());
        } catch (DateTimeParseException e) {
            Ui.print(Ui.INVALID_DATE_FORMAT);
            return;
        }

        save(true);
    }

    public static void main(String[] args) {
        load();
        Ui.print(Ui.GREET);

        while (true) {
            String command = SCANNER.nextLine();
            String[] commandArgs = command.split(" ", 2);

            if (commandArgs[0].equals(Commands.EXIT)) {
                Ui.print(Ui.EXIT);
                break;
            }

            Commands.processCommands(commandArgs);
        }
    }
}
