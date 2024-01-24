import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskList {
    private static final int MAX_ITEMS = 100;
    private List<Task> tasks;
    private final String FILE_PATH = "./data/duke.txt";

    public TaskList() {
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public void addTask(String task) throws DukeException {
        if (this.tasks.size() < MAX_ITEMS) {
            try {
                parseTask(task);
                saveTasks();
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
                System.out.println(
                        "\tNow you have " + this.tasks.size() + " task" +
                                (this.tasks.size() == 1 ? "" : "s") + " in the list");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new DukeException("The task list is full.");
        }
    }

    public void parseTask(String task) throws DukeException {
        String[] parsed = task.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            throw new DukeException("OOPS! Please enter a task name");
        }
        TaskType taskType = TaskType.valueOf(parsed[0].toUpperCase());
        String taskDesc = parsed[1];
        switch (taskType) {
            case TODO:
                this.tasks.add(new ToDo(taskDesc));
                break;
            case DEADLINE:
                String[] parsedDeadline = taskDesc.split(" /by ");
                if (parsedDeadline.length <= 1) {
                    throw new DukeException("Please enter a valid deadline format");
                }
                String deadlineName = parsedDeadline[0];
                String by = parsedDeadline[1];
                this.tasks.add(new Deadline(deadlineName, by));
                break;
            case EVENT:
                String[] parsedEvent = taskDesc.split(" /from | /to ");
                if (parsedEvent.length <= 2) {
                    throw new DukeException("Please enter valid event format");
                }
                String eventName = parsedEvent[0];
                String start = parsedEvent[1];
                String end = parsedEvent[2];
                this.tasks.add(new Event(eventName, start, end));
                break;
            default:
                throw new DukeException("Please enter valid task type");
        }
    }

    public void deleteTask(int index) throws DukeException {
        if (this.tasks.size() == 0) {
            throw new DukeException("Task index is out of range.");
        }
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Index out of range");
        }
        Task deletedTask = this.tasks.remove(index - 1);
        saveTasks();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + deletedTask.toString());
        System.out.println(
                "\tNow you have " + this.tasks.size() + " task" +
                        (this.tasks.size() == 1 ? "" : "s") + " in the list");
    }

    public void markTask(int index) throws DukeException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        saveTasks();
        System.out.println("\tNice! I've marked this task as done:");
        currTask.toString();
    }

    public void unmarkTask(int index) throws DukeException{
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsUndone();
        saveTasks();
        System.out.println("\tOK, I've marked this task as not done yet:");
        currTask.toString();
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("\tThe task list is empty.");
        } else {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println("\t" + (i + 1) + "." + currTask.toString());
            }
        }
    }

    private void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                parseTaskFromString(taskString);
            }
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        } catch (DukeException e) {
            System.out.println("Error occurred when parsing file");
        }
    }
    private void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task: this.tasks) {
                fileWriter.write(task.fileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        }
    }

    private Task parseTaskFromString(String taskString) throws DukeException {
        String[] parts = taskString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String additionalInfo = parts.length > 3 ? parts[3].trim() : null;

        switch (taskType) {
            case "T":
                ToDo todo = new ToDo(description);
                if (isDone) todo.markAsDone();
                tasks.add(todo);
                return todo;
            case "D":
                if (additionalInfo == null) {
                    throw new DukeException("Invalid Deadline format in file");
                }
                Deadline deadline = new Deadline(description, additionalInfo);
                if (isDone) deadline.markAsDone();
                tasks.add(deadline);
                return deadline;
            case "E":
                String[] times = additionalInfo.split(" to ");
                if (times.length < 2) {
                    throw new DukeException("Invalid Event time format in file.");
                }
                String start = times[0].trim();
                String end = times[1].trim();

                Event event = new Event(description, start, end);
                if (isDone) event.markAsDone();
                tasks.add(event);
                return event;

            default:
                return null;
        }
    }
}
