package chingu;

import chingu.task.*;

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
            sortTaskType(reader, task);
        }
        store.delete();
        return task;
    }

    public void sortTaskType(Scanner reader, ArrayList<Task> task) {
        while (reader.hasNextLine()) {
            if (reader.hasNext("T")) {
                settleTodo(reader, task);
            } else if (reader.hasNext("D")) {
                settleDeadline(reader, task);
            } else {
                settleEvent(reader, task);
            }
        }
    }

    public void settleTodo(Scanner reader, ArrayList<Task> task) {
        String data = reader.nextLine();
        String[] divided = data.split("\\|", 2);
        boolean isDone;
        data = divided[1];
        divided = data.split("\\|", 2);
        String status = divided[0].trim();
        if (status.equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        data = divided[1];
        divided = data.split("/priority", 2);
        String description = divided[0].trim();
        String priority = divided[1].trim();
        Task new_task = new ToDos(description, priority);
        if (isDone) {
            new_task.markAsDone();
        }
        task.add(new_task);
    }

    public void settleEvent(Scanner reader, ArrayList<Task> task) {
        String data = reader.nextLine();
        String[] divided = data.split("\\|", 2);
        boolean isDone;
        data = divided[1];
        divided = data.split("\\|", 2);
        String status = divided[0].trim();
        if (status.equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        data = divided[1];
        divided = data.split("\\|", 2);
        String D = divided[0].trim();
        data = divided[1];
        divided = data.split("\\|", 2);
        String from = divided[0].trim();
        String toPriority = divided[1].trim();
        divided = toPriority.split("/priority", 2);
        String to = divided[0].trim();
        String priority = divided[1].trim();
        Task new_task = new Events(D, from, to, priority);
        if (isDone) {
            new_task.markAsDone();
        }
        task.add(new_task);
    }

    public void settleDeadline(Scanner reader, ArrayList<Task> task) {
        String data = reader.nextLine();
        String[] divided = data.split("\\|", 2);
        boolean isDone;
        data = divided[1];
        divided = data.split("\\|", 2);
        String status = divided[0].trim();
        if (status.equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        data = divided[1];
        divided = data.split("\\|", 2);
        String D = divided[0];
        String byPriority = divided[1].trim();
        divided = byPriority.split("/priority", 2);
        String by = divided[0].trim();
        String priority = divided[1].trim();
        Task new_task = new Deadline(D, by, priority);
        if (isDone) {
            new_task.markAsDone();
        }
        task.add(new_task);
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
                String to_add = sortTask(temp, "");
                to_record += to_add;
            }
            data.write(to_record);
            data.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sortTask(Task temp, String to_add) {
        if (temp instanceof ToDos) {
            return saveToDo(temp, to_add);
        } else if (temp instanceof Events) {
            return saveEvent(temp, to_add);
        } else {
            return saveDeadline(temp, to_add);
        }
    }

    public String saveToDo(Task temp, String to_add) {
        String isDone = "0";
        if (temp.isDone) {
            isDone = "1";
        }
        to_add += "T" +" | " + isDone
                + " | " + temp.describeTask()
                + "/priority " + temp.getPriority() + "\n";
        return to_add;
    }

    public String saveEvent(Task temp, String to_add) {
        Events t = (Events) temp;
        String isDone = "0";
        if (temp.isDone) {
            isDone = "1";
        }
        to_add += "E" + " | " + isDone
                + " | " + temp.describeTask() + "|"
                + t.from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                + "|" + t.to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                + "/priority " + t.getPriority()
                + "\n";
        return to_add;
    }

    public String saveDeadline(Task temp, String to_add) {
        Deadline t = (Deadline) temp;
        String isDone = "0";
        if (temp.isDone) {
            isDone = "1";
        }
        to_add += "D" + " | " + isDone
                + " | " + temp.describeTask() + "|"
                + t.by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                + "/priority " + t.getPriority()
                + "\n";
        return to_add;
    }


}
