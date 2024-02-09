package duke.util;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;

public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    public ArrayList<Task> readFile () {
        ArrayList<Task> result = new ArrayList<>();
        try {
            Scanner readTextFile = new Scanner(new File(this.filepath));
            while (readTextFile.hasNextLine()) {
                String line = readTextFile.nextLine();
                String[] splits = line.split(" [|] ");
                Task readTask = null;
                switch (splits[0]) {
                    case "todo":
                        readTask = new ToDo(splits[1], splits[2]);
                        break;
                    case "deadline":
                        readTask = new Deadline(splits[1], splits[2], splits[3]);
                        break;
                    case "event":
                        readTask = new Event(splits[1], splits[2], splits[3], splits[4]);
                        break;
                }
                result.add(readTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file, program will now exit");
            return result;
        }
        return result;
    }
    public void addToWriteFile (Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath, true));
            writer.append(task.writeObject());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occur while writing to file");
        }
    }
    public void rewriteFile (ArrayList<Task> current) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath));
            for (int i = 0; i < current.size(); i++) {
                writer.write(current.get(i).writeObject());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occur while writing to file");
        }
    }
}
