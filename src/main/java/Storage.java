import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public File store;

    public Storage(String filePath) {
        this.store = new File(filePath);
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> task = new ArrayList<Task>();

        if (this.store.exists()) {
            Scanner reader = new Scanner(store);
            while (reader.hasNextLine()) {
                if (reader.hasNext("T")) {
                    String data = reader.nextLine();
                    String[] divided = data.split("\\|", 2);
                    boolean isDone;
                    String type = divided[0];
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    String status = divided[0];
                    if (status == "1") {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    String D = divided[1];
                    Task new_task = new ToDos(D);
                    if (isDone) {
                        new_task.markAsDone();
                    }
                    task.add(new_task);
                } else if (reader.hasNext("D")) {
                    String data = reader.nextLine();
                    String[] divided = data.split("\\|", 2);
                    boolean isDone;
                    String type = divided[0];
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    System.out.println(data);
                    String status = divided[0];
                    if (status == "1") {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    data = divided[1];
                    System.out.println(data);
                    divided = data.split("\\|", 2);
                    String D = divided[0];
                    String by = divided[1].trim();
                    System.out.println(by);
                    Task new_task = new Deadline(D, by);
                    if (isDone) {
                        new_task.markAsDone();
                    }
                    task.add(new_task);
                } else {
                    String data = reader.nextLine();
                    String[] divided = data.split("\\|", 2);
                    boolean isDone;
                    String type = divided[0];
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    String status = divided[0];
                    if (status == "1") {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    String D = divided[0];
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    String from = divided[0].trim();
                    String to = divided[1].trim();
                    System.out.println(from);
                    System.out.println(to);
                    Task new_task = new Events(D, from, to);
                    if (isDone) {
                        new_task.markAsDone();
                    }
                    task.add(new_task);
                }
            }
        }
        return task;

    }


}
