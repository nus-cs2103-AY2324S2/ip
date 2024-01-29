import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws JohnnyException {
        try {
            File file = new File(filePath);
            List<Task> tasks = new ArrayList<>();

            if (!file.isFile() && file.getParentFile().mkdir()) {
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNext()) {
                    String input = scanner.nextLine();
                    String[] parsedInput = input.split(" \\| ");
                    Task task;
                    String taskType = parsedInput[0];

                    switch(taskType) {
                    case "T":
                        task = new ToDo(parsedInput[2]);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parsedInput[3], INPUT_DATE_FORMAT);
                        task = new Deadline(parsedInput[2], by);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parsedInput[3], INPUT_DATE_FORMAT);
                        LocalDateTime to = LocalDateTime.parse(parsedInput[4], INPUT_DATE_FORMAT);
                        task = new Event(parsedInput[2], from, to);
                        break;
                    default:
                        throw new JohnnyException("The file has been corrupted bro.");
                    }

                    if (parsedInput[1].equals("1")) {
                        task.mark();
                    }
                    tasks.add(task);
                }
                scanner.close();
            }
            return tasks;
        } catch (IOException e) {
            throw new JohnnyException("I can't create a new file bro: " + e.getMessage());
        }
    }

    public void rewriteFile(TaskList tasks) throws JohnnyException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).addToFile());
            }
        } catch (IOException e) {
            throw new JohnnyException("Cannot write to file bro: " + e.getMessage());
        }
    }

    public void appendToFile(Task task) throws JohnnyException {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(task.addToFile());
        } catch (IOException e) {
            throw new JohnnyException("Cannot write to file bro: " + e.getMessage());
        }
    }

}
