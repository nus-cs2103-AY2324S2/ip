import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class TaskList extends ArrayList<Task>  {

    private static final String FILE_DIRECTORY = "./data";
    private static final String FILE_NAME = "Mike.txt";

    protected File file;

    TaskList() {
        extractFile();
    }

    public void extractFile() {
        String filePath = FILE_DIRECTORY + "/" + FILE_NAME;

        try {
            if (new File(FILE_DIRECTORY).mkdirs()) {
                Ui.display("File location created at " + filePath);
            } else {
                Ui.display("File location already exists");
            }

            File file = new File(filePath);
            this.file = file;

            if (file.createNewFile()) {
                Ui.display("File created: " + file.getName());
            } else {
                Ui.display("File already exists");
            }

            Scanner fileScanner = new Scanner(file);
            String line;
            Task newTask;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine();
                newTask = extractTask(line);
                this.add(newTask);
            }
        } catch (IOException e) {
            Ui.displayError("404 File not found");
        } catch (MikeException e) {
            Ui.displayError(e.getMessage());
        }
    }

    public void writeToFile() {
        String filePath = FILE_DIRECTORY + "/" + FILE_NAME;

        try (PrintWriter out = new PrintWriter(filePath)) {
            for (Task task : this) {
                out.println(task.getFileEncoding());
            }
        } catch (IOException e) {
            Ui.display(e.getMessage());
        }
    }

    private Task extractTask(String line) throws IndexOutOfBoundsException, MikeException {
        String[] taskInformation = line.split(",");
        String taskType = taskInformation[0];
        String taskDescription = taskInformation[1];
        boolean taskIsCompleted = taskInformation[2].equals("true");

        Task newTask;

        switch (taskType) {
        case "Todo":
            newTask = new Todo(taskDescription);
            break;
        case "Deadline": // format: taskType,taskDescription,taskDone,deadline
            String deadline = taskInformation[3];
            newTask = new Deadline(taskDescription, deadline);
            break;
        case "Event": // format: taskType,taskDescription,taskDone,startDate,endDate
            String startDate = taskInformation[3];
            String endDate = taskInformation[4];
            newTask = new Event(taskDescription, startDate, endDate);
            break;
        default:
            throw new MikeException("File corrupted");
        }

        if (taskIsCompleted) {
            newTask.markAsDone();
        }

        return newTask;
    }

    @Override
    public String toString() {
        return String.join("\n", IntStream.range(0, this.size())
                .boxed()
                .map(i -> {
                    Task task = this.get(i);
                    int index = i + 1;
                    return String.format("%d.%s", index, task.toString());
                })
                .toArray(String[]::new)
        );
    }
}
