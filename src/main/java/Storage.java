import java.io.*;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TodoList load() {
        TodoList list = new TodoList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.loadTask(line);
            }
            System.out.println("Tasks have been loaded from " + filePath);
        } catch (IOException e) {

        }
        return list;
    }

    public void saveToFile(String tasksText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(tasksText);
            System.out.println("Tasks have been saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

}
