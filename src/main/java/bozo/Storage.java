package bozo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File txtFile = new File(filePath);
            Scanner scan = new Scanner(txtFile);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                int isDone = Integer.parseInt(parts[1]);
                String taskName = parts[2];

                switch (type) {
                    case "T":
                        Todo td = new Todo(taskName);
                        if (isDone == 1) {
                            td.isDone = true;
                        } else {
                            td.isDone = false;
                        }
                        list.add(td);
                        break;
                    case "D":
                        Deadline d = new Deadline(taskName, parts[3]);
                        if (isDone == 1) {
                            d.isDone = true;
                        } else {
                            d.isDone = false;
                        }
                        list.add(d);
                        break;
                    case "E":
                        Event e = new Event(taskName, parts[3], parts[4]);
                        if (isDone == 1) {
                            e.isDone = true;
                        } else {
                            e.isDone = false;
                        }
                        list.add(e);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No tasks saved :-((");
        }
        return list;
    }

    public void saveList(TaskList list) {
        try {
            File txtFile = new File(filePath);
            txtFile.getParentFile().mkdirs();
            FileWriter f = new FileWriter(txtFile);
            for (Task task : list) {
                f.write(task.save());
                f.write(System.lineSeparator());
            }
            f.close();
        } catch (IOException e) {
            System.out.println("I can't save ur list :((");
        }
    }

}
