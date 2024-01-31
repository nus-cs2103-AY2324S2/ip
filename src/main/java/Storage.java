import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    Storage(String filePath) {
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (Task task : tasks) {
                fw.write(task.saveFormat());
            }
            fw.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void load(ArrayList<Task> tasks) {
        try {
            Scanner sc = new Scanner(this.file);
            while(sc.hasNext()) {
                String s = sc.nextLine();
                String[] task = s.split(" \\| ");
                Task t = null;
                switch(task[0]) {
                    case "T":
                        t = new ToDo(task[2]);
                        break;
                    case "D":
                        t = new Deadline(task[2], task[3]);
                        break;
                    default:
                        t = new Event(task[2], task[3], task[4]);
                }
                if (task[1].equals("1")) {
                    t.markTask();
                }
                tasks.add(t);
            }
            FileWriter fw = new FileWriter(this.file);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}


