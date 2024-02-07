import java.io.*;
import java.util.ArrayList;


public class Storage {
    private String filePath;
    public Storage(String filepath) {
        this.filePath = filepath;
    }
    public void saveToFile(TaskList taskList) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            ArrayList<Task> store = taskList.getTaskList();
            for (Task task : store) {
                writer.println(task.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TaskList loadFromFile() {
        TaskList store = new TaskList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.startsWith("[T]")) {
                    int index = message.lastIndexOf("]");
                    String description = "todo" + message.substring(index + 1);
                    ToDos todo = new ToDos(description);
                    store.add(todo);
                } else if (message.startsWith("[D]")) {
                    int startIndex = message.lastIndexOf("]");
                    int endIndex = message.lastIndexOf("(");
                    int timeIndex = message.lastIndexOf(":");
                    String dueDate = "by " + message.substring(timeIndex + 1, message.length() - 1);
                    String description = message.substring(startIndex + 2, endIndex);
                    Deadlines deadline = new Deadlines(description, dueDate);
                    store.add(deadline);
                } else if (message.startsWith("[E]")) {
                    int startIndex = message.lastIndexOf("]");
                    int endIndex = message.lastIndexOf("(");
                    int startIdx = message.indexOf(":");
                    int endIdx = message.lastIndexOf(":");
                    String description = message.substring(startIndex + 2, endIndex);
                    String start = "from" + message.substring(startIdx + 1, endIdx - 2);
                    String end = "to" + message.substring(endIdx + 1, message.length() - 1);
                    Events event = new Events(description, start, end);
                    store.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Creating an empty list.");
            return new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return store;
    }
}
