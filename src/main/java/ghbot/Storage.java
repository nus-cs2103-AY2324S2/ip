package ghbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String fileName;
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Return the list of task that were saved previously.
     * @return A list of tasks.
     */
    public List<Task> getInputFromFile() {
        List<Task> lst = new ArrayList<>();
        try {
            File file = new File(this.fileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] subStr = str.split("\\| ");
                if (subStr[0].trim().equalsIgnoreCase("T")) {
                    Todo todo = new Todo(subStr[2]);
                    if (subStr[1].trim().equalsIgnoreCase("1")) {
                        todo.isCompleted();
                    } else {
                        todo.isNotCompleted();
                    }
                    lst.add(todo);
                } else if (subStr[0].trim().equalsIgnoreCase("D")) {
                    Deadline deadline = new Deadline(subStr[2], subStr[3]);
                    if (subStr[1].trim().equalsIgnoreCase("1")) {
                        deadline.isCompleted();;
                    } else {
                        deadline.isNotCompleted();
                    }
                    lst.add(deadline);
                } else if (subStr[0].trim().equalsIgnoreCase("E")) {
                    Event event = new Event(subStr[2], subStr[3], subStr[4]);
                    if (subStr[1].trim().equalsIgnoreCase("1")) {
                        event.isCompleted();;
                    } else {
                        event.isNotCompleted();
                    }
                    lst.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Chat history are not present!");
        }
        return lst;
    }

    /**
     * Write/Update/Delete task in the file.
     * @param lst A list of tasks.
     * @throws IOException throw exception if there is an issue updating the file.
     */
    public void writeDataToFile(List<Task> lst) throws IOException {
        try {
            File file = new File(this.fileName);
            FileWriter fw = new FileWriter(file);
            for (Task task : lst) {
                fw.write(task.toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Files.createDirectories(Paths.get("./data"));
        }
    }
}