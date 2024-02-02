import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class FileManager {

    private File file;
    private enum Task {
        T, D, E
    }

    public FileManager(String filepath) {
        try {
            this.file = new File(filepath);
            File dir = this.file.getParentFile();
            boolean isNewDir = dir.mkdirs();
            boolean isNewFile = this.file.createNewFile();
        } catch (IOException e) {
            throw new Error("There is an error in creating/opening the \"Shon.txt\" file."
                    + " Check if new directory is created.");
        }
    }

    public TodoList loadList() {
        try {
            Scanner scanner = new Scanner(this.file);
            TodoList list = new TodoList();
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Task task = Task.valueOf(String.valueOf(data.charAt(0)));
                switch (task) {
                case T:
                    addTodo(data, list);
                    break;
                case D:
                    addDeadline(data, list);
                    break;
                case E:
                    addEvent(data, list);
                    break;
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new Error("Check that new data file is created (if needed) in initializer.");
        }
    }

    private void addTodo(String data, TodoList list) {
        String[] d = data.split(" \\| ", 3);
        String isDoneStatus = d[1], description = d[2];
        boolean isDone = isDoneStatus.equals("1");
        list.addTodo(description, isDone);
    }

    private void addDeadline(String data, TodoList list) {
        String[] d = data.split(" \\| ", 4);
        String isDoneStatus = d[1], description = d[2], by = d[3];
        boolean isDone = isDoneStatus.equals("1");
        list.addDeadline(description, by, isDone);
    }

    private void addEvent(String data, TodoList list) {
        String[] d = data.split(" \\| ", 5);
        String isDoneStatus = d[1], description = d[2], from = d[3], to = d[4];
        boolean isDone = isDoneStatus.equals("1");
        list.addEvent(description, from, to, isDone);
    }

    public void updateData(String[] data) {
        try {
            FileWriter writer = new FileWriter(this.file.getPath());
            for (String line : data) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to data file.");
        }
    }
}
