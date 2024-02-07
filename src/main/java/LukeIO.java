import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LukeIO {
    private File file;
    public LukeIO(String path) {
        this.file = new File(path);

        if (!this.file.exists()) {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("Sorry! There was an error creating the file! :'(");
                e.printStackTrace();
            }
        }

    }
    public ArrayList<Task> readTask() throws FileException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        // Read tasks from file
        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(":");

                String type = parts[0];
                String markOrUnmark = parts[1];
                String description = parts[2];
                if (type.equals("T")) {
                    Task task = new Todo(description);
                    if (markOrUnmark.equals("X")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } else if (type.equals("D")) {
                    String by = parts[3];
                    Task task = new Deadline(description, by);
                    if (markOrUnmark.equals("X")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } else if (type.equals("E")) {
                    String from = parts[3];
                    String to = parts[4];
                    Task task = new Event(description, from, to);
                    if (markOrUnmark.equals("X")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }

            }

        } catch (FileNotFoundException e) {
            // create new file


        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FileException("Sorry! File is corrupted! :'(");
        }

        return tasks;
    }

    public void writeTask(ArrayList<Task> tasks) throws FileException {
        try (FileWriter fw = new FileWriter(this.file, false)) {
            for (Task task : tasks) {
                fw.write(task.toDataString() + "\n");
            }


        } catch (IOException e) {
            throw new FileException("Sorry! File not found! :'(");
        }


    }
}
