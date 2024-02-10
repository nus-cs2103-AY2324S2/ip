import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskRepository {
    private final String FILE_PATH = "./data/taskStorage.txt";
    private TaskList taskList;

    public TaskRepository() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            this.taskList = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitOutput = line.split("\\|");

                System.out.println("Reached");
                System.out.println(Arrays.toString(splitOutput));

                // switch (splitOutput[0]) {
                // case "T":
                // if (splitOutput[1].equals("X")) {
                // taskList.addTodo(new Todo(true, splitOutput[2]));
                // } else {
                // TaskList.addTask(new Todo(false, splitOutput[2]));
                // }
                // break;
                // case "D":
                // if (splitOutput[1].equals("X")) {
                // TaskList.addTask(new Deadline(true, splitOutput[2],splitOutput[3]));
                // } else {
                // TaskList.addTask(new Deadline(false, splitOutput[2],splitOutput[3]));
                // }
                // break;
                // case "E":
                // if (splitOutput[1].equals("X")) {
                // TaskList.addTask(new Event(true, splitOutput[2],splitOutput[3],
                // splitOutput[4]));
                // } else {
                // TaskList.addTask(new Event(false, splitOutput[2],splitOutput[3],
                // splitOutput[4]));
                // }
                // break;
                // }
            }
        } catch (IOException e) {
            // System.out.println(MamtaException.IOException());
        }
        return taskList;
    }

    public void saveTasks(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (String task : taskList.listTasks()) {
                fileWriter.write(task + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();

        // Create a new task list with some tasks
        TaskList originalTaskList = new TaskList();
        originalTaskList.addTodo("read book");
        originalTaskList.addDeadline("return book", "June 6th");
        originalTaskList.addEvent("project meeting", "Aug 6th 2pm", "4pm");
        originalTaskList.addTodo("join sports club");

        // Save the tasks to the file
        taskRepository.saveTasks(originalTaskList);

        // Load the tasks from the file
        TaskList loadedTaskList = taskRepository.loadTasks();

        // Check if the loaded tasks match the original tasks
        System.out.println(loadedTaskList.listTasks());
    }
}