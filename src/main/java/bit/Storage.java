package bit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class handles the storage of tasks to files in harddisk
 */

public class Storage {

    private final String filePath = "./data/bit.txt";
    private final File file = new File(filePath);

    /**
     * Initialize storage according to file path
     */
    public Storage() {
        boolean isCreated = createFile(file);

    }

    /**
     * Create a file myFile if it does not exist. If file exists after this method, return true.
     * Otherwise, return false.
     * @param myFile file to be created
     * @return true or false based on whether file exists after method is run.
     */
    public boolean createFile(File myFile) {
        try {
            if (!myFile.exists()) {
                myFile.getParentFile().mkdirs();
                myFile.createNewFile();
            }
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

    }

    /**
     * Load all tasks stored in text file to Tasklist.
     * @param list list of tasks to be loaded
     */
    public void loadFile(Tasklist list) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String next;
            while ((next = br.readLine()) != null) {
                String[] parts = next.split("/");
                if (parts.length == 1) {
                    return;
                }
                System.out.println(parts[0]);
                System.out.println(parts[1]);
                if (parts[0].equals("T")) {
                    Task t = new Todo(parts[1]);
                    if (parts[2].equals("M")) {
                        t.complete();
                    }
                    list.addFromStorage(t);
                } else if (parts[0].equals("D")) {
                    Task d = new Deadline(parts[1], parts[2]);
                    if (parts[3].equals("M")) {
                        d.complete();
                    }
                    list.addFromStorage(d);
                } else if (parts[0].equals("E")) {
                    Task e = new Event(parts[1], parts[2], parts[3]);
                    if (parts[4].equals("M")) {
                        e.complete();
                    }
                    list.addFromStorage(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Errorrrr...");
        }
    }

    /**
     * Save all tasks in the list to a text file.
     * @param tasklist the tasks to be stored.
     */
    public void saveAll(Tasklist tasklist) {

        for (int i = 0; i < tasklist.getSize(); i++) {
            Task t = tasklist.getTask(i);
            if (t instanceof Todo) {
                saveToFile(t.isDone, t.DESCRIPTION);
            } else if (t instanceof Deadline) {
                saveToFile(t.isDone, t.DESCRIPTION, ((Deadline) t).getDeadline());
            } else if (t instanceof Event) {
                saveToFile(t.isDone, t.DESCRIPTION, ((Event) t).getStart(), ((Event) t).getEnd());
            }

        }
    }

    /**
     * Save a todo to text file.
     * @param marked completion status of todo
     * @param description the description of the task
     */
    public void saveToFile(boolean marked, String description) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write("T/" + description);
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    /**
     * Save a deadline to text file.
     * @param marked completion status of task.
     * @param description body of task.
     * @param deadline deadline of the task.
     */
    public void saveToFile(boolean marked, String description, String deadline) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(("D/" + description + "/" + deadline));
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    /**
     * Save an event to textfile.
     * @param marked Completion status of task.
     * @param description Body of task.
     * @param start Start time of event
     * @param end Ending time of event
     */
    public void saveToFile(boolean marked, String description, String start, String end) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write("E/" + description + "/" + start + "/" + end);
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    /**
     * Clear file after extracting tasks.
     */
    public void cleanList() {
        try {
            FileWriter myCleaner = new FileWriter(filePath);
            myCleaner.write("");
            myCleaner.close();
        } catch (IOException e) {
            return;
        }
    }
}

