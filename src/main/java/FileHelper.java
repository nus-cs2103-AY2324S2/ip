import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private  FileWriter fileWriter;
    private  String filePath = "./data/duke.txt";
    private static FileHelper fileHelper;

    public FileHelper() throws IOException {
        fileWriter = new FileWriter(filePath, true);
    }

    public static FileHelper getInstance() throws IOException {
        if (fileHelper == null) {
            fileHelper = new FileHelper();
            return fileHelper;
        } else {
            return fileHelper;
        }
    }

    public  void backupTasks(List<Task> tasks) throws IOException {
        new FileWriter(filePath).write("");
        tasks.forEach(this::saveTask);
        fileWriter.close();
    }

    private void saveTask(Task task) {
        try {
            List<String> taskString = new ArrayList<>();
            String hasDone = String.valueOf(task.getHasDone() ? 1 : 0);
            if (task instanceof Todo) {
                taskString.add("T");
                taskString.add(hasDone);
                taskString.add(task.getDescription());
            } else if (task instanceof Event) {
                taskString.add("E");
                taskString.add(hasDone);
                taskString.add(task.getDescription());
                taskString.add(((Event) task).getStartDateString() + "-" + ((Event) task).getEndDateString());
            } else {
                taskString.add("D");
                taskString.add(hasDone);
                taskString.add(task.getDescription());
                taskString.add(((Deadline) task).getDeadlineString());
            }
            fileWriter.write(String.join(" | ", taskString));
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Sorry ah bro, I failed to save your task: " + task);
        }
    }

    public List<Task> loadTask() throws IOException, FileCorruptedException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            List<Task> tasks = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                tasks.add(parseTask(line));
            }
            return tasks;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new FileCorruptedException("Bro, sorry ah, the file data may be corrupted");
        }
    }

    private Task parseTask(String taskString) throws FileCorruptedException {
        String[] taskArray = taskString.split("\\|");
        for(int i = 0; i < taskArray.length; i++) {
            taskArray[i] = taskArray[i].trim();
        }
        if (!taskArray[1].equals("1") && !taskArray[1].equals("0")) {
            throw new FileCorruptedException("Bro, sorry ah, the file data may be corrupted");
        }
        boolean hasDone = Integer.parseInt(taskArray[1]) == 1;


        switch (taskArray[0]) {
            case "T":
                if (taskArray.length != 3) {
                    throw new FileCorruptedException("Bro, sorry ah, the file data may be corrupted");
                }
                return new Todo(taskArray[2], hasDone);
            case "E":
                if (taskArray.length != 4) {
                    throw new FileCorruptedException("Bro, sorry ah, the file data may be corrupted");
                }
                String starDate = taskArray[3].split("-")[0];
                String endDate = taskArray[3].split("-")[1];
                return new Event(taskArray[2], hasDone, LocalDateTime.parse(starDate,
                        DateTimeFormatter.ofPattern("MM dd yyyy HH:mm")) , LocalDateTime.parse(endDate,
                        DateTimeFormatter.ofPattern("MM dd yyyy HH:mm")));
            case "D":
                if (taskArray.length != 4) {
                    throw new FileCorruptedException("Bro, sorry ah, the file data may be corrupted");
                }
                return new Deadline(taskArray[2], hasDone, LocalDateTime.parse(taskArray[3],
                        DateTimeFormatter.ofPattern("MM dd yyyy HH:mm")));
            default:
                throw new FileCorruptedException("Bro, sorry ah, the file data may be corrupted");
        }
    }
}
