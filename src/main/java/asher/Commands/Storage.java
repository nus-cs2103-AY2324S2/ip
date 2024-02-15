package asher.Commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import asher.Tasks.Deadline;
import asher.Tasks.Event;
import asher.Tasks.TaskList;
import asher.Tasks.Todo;
import asher.Tasks.Task;

public class Storage {
    public Storage() {
    }

    public void getFileContents(String filePath, TaskList tasks) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new IOException("Failed to create file: " + filePath);
                }
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = createTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            } scanner.close();
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    // from the data we have, break it down to get the task listed
    private Task createTask(String list) {
        Task task;
        String[] splitParts = list.split(" \\| ");

        // invalid format
        if (splitParts.length < 3) {
            return null;
        }

        String type = splitParts[0];
        boolean isCompleted = splitParts[1].equals("1");
        String description = splitParts[2];

        if (type.equals("T")) {
            Todo todo = new Todo(description);
            if (isCompleted) {
                todo.markDone();
            }
            task = todo;
        } else if (type.equals("D") && splitParts.length == 5) {
            String date = splitParts[3];
            LocalDate dueDate = LocalDate.parse(date);
            String dueTimeInString = splitParts[4];
            LocalTime dueTime = LocalTime.parse(dueTimeInString);
            Deadline deadline = new Deadline(description, dueDate, dueTime);
            if (isCompleted) {
                deadline.markDone();
            }
            task = deadline;
        } else if (type.equals("E") && splitParts.length == 7) {
            String startDateInString = splitParts[3];
            LocalDate startDate = LocalDate.parse(startDateInString);
            String startTimeInString = splitParts[4];
            LocalTime startTime = LocalTime.parse(startTimeInString);

            String endDateInString = splitParts[5];
            LocalDate endDate = LocalDate.parse(endDateInString);
            String endTimeInString = splitParts[6];
            LocalTime endTime = LocalTime.parse(endTimeInString);
            Event event = new Event(description, startDate, startTime, endDate, endTime);
            if (isCompleted) {
                event.markDone();
            }
            task = event;
        } else {
            task = null;
        }
        return task;
    }

    public void writeToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("./taskLists.txt");
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.get(i);
                if (task == null) {
                    break;
                }
                String taskString = task.writeToString();
                fw.write(taskString + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Cannot write into the file" + e.getMessage());
        }
    }
}
