package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Represents the disk storage for the task list.
 */
public class Storage {
    private File file;
    private ArrayList<Task> tasks = new ArrayList<>(100);
    /**
     * Creates a new Storage object.
     *
     * @param filePath The path to the file that stores the task list.
     * @throws DukeException If the file does not exist and cannot be created.
     */
    public Storage(String filePath) throws DukeException {
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating new file");
            }
        }
    }
    /**
     * Saves the task list to the file.
     * Assumption: load should be called before save.
     *
     * @throws DukeException If there is an error writing to file.
     */
    public void save() throws DukeException {
        try{
        FileWriter writer = new FileWriter(file);
        for(int a = 0; a < tasks.size(); a++ ) {
            Task task = tasks.get(a);
            String taskString = task.toStore();
            writer.write(taskString + "\n");
        }
        writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving file");
        }
    }

    /**
     * Loads the task list from the file.
     *
     * @return The task list.
     * @throws DukeException If there is an error reading from file.
     */
    public ArrayList<Task> load() throws DukeException {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = bufferedReader.readLine();
            //System.out.println(str);
            while (str != null){
                String[] loadtokens = str.split("/");
                //for(int a = 0; a< loadtokens.length; a++){System.out.println(loadtokens[a]);}
                String type = loadtokens[0];
                String status = loadtokens[1];
                String desc = loadtokens[2];
                String priority = loadtokens[3];
                Task task;
                switch (type) {
                    case "T":
                        task = new Task(desc);
                        task.setPriority(priority);
                        break;
                    case "E":
                        String to = loadtokens[4];
                        String from = loadtokens[5];
                        task = new Event(desc, to, from);
                        task.setPriority(priority);
                        break;
                    case "D":
                        String duedate = loadtokens[4];
                        task = new Deadline(desc, duedate);
                        task.setPriority(priority);
                        break;
                    default:
                        throw new DukeException("File corrupted.");
                }
                if(status.equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
                str = bufferedReader.readLine();

            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
                throw new DukeException("Error loading tasks from file");
            } catch (IOException e) {
                throw new DukeException("Error saving file");
            }
        return tasks;

    }


}
