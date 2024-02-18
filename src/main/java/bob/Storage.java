package bob;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * A class for saving and loading files from storage.
 */
public class Storage {
    private final String filePath;

    /*
     * A constructor that specifies the storage filepath.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*
     * A method to load tasks from the specified file path.
     *
     * @return A TaskList parsed from the save file.
     */
    public TaskList loadFile() {
        TaskList taskList = new TaskList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String taskDescription = parts[2].trim();

                Task task;

                if (taskType.equals("T")) {
                    task = new ToDo(taskDescription);
                }

                else if (taskType.equals("D")) {
                    String deadlineDate = parts[3].trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(deadlineDate, formatter);
                    task = new Deadline(taskDescription, dateTime);
                }

                else if (taskType.equals("E")) {
                    String fromDate = parts[3].trim();
                    String toDate = parts[4].trim();

                    task = new Event(taskDescription, fromDate, toDate);
                }

                else {
                    throw new IllegalStateException("Unexpected value: " + taskType);
                }

                if (isDone) {
                    task.markAsDone();
                }

                taskList.addTask(task);
            }
        }

        catch (FileNotFoundException e) {
            File data = new File("data");
            data.mkdir();
            File tasks = new File(data, "tasks.txt");
            try {
                tasks.createNewFile();
            }
            catch (IOException x) {
                System.out.println(x.getMessage());
            }
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("File loaded.");
        return taskList;
    }

    /*
     * A method to save the current tasks.
     *
     * @param taskList An ArrayList of the current tasks.
     */
    public void saveFile(TaskList taskList) {
        try {
            File tasks = new File(filePath);

            FileWriter writer = new FileWriter(tasks);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Task task : taskList) {
                String taskType;
                String line;
                String isDone = task.getIsDone() ? "1" : "0";
                String description = task.getDescription();

                if (task instanceof Deadline) {
                    taskType = "D";
                    LocalDateTime deadlineDate = ((Deadline) task).getBy();
                    String deadline = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                    line = String.format("%s | %s | %s | %s", taskType, isDone, description, deadline);
                }

                else if (task instanceof Event) {
                    taskType = "E";
                    String from = ((Event) task).getFrom();
                    String to = ((Event) task).getTo();
                    line = String.format("%s | %s | %s | %s | %s", taskType, isDone, description, from, to);
                }

                else {
                    taskType = "T";
                    line = String.format("%s | %s | %s", taskType, isDone, description);
                }

                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("File saved.");
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
