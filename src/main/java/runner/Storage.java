package runner;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class for Storage object.
 * Aim to manage the saving and retrieval of the user's tasks.
 */
public class Storage {
    private final TaskList taskList;

    /**
     * Constructor for Storage.
     * @param taskList One taskList to manage.
     */
    public Storage (TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Load taskList from the existing file.
     */
    public void loadList() {
        try {
            File f = new File("src/main/data/task_list.txt");
            System.out.println("Loading List...");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                char type = line.charAt(0);
                String info = line.substring(2);
                switch (type) {
                    case 'T':
                        Task td = new Todo(info);
                        addCheckedTask(line, td);
                        break;
                    case 'D':
                        String[] deadLine = info.split(" /by ",2);
                        Task dd = new Deadline(deadLine[0], deadLine[1]);
                        addCheckedTask(line, dd);
                        break;
                    case 'E':
                        String[] event = info.split(" /from ",2);
                        String[] time = event[1].split(" /to ",2);
                        Task et = new Event(event[0], time[0], time[1]);
                        addCheckedTask(line, et);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("task_list.txt Not Found");
            try {
                System.out.println("Creating the File...");
                new File("src/main/data/task_list.txt").createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Save tasks to the existing file.
     */
    public void saveList() {
        try {
            FileWriter fw = new FileWriter("src/main/data/task_list.txt");
            for (Task tk : taskList.get_list()) {
                fw.write(tk.getInfo()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check the status of the Task and add it to the TaskList.
     * @param line Description of a Task.
     * @param t Task checked.
     */
    public void addCheckedTask(String line, Task t) {
        if (line.charAt(1) == '0') {
            t.uncomplete();
        } else {
            t.complete();
        }
        taskList.add(t);
    }
}
