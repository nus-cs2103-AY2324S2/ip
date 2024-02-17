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
                char status = line.charAt(1);
                String info = line.substring(2);
                switch (type) {
                    case 'T':
                        Task todo = new Todo(info);
                        checkStatusAdd(status, todo);
                        break;
                    case 'D':
                        String[] date = info.split(" /by ",2);
                        Task deadline = new Deadline(date[0], date[1]);
                        checkStatusAdd(status, deadline);
                        break;
                    case 'E':
                        String[] eventMsg = info.split(" /from ",2);
                        String[] time = eventMsg[1].split(" /to ",2);
                        Task event = new Event(eventMsg[0], time[0], time[1]);
                        checkStatusAdd(status, event);
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
            for (Task tk : taskList.getList()) {
                fw.write(tk.storeInfo()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check the status of the Task and add it to the TaskList.
     * @param status Description of a Task.
     * @param tk Task checked.
     */
    public void checkStatusAdd(char status, Task tk) {
        if (status == '0') {
            tk.uncomplete();
        } else {
            tk.complete();
        }
        taskList.add(tk);
    }
}