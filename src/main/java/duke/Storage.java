package duke;

import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from saved file and
 * saving tasks in the file after the job is done.
 */
public class Storage {

    public File store;

    /**
     * The constructor that creates an instance of Storage class.
     * @param filePath where the file is located and the file where the tasks will be saved
     */
    public Storage(String filePath) {
        this.store = new File(filePath);
    }

    /**
     * Loads the tasks saved in the file into the tasklist once the program start running.
     * @return Arraylist<Task>
     * @throws IOException
     */
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
                    Task new_task = new Events(D, from, to);
                    if (isDone) {
                        new_task.markAsDone();
                    }
                    task.add(new_task);
                }
            }
        }
        store.delete();
        return task;

    }

    /**
     * Saves the tasks in Tasklist in the file before the program ends.
     * @param tasks
     */
    public void save(TaskList tasks) {
        try {
            FileWriter data = new FileWriter("./data/list.txt");
            String to_record = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                Task temp = tasks.getTask(i);
                if (temp instanceof ToDos) {
                    System.out.println("EH");
                    String isDone = "0";
                    if (temp.isDone) {
                        isDone = "1";
                    }
                    to_record += "T" +" | " + isDone
                            + " | " + temp.describeTask() + "\n";

                } else if (temp instanceof Events) {
                    System.out.println("OH");
                    Events t = (Events) temp;
                    String isDone = "0";
                    if (temp.isDone) {
                        isDone = "1";
                    }
                    to_record += "E" + " | " + isDone
                            + " | " + temp.describeTask() + "|"
                            + t.from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                            + "|" + t.to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                            + "\n";

                } else if (temp instanceof Deadline) {
                    System.out.println("YOU");
                    Deadline t = (Deadline) temp;
                    String isDone = "0";
                    if (temp.isDone) {
                        isDone = "1";
                    }
                    to_record += "D" + " | " + isDone
                            + " | " + temp.describeTask() + "|"
                            + t.by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                            + "\n";
                }
            }
            data.write(to_record);
            data.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
