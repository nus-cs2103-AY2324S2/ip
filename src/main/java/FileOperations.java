import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileOperations {
    private FileOperations() {}

    public static File loadFile() throws IOException {
        File data = new File("./data/data.txt");
        data.getParentFile().mkdirs();
        data.createNewFile();
        return data;
    }

    public static ChatSession createChatSession(File file) throws FileCorruptionException {
        TaskList taskList = new TaskList(file);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = FileOperations.parseTaskData(taskData);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileCorruptionException("Unable to read file");
        }
        return new ChatSession(taskList);
    }

    private static Task parseTaskData(String data) throws Exception {
        String[] fields = data.split("\\|");
        Task t;

        switch (fields[0]) {
        case "T":
            t = FileOperations.parseTodoData(fields);
            break;
        case "D":
            t = FileOperations.parseDeadlineData(fields);
            break;
        case "E":
            t = FileOperations.parseEventData(fields);
            break;
        default:
            throw new Exception();
        }
        return t;
    }

    private static ToDo parseTodoData(String[] fields) {
        boolean isDone = (Integer.valueOf(fields[2]) == 1);
        return new ToDo(fields[1], isDone);
    }

    private static Deadline parseDeadlineData(String[] fields) {
        boolean isDone = (Integer.valueOf(fields[2]) == 1);
        return new Deadline(fields[1], isDone, fields[3]);
    }

    private static Event parseEventData(String[] fields) {
        boolean isDone = (Integer.valueOf(fields[2]) == 1);
        return new Event(fields[1], isDone, fields[3], fields[4]);
    }



}
